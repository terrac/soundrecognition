package PatternMatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class NameList {


	public List<ITuple<String>> names = new ArrayList();

	public NameList(String b) {
		names.add(new ITuple<String>(b,1));
	}
	@Override
	public String toString() {
		return names+"";
	}

	

}
