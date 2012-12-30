package com.eveena.instantfix;

public class GenericFixMessage {
	private String beginString;
	private String senderCompID;
	private String targetCompID;
	private String msgType;
	private int msgSeqNum;
	private String clOrdID;
	private String origClOrdID;
	private int checkSum;
	private String error;
	private boolean garbled;

	public String getBeginString() {
		return beginString;
	}

	public void setBeginString(String beginString) {
		this.beginString = beginString;
	}

	public String getSenderCompID() {
		return senderCompID;
	}

	public void setSenderCompID(String senderCompID) {
		this.senderCompID = senderCompID;
	}

	public String getTargetCompID() {
		return targetCompID;
	}

	public void setTargetCompID(String targetCompID) {
		this.targetCompID = targetCompID;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getMsgSeqNum() {
		return msgSeqNum;
	}

	public void setMsgSeqNum(int msgSeqNum) {
		this.msgSeqNum = msgSeqNum;
	}

	public String getClOrdID() {
		return clOrdID;
	}

	public void setClOrdID(String clOrdID) {
		this.clOrdID = clOrdID;
	}

	public String getOrigClOrdID() {
		return origClOrdID;
	}

	public void setOrigClOrdID(String origClOrdID) {
		this.origClOrdID = origClOrdID;
	}

	public int getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(int checkSum) {
		this.checkSum = checkSum;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isGarbled() {
		return garbled;
	}

	public void setGarbled(boolean garbled) {
		this.garbled = garbled;
	}

}
