package main;

import java.util.List;

import catalogues.Catalogue;

import PatternMatch.SoundBit;
import PatternMatch.ITuple;

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
