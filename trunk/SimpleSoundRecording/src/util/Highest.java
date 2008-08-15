package util;

public class Highest {
	int count = 0;
	public int hcount = 0;
	public Highest() {
		// TODO Auto-generated constructor stub
	}
	public void calIncrement() {
		if(count > hcount){
			hcount = count;
		}
		count = 0;

	}
	public void calPassed(int a) {
		if(a > hcount){
			hcount = a;
		}
		count = 0;

	}
	public Object c;
	public void calPassed(int a, Object b) {
		if(a > hcount){
			hcount = a;
			c = b;
		}
		count = 0;

	}
	public void increment(){
		count++;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "highest "+hcount;
	}
}
