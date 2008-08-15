package run;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import catalogues.Catalogue;

import compare.compare;
import compare.compareDots;
import compare.compareblock;

import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.LTuple;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;
import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;

public class CompareRun implements IRun, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -100694517048033326L;

	public CompareRun(compare a) {
		compare = a;
	}

	compare compare;

	List<Integer> bclist = new ArrayList<Integer>();

	public int execute(Recorder recorder, Map<String, Object> variables) {
		List<Integer> tbclist = new ArrayList<Integer>();
		int bestdiff = Integer.MAX_VALUE;

		List<Integer> lolist = (List<Integer>) variables.get("lolist");
		List<Integer> losalist = (List<Integer>) variables.get("losalist");

		String out = "";
		do {
			compare.setup();
			for (int sizeavg = 40; sizeavg < 1000; sizeavg += 40) {

				for (int lengthbetween = 40; lengthbetween < 1000; lengthbetween += 40) {

					State state = new State();
					compare.reset();

					// setup(state);
					compare.reset();
					state.catalogue = new BasicCatalogue();

					state.catalogue.add("default", new compareDots());
					state.catalogue.add("default", compare);
					state.add("default", sizeavg, lengthbetween);

					TUtil.buildsoundfortests(recorder, state);

					int tdiff = 0;
					tdiff = Run.runtests(recorder, state);
					// System.out.println(tdiff);
					if (bestdiff > tdiff) {
						bestdiff = tdiff;

						out = Catalogue.printout;
						compare.setLengthBetween(lengthbetween);
						compare.setSizeAverage(sizeavg);
						compare.defaultName();
					}
				}
			}
		} while (compare.getNext());
		System.out.println(out);
		System.out.println(compare);
		return bestdiff;
	}

	int next = 0;

	@Override
	public compare getCompare() {
		// TODO Auto-generated method stub
		return compare;
	}

	public void setup(State state) {
	}

}
