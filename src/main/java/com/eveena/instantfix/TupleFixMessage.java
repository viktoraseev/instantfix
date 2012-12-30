package com.eveena.instantfix;

import java.util.LinkedList;
import java.util.List;

public class TupleFixMessage {
	
	private final LinkedList<Tuple> values = new LinkedList<Tuple>();
	
	public TupleFixMessage() {
	}

	public String getValue(String name) {
		for(Tuple tuple : values) {
			if (name.equals(tuple.getFirst()))
				return tuple.getSecond();
		}
		return null;
	}

	public List<Tuple> getValues() {
		return values;
	}
	
	public void setValue(String name, String value) {
		values.add(new Tuple(name, value));
	}
}
