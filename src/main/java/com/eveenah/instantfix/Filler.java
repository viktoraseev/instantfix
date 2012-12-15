package com.eveenah.instantfix;

import java.nio.charset.Charset;

public class Filler implements IFiller<GenericFix> {
	public static final byte[] beginString = "8".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] checkSum = "10".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] senderCompID = "49".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] targetCompID = "56".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] msgType = "35".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] msgSeqNum = "34".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] clOrdID = "11".getBytes(Charset.forName("ISO-8859-1"));
	public static final byte[] origClOrdID = "41".getBytes(Charset.forName("ISO-8859-1"));

	public void fill(GenericFix msg, PairDataReader data) {
		if (data.headerEquals(senderCompID))
			msg.setSenderCompID(data.readString());
		else if (data.headerEquals(targetCompID))
			msg.setTargetCompID(data.readString());
		else if (data.headerEquals(msgSeqNum))
			msg.setMsgSeqNum(data.readInt());
		else if (data.headerEquals(msgType))
			msg.setMsgType(data.readString());
		else if (data.headerEquals(beginString))
			msg.setBeginString(data.readString());
		else if (data.headerEquals(checkSum))
			msg.setCheckSum(data.readInt());
		else if (data.headerEquals(clOrdID))
			msg.setClOrdID(data.readString());
		else if (data.headerEquals(origClOrdID))
			msg.setOrigClOrdID(data.readString());
	}
	
	public GenericFix newObj() {
		return new GenericFix();
	}
}
