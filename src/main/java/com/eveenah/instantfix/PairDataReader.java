package com.eveenah.instantfix;



public class PairDataReader {
	private static final byte EQUALS_SIGN = '=';
	private static final byte SOH = '\1';
	private static final int shift = Integer.MIN_VALUE;
	private byte[] prevBuf;
	private byte[] newBuf = new byte[0];
	private int beginPos; // pointer on first symbol
	private int equalsPos; // pointer on equals symbol
	private int sohPos; // pointer on soh
	private boolean headerFound = false;
	private boolean valueFound = false;
	/**
	 * position of next symbol to read
	 */
	private int currentPos;
	
	
	
	/**
	 * Checks if pair header equals to buffer
	 * @param buf
	 * @return
	 */
	public boolean headerEquals(byte[] buf) {
		if (beginPos < 0 && equalsPos < 0) {
			// header is completely in prevBuf
			int bufLen = buf.length;
			int beginLen = equalsPos - beginPos;
			return bufLen == beginLen && equals(prevBuf, buf, beginPos - shift, 0, bufLen);
		} else if (beginPos >=0 && equalsPos >=0) {
			// header is completely in newBuf
			int bufLen = buf.length;
			int beginLen = equalsPos - beginPos;
			return bufLen == beginLen && equals(newBuf, buf, beginPos, 0, bufLen);
		}
		// header is splitted
		int bufLen = buf.length;
		int rBegin = beginPos - shift;
		int beginLen = prevBuf.length - rBegin;
		
		return bufLen == beginLen + equalsPos &&
				equals(prevBuf, buf, rBegin, 0, beginLen) &&
				equals(newBuf, buf, 0, beginLen, equalsPos);
	}

	private boolean equals(byte[] src, byte[] dst, int srcPos, int dstPos, int len) {
		for(int i=0;i < len;i++ ) 
			if (src[srcPos + i] != dst[dstPos + i])
				return false;
		return true;
	}
	
	/**
	 * Search for b in src
	 * @param startPos start pos, included into search
	 * @param endPos last pos, not included into search
	 * @return pos or -1
	 */
	private int search(byte b, byte[] src, int startPos, int endPos) {
		for (int i = startPos; i < endPos; i++) {
			if (src[i] == b)
				return i;
		}
		return -1;
	}

	/**
	 * Provides more byte data for decoder. call parse() after that. Can only be called if previous data was fully parsed.
	 * @param buf
	 */
	public void push(byte[] buf) {
		int newLen = newBuf.length;
		if (currentPos != newLen)
			throw new IllegalStateException("Previous buf not parsed to end");

		currentPos = 0;
		if (headerFound && valueFound) {
			// found everything, can discard
			prevBuf = null;
			newBuf = buf;
			headerFound = false;
			return;
		} else if (headerFound && !valueFound) {
			//header found, value not found
			if (beginPos >= 0) {
				prevBuf = newBuf;
				beginPos += shift;
				equalsPos +=shift;
				newBuf = buf;
				return;
			} else {
				// data in prevBuf, we need to store all info in newBuf. expensive operation, hope will never occur
				// TODO optimize to copy only needed data
				int prevLen = prevBuf.length;
				byte[] mergedBuf = new byte[prevLen + newLen];
				System.arraycopy(prevBuf, 0, mergedBuf, 0, prevLen);
				System.arraycopy(newBuf, 0, mergedBuf, prevLen, newLen);
				beginPos = beginPos < 0 ? (beginPos) : (beginPos + prevLen + shift);
				equalsPos = equalsPos < 0 ? (equalsPos) : (equalsPos + prevLen + shift);
				newBuf = buf;
				prevBuf = mergedBuf;
				return;
			}
		} else if (!headerFound && !valueFound) {
			// value & header not found
			if (beginPos >= 0) {
				// TODO fix copying empty arrays
				prevBuf = newBuf;
				beginPos += shift;
				newBuf = buf;
				return;
			} else {
				// data in prevBuf, we need to store all info in newBuf. expensive operation, hope will never occur
				// TODO optimize to copy only needed data
				int prevLen = prevBuf.length;
				byte[] mergedBuf = new byte[prevLen + newLen];
				System.arraycopy(prevBuf, 0, mergedBuf, 0, prevLen);
				System.arraycopy(newBuf, 0, mergedBuf, prevLen, newLen);
				beginPos = beginPos < 0 ? (beginPos) : (beginPos + prevLen + shift);
				prevBuf = mergedBuf;
				newBuf = buf;
				return;
			}
		} else if (!headerFound && valueFound) {
			throw new IllegalStateException("Unexpected state  encountered");
		}
		throw new IllegalStateException("Unexpected state 2 encountered");
	} // method end

	/** searches for tag or value or both.
	 * @return true if tag+value was found. returns false if more data is needed
	 */
	public boolean parse() {
		if (!headerFound) {
			int pos = search(EQUALS_SIGN, newBuf, currentPos, newBuf.length);
			if (pos < 0) {
				// searched for header in buf, not found
				currentPos = newBuf.length;
				return false;
			} else {
				// found header
				currentPos= pos;
				equalsPos = currentPos;
				headerFound = true;
				return parse();
			}
		} else if (!valueFound) {
			int pos = search(SOH, newBuf, currentPos, newBuf.length);
			if (pos < 0) {
				// searched for soh in buf, not found
				currentPos = newBuf.length;
				return false;
			} else {
				// found value
				currentPos= pos;
				valueFound = true;
				sohPos = currentPos;
				return true;
			}
		}
		// parse called when header & value was found
		// reset and actually parse
		headerFound = false;
		valueFound = false;
		beginPos = currentPos + 1;
		return parse();
	}
	
	public String readString() {
		if (!headerFound || !valueFound)
			throw new IllegalStateException("Can't call read until parse returned true");
		return null;
	}

	public int readInt() {
		if (!headerFound || !valueFound)
			throw new IllegalStateException("Can't call read until parse returned true");
		return 0;
	}
}
