package main;

import java.util.List;

import PatternMatch.SoundBit;
import PatternMatch.ITuple;
import all.Catalogue;

public class BasicCatalogue extends Catalogue{

	public void beforeMatching() {
		//System.out.println("before");
	}

	public void aftermatching() {
	//	System.out.println("after");
	}

	@Override
	protected void visualstuff(String name, List<SoundBit> clist) {
		// TODO Auto-generated method stub
		// super.visualstuff(name, clist);
	}

	
}
