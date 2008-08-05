package compare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.ITuple;

public abstract class compare implements Serializable {

	public abstract List compare(String name, List<PatternMatch.SoundBit> clist,
			List<ITuple<String>> contList);

	public double getSignificance(){
		return significance;
	}
	double significance = 1;
	public void setSignificance(double significance) {
		this.significance = significance;
	}
	public int getLengthBetween(){
		return 0;
	}
	public int getSizeAverage(){
		return 0;
	}
	String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
