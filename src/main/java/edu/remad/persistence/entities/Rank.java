package edu.remad.persistence.entities;

public enum Rank {
	ENSIGN(0), LIEUTENANT(1), COMMANDER(2), CAPTAIN(3), COMMODORE(4), ADMIRAL(5);

	public final int ordinal;

	Rank(int ordinal) {
		this.ordinal = ordinal;
	}
}
