package compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import PatternMatch.*;

public class compareblock implements compare	 {
	public List<Block> block = new ArrayList<Block>();
	Map<String, BlockCompare> bmap = new HashMap<String, BlockCompare>();
	
	public List compare(String name, List<PatternMatch.SoundBit> clist,
			List<ITuple<String>> contList) {
		
		BlockCompare bc = new BlockCompare();
		bc.execute(clist,block);
		
		//System.out.println(bc);
		if (!name.equals("")) {
			bmap.put(name, bc);
		} else {
			ITuple e = null;
			List d = new ArrayList();
			for (ITuple<String> cont : contList) {
				
				
				final BlockCompare a = bmap.get(cont.getValue());
				
				
				final int c = a.compare(bc);
				final String fna = cont.getValue();
				ITuple<String> tuple = new ITuple<String>() {
	
					@Override
					public String getValue() {
						// TODO Auto-generated method stub
						return fna;
					}
	
					@Override
					public int getInt() {
						// TODO Auto-generated method stub
						return c;
					}
	
					@Override
					public int compareTo(ITuple o) {
						// TODO Auto-generated method stub
						return c - o.getInt();
					}
	
					@Override
					public String toString() {
						// TODO Auto-generated method stub
						return fna+" "+c;
					}
	
				};
				
				d.add(tuple);
	
			}
			Collections.sort(d);

			return d;
		}
		return contList;
	}
	

}
