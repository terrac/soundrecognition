package PatternMatch;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class NameList implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public List<ITuple<String>> names = new ArrayList();

	public NameList(String b) {
		names.add(new ITuple<String>(b,1));
	}
	@Override
	public String toString() {
		return names+"";
	}

	

}
