package PatternMatch;

public class Tuple<V,Y> implements Comparable<Tuple> {
	V val;
	Y in;
	public V getV(){return val;}
	public Y getY(){return in;}
	@Override
	public int compareTo(Tuple o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public Tuple(V a , Y b){
		val = a;
		in = b;
	}
	public Tuple() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return val +" "+ in;
	}
}