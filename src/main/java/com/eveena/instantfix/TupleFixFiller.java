package com.eveena.instantfix;


public class TupleFixFiller implements IFiller<TupleFixMessage> {
	public void fill(TupleFixMessage msg, PairDataReader data) {
		msg.setValue(data.readHeader(), data.readString());
	}
	
	public TupleFixMessage newObj() {
		return new TupleFixMessage();
	}
}
