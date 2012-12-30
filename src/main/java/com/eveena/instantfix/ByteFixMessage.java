package com.eveena.instantfix;

public class ByteFixMessage {
	private ByteString beginString;
	private ByteString senderCompID;
	private ByteString targetCompID;
	private ByteString msgType;
	private ByteString msgSeqNum;
	private ByteString clOrdID;
	private ByteString origClOrdID;
	private ByteString checkSum;

	public ByteString getBeginString() {
		return beginString;
	}

	public void setBeginString(ByteString beginString) {
		this.beginString = beginString;
	}

	public ByteString getSenderCompID() {
		return senderCompID;
	}

	public void setSenderCompID(ByteString senderCompID) {
		this.senderCompID = senderCompID;
	}

	public ByteString getTargetCompID() {
		return targetCompID;
	}

	public void setTargetCompID(ByteString targetCompID) {
		this.targetCompID = targetCompID;
	}

	public ByteString getMsgType() {
		return msgType;
	}

	public void setMsgType(ByteString msgType) {
		this.msgType = msgType;
	}

	public ByteString getMsgSeqNum() {
		return msgSeqNum;
	}

	public void setMsgSeqNum(ByteString msgSeqNum) {
		this.msgSeqNum = msgSeqNum;
	}

	public ByteString getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(ByteString clOrdID) {
		this.clOrdID = clOrdID;
	}

	public ByteString getOrigClOrdID() {
		return origClOrdID;
	}

	public void setOrigClOrdID(ByteString origClOrdID) {
		this.origClOrdID = origClOrdID;
	}

	public ByteString getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(ByteString checkSum) {
		this.checkSum = checkSum;
	}
}
