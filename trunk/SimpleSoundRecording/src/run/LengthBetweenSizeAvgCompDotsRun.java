package run;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import compare.compare;
import compare.compareDots;

import PatternMatch.Tuple;

import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;

public class LengthBetweenSizeAvgCompDotsRun implements IRun {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3430504284091319588L;
	public List<Integer> lolist = new ArrayList<Integer>();
	public List<Integer> losalist = new ArrayList<Integer>();

	compare compare = new compareDots();
	int ssizeavg, slengthbetween;

	public LengthBetweenSizeAvgCompDotsRun(compare compare) {
		this.compare = compare;
	}
	
	public LengthBetweenSizeAvgCompDotsRun() {
		// TODO Auto-generated constructor stub
	}
	
	public int execute(Recorder recorder, Map<String, Object> variables) {
		int bestle = 0;
		int bestsi = 0;
		int bestdiff = Integer.MAX_VALUE;

		List<Integer> lobelist = new ArrayList<Integer>();

		bestdiff = Integer.MAX_VALUE;
		for (int sizeavg = 40; sizeavg < 1000; sizeavg += 40) {

			for (int lengthbetween = 40; lengthbetween < 1000; lengthbetween += 40) {

				State state = new State();
				state.catalogue = new BasicCatalogue();
				ssizeavg = Math.abs(lengthbetween);
				slengthbetween = Math.abs(sizeavg);

				// setup(state);
				 compare.reset();
				state.catalogue.add("default", compare);
				state.add("default", ssizeavg, slengthbetween);
				TUtil.buildsoundfortests(recorder, state);

				int tdiff = 0;
				tdiff = Run.runtests(recorder, state);

//				System.out.println(state.rlist + " " + tdiff);
//				System.out.println(bestle + " " + bestsi + " " + bestdiff);

				if (bestdiff >= tdiff) {
					bestdiff = tdiff;
					bestle = slengthbetween;
					bestsi = ssizeavg;

					
//					if(tdiff == 2){
//						break;
//					}
					
					lolist.add(bestle);
					losalist.add(bestsi);
					lobelist.add(bestdiff);
					compare.setLengthBetween(bestle);
					compare.setSizeAverage(bestsi);
					compare.defaultName();
				}

			}

			//bestdiff = Integer.MAX_VALUE;
		}
		System.out.println(lolist);
		System.out.println(losalist);
		variables.put("lolist", lolist);
		variables.put("losalist", losalist);
		System.out.println(lobelist);
		return bestdiff;
	}

	public void setup(State state) {
		// state.add("default",lolist.get(count),losalist.get(count));
	}

	@Override
	public compare getCompare() {
		// TODO Auto-generated method stub
		return compare;
	}

	int count = 0;

	public boolean getNext() {
		if (count < lolist.size() - 1) {
			count++;
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "\n"+compare+"sizeavg " + lolist + "\nlengthbetween " + losalist;
	}

}