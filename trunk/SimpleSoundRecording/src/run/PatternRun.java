package run;

import java.util.Map;

import main.Recorder;
import main.State;

import compare.compare;
import compare.comparePattern;

public class PatternRun implements IRun {

	comparePattern compPattern = new comparePattern();
	@Override
	public int execute(Recorder recorder, Map<String, Object> variables) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public compare getCompare() {
		// TODO Auto-generated method stub
		return compPattern;
	}

	@Override
	public boolean getNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setup(State state) {
		// TODO Auto-generated method stub

	}

	

}
