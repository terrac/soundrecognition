package build;

import java.util.ArrayList;
import java.util.List;

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

public class BlockCompareRun implements IRun{
	static final int top = 10;
	
	int bottom;
	BlockCompare bc;
	compareblock compare = new compareblock();
	List<Integer> bclist = new ArrayList<Integer>();
	public int execute(Recorder recorder) {
		List<Integer> tbclist = new ArrayList<Integer>();	
		int bestdiff = Integer.MAX_VALUE;
	
		bestdiff = Integer.MAX_VALUE;
	
		for (int w = 0; w < top; w++) {
			bottom = w;
			setup();
			State state = new State();
			state.lblist.add(40);
			state.salist.add(40);
			state.catalogue = new BasicCatalogue();
			state.catalogue.compList.add(new compareDots());
			state.catalogue.compList.add(compare);
			TUtil.buildsoundfortests(recorder, state);
	
			int tdiff = 0;
			tdiff = Run.runtests(recorder, state, tdiff);
	
			if (bestdiff > tdiff) {
				bestdiff = tdiff;
				bclist.add(w);
			}
	
			bestdiff = Integer.MAX_VALUE;
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
						z = (int) (c * x.height * .10 * (top-bottom));
						y = (int) (b * x.length * .10 * (top-bottom));
						if (a.size() <= c) {
							return false;
						}
						boolean d = a.get(z).height == y;
						return d;
					}
				});
			}
		}
	}


	public void generateRandom() {
		bottom = bclist.get((int) (Math.random()* bclist.size()));
	}


	@Override
	public compare.compare getCompare() {
		// TODO Auto-generated method stub
		return compare;
	}


	public void setup(State state) {
	}

}
