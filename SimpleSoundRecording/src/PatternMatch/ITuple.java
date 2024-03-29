package PatternMatch;

import java.io.Serializable;

public class ITuple<V> implements Comparable<ITuple>, Serializable {
	V val;
	public double in;
	public V getValue(){return val;}
	public double getInt(){return in;}
	@Override
	public int compareTo(ITuple o) {
		// TODO Auto-generated method stub
		return Double.compare(o.in ,in);
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(getValue() == null)
			return false;
		return getValue().equals(((ITuple<String>)obj).getValue());
	}
	public ITuple(V a , double b){
		val = a;
		in = b;
	}
	public ITuple(ITuple<V> a){
		val = a.val;
		in = a.in;
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