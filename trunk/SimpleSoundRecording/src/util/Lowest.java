package util;

public class Lowest {
	
	int lcount = 0;
	public Lowest() {

	}

	public void calPassed(int a) {
		if(a > lcount){
			lcount = a;
		}

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "lowest "+lcount;
	}
}
