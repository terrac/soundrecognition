package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.Pattern;
import main.TUtil;
import main.dir;
import main.refinery;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;

import sun.security.x509.AVA;
import util.Average;
import util.Highest;
import util.LongestCommonSubsequence.DiffEntry;
import util.LongestCommonSubsequence.DiffType;

import PatternMatch.*;

public class compareUpDownMiddle extends compare {

	Map<String, List<Pattern>> hMap = new HashMap();

	public void reset() {
		hMap.clear();

	}

	private static final long serialVersionUID = 1L;

	@Override
	public List compare(String name, List<SoundBit> clist,
			List<ITuple<String>> contList) {
		List<Pattern> refinePatterns = refinery.refinePatterns(clist);
		List<ITuple<String>> tlist = new ArrayList<ITuple<String>>();
		if (!name.equals("")) {
			hMap.put(name, refinePatterns);
		} else {
			for (ITuple<String> tuple : contList) {
				List<Pattern> a = hMap.get(tuple.getValue());
				int count = 0;
				for (int i = 0; i < a.size()&& i < refinePatterns.size(); i++) {
//					if(a.get(i).equals(refinePatterns))
					if(a.get(i).list.get(0).equals(refinePatterns.get(i).list.get(0))){
						count++;
					}
				}
				System.out.println(count);
				tuple.in = count;
				// DiffType type = null;
				// Highest hi = new Highest();
				// for (DiffEntry<Pattern> entry : seq.diff()) {
				// if (type != entry.getType()) {
				// if (type != null) {
				//						
				// }
				// hi.calIncrement();
				// type = entry.getType();
				// }
				// hi.increment();
				// }
				// tlist.add(new ITuple<String>(tuple.getValue(),hi.hcount));
				//			                                                          
				//System.out.println(seq.getHtmlDiff());
			}
		}
		Collections.sort(tlist);
		return contList;
	}

	@Override
	public double getSignificance() {
		// TODO Auto-generated method stub
		return 1.1;
	}
}
