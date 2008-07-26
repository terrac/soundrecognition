package main;

import java.util.ArrayList;

import old.dir;


public class Pattern {
	java.util.List<dir> list = new ArrayList<dir>();
	
	int height;
	int length;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return list.toString() + " h "+height+" l "+ length;
	}
}
