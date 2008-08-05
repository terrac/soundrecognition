package compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.Highest;
import util.Lowest;
import util.LongestCommonSubsequence.DiffEntry;
import util.LongestCommonSubsequence.DiffType;

import PatternMatch.BlockCompare;
import PatternMatch.ITuple;
import PatternMatch.SoundBit;

public class comparePattern extends compare {

	Map<String, String> map = new HashMap<String, String>();

	@Override
	public List compare(String name, List<SoundBit> clist,
			List<ITuple<String>> contList) {

		int prev = 0;
		String pattern = "";
		for (SoundBit c : clist) {

			String e = "";
			if (c.height > prev) {
				e += "U";
			} else if (c.height < prev) {
				e += "D";
			} else {
				e = "M";
			}

			if (Math.abs(c.height - prev) > 3) {
				e += e;
			}
			pattern += e;
			prev = c.height;
		}

		// System.out.println(bc);
		if (!name.equals("")) {
			map.put(name, pattern);
		} else {
			List<ITuple<String>> tlist = new ArrayList<ITuple<String>>();
			for (ITuple<String> tuple : contList) {
				String a = map.get(tuple.getValue());
				util.LcsString seq = new util.LcsString(a, pattern);
				DiffType type = null;
				Highest hi = new Highest();
				for (DiffEntry<Character> entry : seq.diff()) {
					if (type != entry.getType()) {
						if (type != null) {
							
						}
						hi.calIncrement();
						type = entry.getType();
					}
					hi.increment();
				}
				tlist.add(new ITuple<String>(tuple.getValue(),hi.hcount));
//				                                                                                                                                                                                                       
			}
			Collections.sort(tlist,Collections.reverseOrder());
			return tlist;
		}
		return contList;
	}
}
