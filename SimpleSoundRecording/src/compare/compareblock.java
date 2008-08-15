package compare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import PatternMatch.*;

public class compareblock extends compare implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = -1292260982656897090L;
	public List<Block> block = new ArrayList<Block>();
	Map<String, BlockCompare> bmap = new HashMap<String, BlockCompare>();

	public void reset() {
		// block.clear();
		bmap.clear();
	}

	compareDots dots = new compareDots();

	public List compare(String name, List<PatternMatch.SoundBit> clist,
			List<ITuple<String>> contList) {
		if (contList == null) {

			contList = dots.compare(name, clist, contList);

		}
		BlockCompare bc = new BlockCompare();
		bc.execute(clist, block);

		// System.out.println(bc);
		if (!name.equals("")) {
			bmap.put(name, bc);
		} else {
			ITuple e = null;
			List d = new ArrayList();
			for (ITuple<String> cont : contList) {

				final BlockCompare a = bmap.get(cont.getValue());

				final int c = a.blocksFound.size();
				final String fna = cont.getValue();
				ITuple<String> tuple = new ITuple<String>() {

					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return fna;
					}

					@Override
					public double getInt() {
						// TODO Auto-generated method stub
						return c;
					}

					@Override
					public int compareTo(ITuple o) {
						// TODO Auto-generated method stub
						return Double.compare(c, o.getInt());
					}

					@Override
					public String toString() {
						// TODO Auto-generated method stub
						return fna + " " + c;
					}

				};

				d.add(tuple);

			}
			Collections.sort(d, Collections.reverseOrder());
			return d;
		}
		return contList;
	}

	int increment = 1;
	double bottom = 3;

	public void setup() {
		block.clear();

		for (int i = increment; i < bottom; i += increment) {
			for (int j = increment; j < bottom; j += increment) {
				final int c = i;
				final int b = j;
				block.add(new Block() {

					@Override
					public boolean execute(List<SoundBit> a, BlockCompare x,
							int highest) {
						z = (int) (c);
						y = (int) (b);
						int v = z - increment;
						int u = y - increment;

						double upper = y / bottom * highest;
						double downer = u / bottom * highest;
						double lefter = z / bottom * a.size();
						int righter = (int) (v / bottom * a.size());
						boolean flag = false;
						for (int k = (int) downer; k < upper; k++) {
							for (int l = righter; l < lefter; l++) {
								if (a.get(l).height == k) {
									flag = true;
								}
							}
						}

						return flag;
					}
				});
			}
		}
	}
}