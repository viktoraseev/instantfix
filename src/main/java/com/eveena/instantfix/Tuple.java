package com.eveena.instantfix;

public final class Tuple {
	private String first;
	private String second;

	public Tuple(String name, String value) {
		first = name;
		second = value;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getSecond() {
		return second;
	}

	public void setSecond(String second) {
		this.second = second;
	}
}