package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import PatternMatch.*;

public class compareDots implements compare {
	List<List<NameList>> sllist = new ArrayList<List<NameList>>();

	@Override
	public List compare(String name, List<SoundBit> clist,
			List<ITuple<String>> contList) {
		// TODO Auto-generated method stub

		contList = new ArrayList();
		int j = 0;
		for (SoundBit c : clist) {
			if (sllist.size() - 1 < j) {
				sllist.add(new ArrayList<NameList>());
			}
			NameList nameList = null;
			List<NameList> currentlist = sllist.get(j);
			if (currentlist.size() > c.height) {
				nameList = currentlist.get(c.height);
			} else {
				while (currentlist.size() - 1 < c.height) {
					currentlist.add(null);
				}
			}
			if (!name.equals("")) {
				if (nameList == null) {

					currentlist.add(c.height, new NameList(name));
				} else {
					int indexOf = nameList.names.indexOf(new ITuple<String>(
							name, 0));
					if (-1 == indexOf) {
						nameList.names.add(new ITuple<String>(name, 1));
					} else {
						nameList.names.get(indexOf).in++;
					}
				}

			} else {

				if (nameList != null) {
					for (ITuple<String> a : nameList.names) {
						int indexOf = contList.indexOf(a);
						if (indexOf == -1) {

							contList.add(new ITuple<String>(a.getValue(), 1));
						}

						else {
							contList.get(indexOf).in++;
						}
					}
				}
			}

			j++;
		}
		Collections.sort(contList);

		return contList;
	}

}
