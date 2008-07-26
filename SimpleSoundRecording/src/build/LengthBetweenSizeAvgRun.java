package build;

import java.util.ArrayList;
import java.util.List;

import compare.compare;

import PatternMatch.Tuple;
import all.State;

import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;

public class LengthBetweenSizeAvgRun implements IRun {


	List<Integer> lolist = new ArrayList<Integer>();
	List<Integer> losalist = new ArrayList<Integer>();	

	int ssizeavg, slengthbetween;
	public int execute(Recorder recorder) {
		int bestle = 0;
		int bestsi = 0;
		int bestdiff = Integer.MAX_VALUE;

		List<Integer> lobelist = new ArrayList<Integer>();

		bestdiff = Integer.MAX_VALUE;
		for (int sizeavg = 1; sizeavg < 1000; sizeavg += 40) {

			for (int lengthbetween = 1; lengthbetween < 1000; lengthbetween += 40) {

				State state = new State();
				state.catalogue = new BasicCatalogue();
				ssizeavg = Math.abs(lengthbetween);
				slengthbetween = Math.abs(sizeavg);
				
				setup(state);

				TUtil.buildsoundfortests(recorder, state);

				int tdiff = 0;
				tdiff = Run.runtests(recorder, state, tdiff);

				System.out.println(state.lblist + " " + state.salist + " "
						+ tdiff);
				System.out.println(bestle + " " + bestsi + " " + bestdiff);

				if (bestdiff > tdiff) {
					bestdiff = tdiff;
					bestle = state.lengthbetween;
					bestsi = state.sizeavg;
				}

			}

			lolist.add(bestle);
			losalist.add(bestsi);
			lobelist.add(bestdiff);
			bestdiff = Integer.MAX_VALUE;
		}
		System.out.println(lolist);
		System.out.println(losalist);
		System.out.println(lobelist);
		return 0;
	}

	public void setup(State state) {
		state.lblist.add(ssizeavg);
		state.salist.add( slengthbetween);
	}

	@Override
	public compare getCompare() {
		// TODO Auto-generated method stub
		return null;
	}

	public void generateRandom() {
	}

}