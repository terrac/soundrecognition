package run;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import compare.compare;
import compare.compareDots;

import main.Recorder;
import main.TUtil;
import PatternMatch.Tuple;
import all.State;
import catalogues.Catalogue;
import catalogues.PCatalogue;

public class CountLengthRun implements IRun{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8760435814781653078L;
	int scountlen = 0;
	public int execute(Recorder recorder,Map<String,Object> variables) {
		int lowest = Integer.MAX_VALUE;
		int countlen = 0;
		
		List<Integer> clist = new ArrayList<Integer>();
		for (int i = 1000; i < 10000; i+= 100) {

			State state = new State();
			state.lblist.add(40);
			state.salist.add(40);
			scountlen = i;
			setup(state);
			Catalogue catalogue = new PCatalogue();
			state.catalogue = catalogue;
			state.catalogue.compList.add(new compareDots());

			TUtil.buildsoundfortests(recorder, state);
			
			int todiff = Run.runtests(recorder, state, catalogue);
			
			if (todiff < lowest) {
				lowest = todiff;
				System.out.println(todiff);
				System.out.println(lowest);
				System.out.println(i);
				countlen =i;

			}
			System.out.println(todiff);
			System.out.println(i);
		}
		clist.add(countlen);
		
		System.out.println(lowest);
		System.out.println(countlen);
		return 0;
	}
	public void setup(State state) {
		state.countlength = scountlen;
	}
	@Override
	public compare getCompare() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getNext() {
		return false;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "countlen "+scountlen;
	}
}
