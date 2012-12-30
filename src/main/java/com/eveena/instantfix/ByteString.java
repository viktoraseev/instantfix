package com.eveena.instantfix;

public class ByteString {
	public static final ByteString EMPTY = new ByteString(new byte[0]);
	
	private final int offset;
	private final int length;
	private final byte[] value;

	public ByteString(byte[] buf) {
		this(buf, 0, buf.length);
	}

	public ByteString(byte[] buf, int offset, int length) {
		this.value = buf;
		this.offset = offset;
		this.length = length;
	}

	public byte byteAt(final int index) {
		return value[index + offset];
	}

	public int size() {
		return length;
	}

	public boolean isEmpty() {
		return length == 0;
	}

	public static ByteString copyFrom(final byte[] bytes, final int offset, final int size) {
		final byte[] copy = new byte[size];
		System.arraycopy(bytes, offset, copy, 0, size);
		return new ByteString(copy);
	}

	public static ByteString copyFrom(final byte[] bytes) {
		return copyFrom(bytes, 0, bytes.length);
	}
}
