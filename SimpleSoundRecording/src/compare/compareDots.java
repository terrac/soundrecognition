package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.TUtil;

import com.sun.xml.internal.ws.api.addressing.AddressingVersion;

import sun.security.x509.AVA;
import util.Average;

import PatternMatch.*;

public class compareDots extends compare {

	Map<Integer, Map<String, Average>> hMap = new HashMap();

	public void reset() {
		hMap.clear();

	}

	private static final long serialVersionUID = 1L;

	@Override
	public List compare(String name, List<SoundBit> clist,
			List<ITuple<String>> contList) {

		contList = new ArrayList();
		Map<Integer, Integer> aMap = new HashMap();
		for (SoundBit c : clist) {

			if (!aMap.containsKey(c.height))
				aMap.put(c.height, 1);
			else
				aMap.put(c.height, aMap.get(c.height) + 1);
		}
		if (!name.equals("")) {
			for (Integer a : aMap.keySet()) {
				if (!hMap.containsKey(a)) {
					hMap.put(a, new HashMap());
				}
				Map<String, Average> map = hMap.get(a);
				if (map.containsKey(name)) {
					map.get(name).add((double) aMap.get(a));
				} else {

					map.put(name, new Average(aMap.get(a)));
				}

			}

		} else {

			for (Integer a : aMap.keySet()) {
				if (!hMap.containsKey(a)) {
					hMap.put(a, new HashMap());
				}
				Map<String, Average> map = hMap.get(a);
				for (String b : map.keySet()) {
					double c = TUtil.within(map.get(b).getAverage(), aMap
							.get(a));
					// if(c < 10){
					int indexOf = contList.indexOf(new ITuple<String>(b, 0));
					if (indexOf == -1) {
						contList.add(new ITuple<String>(b, c));
					} else {
						contList.get(indexOf).in += c;
					}
					// }
				}

			}
		}
		Collections.sort(contList );

		return contList;
	}

	@Override
	public double getSignificance() {
		// TODO Auto-generated method stub
		return 1.1;
	}
}
