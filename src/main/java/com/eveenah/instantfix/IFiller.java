package com.eveenah.instantfix;

public interface IFiller <T> {
	void fill(T msg, PairDataReader data);
	T newObj();
}
