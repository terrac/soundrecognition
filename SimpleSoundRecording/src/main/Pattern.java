package main;

import java.util.ArrayList;



public class Pattern {
	public java.util.List<dir> list = new ArrayList<dir>();
	
	int height;
	int length;
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return list.toString() + " h "+height+" l "+ length;
	}
	
	@Override
	public boolean equals(Object obj) {
		Pattern c = (Pattern) obj;
		int b = 0;
		if(c.list.size() != list.size()){
			return false;
		}
		for (dir a : list) {
			if(!a.equals(c.list.get(b)))
				return false;
			b++;
		}
		return true;
	}
}
