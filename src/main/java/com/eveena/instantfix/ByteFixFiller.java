package com.eveena.instantfix;

import java.nio.charset.Charset;

public class ByteFixFiller implements IFiller<ByteFixMessage> {
	
	private static final Charset CHARSET = Charset.forName("ISO-8859-1");
	public static final byte[] beginString = "8".getBytes(CHARSET);
	public static final byte[] checkSum = "10".getBytes(CHARSET);
	public static final byte[] senderCompID = "49".getBytes(CHARSET);
	public static final byte[] targetCompID = "56".getBytes(CHARSET);
	public static final byte[] msgType = "35".getBytes(CHARSET);
	public static final byte[] msgSeqNum = "34".getBytes(CHARSET);
	public static final byte[] clOrdID = "11".getBytes(CHARSET);
	public static final byte[] origClOrdID = "41".getBytes(CHARSET);

	public void fill(ByteFixMessage msg, PairDataReader data) {
		if (data.headerEquals(senderCompID))
			msg.setSenderCompID(data.readByteString());
		else if (data.headerEquals(targetCompID))
			msg.setTargetCompID(data.readByteString());
		else if (data.headerEquals(msgSeqNum))
			msg.setMsgSeqNum(data.readByteString());
		else if (data.headerEquals(msgType))
			msg.setMsgType(data.readByteString());
		else if (data.headerEquals(beginString))
			msg.setBeginString(data.readByteString());
		else if (data.headerEquals(checkSum))
			msg.setCheckSum(data.readByteString());
		else if (data.headerEquals(clOrdID))
			msg.setClOrdID(data.readByteString());
		else if (data.headerEquals(origClOrdID))
			msg.setOrigClOrdID(data.readByteString());
	}
	
	public ByteFixMessage newObj() {
		return new ByteFixMessage();
	}
}
