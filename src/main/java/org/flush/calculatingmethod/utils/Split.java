package org.flush.calculatingmethod.utils;

public class Split {
	
	private String str;
	
	public Split(String str) {
		this.str = str;
	}
	
	public String[] getSplitedString() {
		String[] strs = str.split("|");
		return strs;
	}
	
}
