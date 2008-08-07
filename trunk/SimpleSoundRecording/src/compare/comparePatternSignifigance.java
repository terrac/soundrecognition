package compare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import util.Highest;
import util.Lowest;
import util.LongestCommonSubsequence.DiffEntry;
import util.LongestCommonSubsequence.DiffType;

import PatternMatch.BlockCompare;
import PatternMatch.ITuple;
import PatternMatch.SoundBit;

public class comparePatternSignifigance extends compare {

	Map<String, String> map = new HashMap<String, String>();

	Map<String, Set<String>> smap = new HashMap();

	public void reset() {
		smap.clear();
		map.clear();
	}

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

			Set<String> set = new HashSet<String>();
			if (map.containsKey(name)) {
				String string = map.get(name);
				for (int i = 0; i < pattern.length(); i++) {
					for (int j = 0; j < pattern.length(); j++) {
						int k = i + j;
						if (k >= pattern.length()) {
							continue;
						}
						String a = pattern.substring(i, k);

						if (string.contains(a)) {
							set.add(a);
						}
					}
				}
			}
			if (!smap.containsKey(name)) {
				smap.put(name, set);
			} else {
				Set<String> cur = smap.get(name);

				// can't modify while iterating
				List<String> remove = new ArrayList<String>();
				for (String string : cur) {
					if (!set.contains(cur)) {
						remove.add(string);
					}
				}
				for (String string2 : remove) {
					cur.remove(string2);
				}
			}
			map.put(name, pattern);

		} else {
			List<ITuple<String>> tlist = new ArrayList<ITuple<String>>();
			for (ITuple<String> tuple : contList) {
				Set<String> a = smap.get(tuple.getValue());
				boolean flag = true;
				for (String string3 : a) {
					if (!pattern.contains(string3)) {
						flag = false;
					}
				}
				if (flag) {
					tlist.add(tuple);
				}
			}
			Collections.sort(tlist, Collections.reverseOrder());
			return tlist;
		}
		return contList;
	}

	@Override
	public double getSignificance() {
		// TODO Auto-generated method stub
		return 5;
	}
}
