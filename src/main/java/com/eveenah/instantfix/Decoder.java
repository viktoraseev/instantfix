package com.eveenah.instantfix;

/**
 * Parsing algorithm: </br>
 * 1) mark begin of pair, found equals sign, found end.</br>
 * 2) pass found data to filler block. Filler will decode</br>
 * possible states: ready, looking for message, in a middle of message.
 * @author Victor
 *
 */
public class Decoder <T> {
	private PairDataReader data = new PairDataReader();
	private final IFiller<T> filler;
	private T msg;
	private boolean preservePair;
	
	public Decoder(IFiller<T> filler) {
		this.filler = filler;
	}
	
	public void reset() {
		preservePair = false;
		msg = null;
		data = new PairDataReader();
	}
	
	public void push(byte[] buf) {
		if (preservePair)
			throw new IllegalArgumentException("Prev message not read from buf");
		data.push(buf);
	}
	
	/**
	 * Decode current buf and return decoded message. return null if more data required.
	 */
	public T decode() {
		while(preservePair || data.parse()) {
			preservePair = false;
			if (data.headerEquals(Filler.beginString)) {
				if (msg == null) {
					// new message found
					// TODO change to filler.newObj(String type) 
					msg = filler.newObj();
				} else {
					// we parsed prev message, but found beginString. 
					// return prev message and do not parse for pair next time
					preservePair = true;
					T r = msg;
					msg = null;
					return r;
				}
			} else if (msg == null) {
				// found strange data without begin, skip it
				// TODO possibly create message with errors?
				continue;
			}
			filler.fill(msg, data);
			if (data.headerEquals(Filler.checkSum)) {
				//found end of message. reset current msg and return it
				T r = msg;
				msg = null;
				return r;
			}
		}
		// we didn't find anything in current buffer, we need new one
		return null;
	}
}
