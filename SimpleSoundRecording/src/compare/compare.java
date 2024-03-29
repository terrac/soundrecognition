package compare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import run.LengthBetweenSizeAvgCompDotsRun;

import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.ITuple;

public abstract class compare implements Serializable {
	private static final long serialVersionUID = -1723980345464253483L;

	public abstract List compare(String name,
			List<PatternMatch.SoundBit> clist, List<ITuple<String>> contList);

	public double getSignificance() {
		return significance;
	}

	double significance = 1;

	public void setSignificance(double significance) {
		this.significance = significance;
	}

	public int getLengthBetween() {
		return lengthBetween;
	}

	public int getSizeAverage() {
		return sizeAverage;
	}

	public void setLengthBetween(int lengthBetween) {
		this.lengthBetween = lengthBetween;
	}

	public void setSizeAverage(int sizeAverage) {
		this.sizeAverage = sizeAverage;
	}

	int lengthBetween = 0;
	int sizeAverage = 0;
	String name;
	{
		name = this.getClass().getSimpleName();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void defaultName() {
		this.name = getLengthBetween() + " " + getSizeAverage();
	}

	public void reset() {
		throw new RuntimeException("no reset");
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	public boolean getNext(){
		return false;
	}
	public void setup(){
		
	}
}
