package PatternMatch;

public class LTuple  {
	public Object[] val;
	
	
	public LTuple(Object... values){
		if(values.length != 3){
			throw new RuntimeException();
		}
		val = values;
	}
	public String getName(){
		return (String) val[0];
	}
	public int getLengthBetween(){
		return (Integer) val[1];
	}
	public int getSizeAverage(){
		return (Integer) val[2];
	}
	public LTuple() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val.toString();
	}
}