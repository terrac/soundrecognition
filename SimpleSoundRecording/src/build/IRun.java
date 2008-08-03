package build;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import compare.compare;

import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;
import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;
import all.State;

public interface IRun extends Serializable {

	

	public int execute(Recorder recorder);
	
	public compare getCompare();
	public void setup(State state);

	public void generateRandom();

}