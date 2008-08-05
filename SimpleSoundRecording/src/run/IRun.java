package run;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import compare.compare;

import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;
import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;

public interface IRun extends Serializable {

	

	public int execute(Recorder recorder,Map<String,Object> variables);
	
	public compare getCompare();
	public void setup(State state);

	public boolean getNext();

}