package run;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;

import compare.compare;
import compare.compareDots;
import compare.comparePattern;
import compare.comparePatternSignifigance;

public class PatternRun implements IRun {

	comparePatternSignifigance compPattern = new comparePatternSignifigance();

	@Override
	public int execute(Recorder recorder, Map<String, Object> variables) {
		// List<Integer> tbclist = new ArrayList<Integer>();
		int bestdiff = Integer.MAX_VALUE;

		bestdiff = Integer.MAX_VALUE;

		List<Integer> lolist = (List<Integer>) variables.get("lolist");
		List<Integer> losalist = (List<Integer>) variables.get("losalist");

		for (int i = 0; i < lolist.size(); i++) {

			State state = new State();
			state.add("default", lolist.get(i), losalist.get(i));
			state.catalogue = new BasicCatalogue();
			state.catalogue.add("default", new compareDots());
			state.catalogue.add("default", compPattern);
			TUtil.buildsoundfortests(recorder, state);

			int tdiff = 0;
			tdiff = Run.runtests(recorder, state);

			if (bestdiff > tdiff) {
				bestdiff = tdiff;

				compPattern.setLengthBetween(lolist.get(i));
				compPattern.setSizeAverage(losalist.get(i));
				compPattern.defaultName();
			}
		}

		return bestdiff;
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
