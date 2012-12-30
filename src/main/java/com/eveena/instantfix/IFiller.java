package com.eveena.instantfix;

public interface IFiller <T> {
	void fill(T msg, PairDataReader data);
	T newObj();
}
