package run;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import compare.compareDots;
import compare.compareblock;

import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;
import all.State;
import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;

public class BlockCompareRun implements IRun, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -100694517048033326L;

	static final int top = 10;

	int bottom;
	BlockCompare bc;
	compareblock compare = new compareblock();
	List<Integer> bclist = new ArrayList<Integer>();


	public int execute(Recorder recorder,Map<String,Object> variables) {
		List<Integer> tbclist = new ArrayList<Integer>();
		int bestdiff = Integer.MAX_VALUE;

		bestdiff = Integer.MAX_VALUE;

		List<Integer> lolist = (List<Integer>) variables.get("lolist");
		List<Integer> losalist =  (List<Integer>) variables.get("losalist");
		for (int w = 0; w < top; w++) {
			bottom = w;
			setup();
			for (int i = 0; i < lolist.size(); i++) {

				State state = new State();
				state.lblist.add(lolist.get(i));
				state.salist.add(losalist.get(i));
				state.catalogue = new BasicCatalogue();
				state.catalogue.compList.add(new compareDots());
				state.catalogue.compList.add(compare);
				TUtil.buildsoundfortests(recorder, state);

				int tdiff = 0;
				tdiff = Run.runtests(recorder, state);

				if (bestdiff > tdiff) {
					bestdiff = tdiff;
					bclist.add(w);
					compare.setLengthBetween(lolist.get(i));
					compare.setSizeAverage(losalist.get(i));
				}
			}
			
		}
		return bestdiff;
	}

	public void setup() {

		for (int i = 0; i < bottom; i += 1) {
			for (int j = 0; j < bottom; j += 1) {
				final int c = i;
				final int b = j;
				compare.block.add(new Block() {
					@Override
					public boolean execute(List<SoundBit> a, BlockCompare x) {
						z = (int) (c * x.height * .10 * (top - bottom));
						y = (int) (b * x.length * .10 * (top - bottom));
						if (a.size() <= c) {
							return false;
						}
						if (a.size() <= z) {
							return false;
						}
						boolean d = a.get(z).height == y;
						return d;
					}
				});
			}
		}
	}

	int next = 0;

	public boolean getNext() {

		if (next == bclist.size()) {
			return false;
		} else {
			bottom = bclist.get(next);
			next++;
			return true;
		}
	}

	@Override
	public compare.compare getCompare() {
		// TODO Auto-generated method stub
		return compare;
	}

	public void setup(State state) {
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "bottom " + bottom;

	}
}
