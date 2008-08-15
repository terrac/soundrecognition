package main;

import java.util.List;

import compare.compare;

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
	protected void printstuff(String name, List<ITuple<String>> contList,
			int b, compare compare) {
		// TODO Auto-generated method stub
	//	super.printstuff(name, contList, b, compare);
	}
	
}
