package PatternMatch;

public class ITuple<V> implements Comparable<ITuple> {
	V val;
	public int in;
	public V getValue(){return val;}
	public int getInt(){return in;}
	@Override
	public int compareTo(ITuple o) {
		// TODO Auto-generated method stub
		return o.in -in;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return val.equals(((ITuple<String>)obj).getValue());
	}
	public ITuple(V a , int b){
		val = a;
		in = b;
	}
	public ITuple() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val +" "+ in;
	}
}