package util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Average implements Serializable{
	List<Double> numbers = new ArrayList<Double>();
	                                                   
	public void add(Double a) {
		numbers.add(a);
	}
	public Average() {
		// TODO Auto-generated constructor stub
	}
	public Average(double a) {
		add(a);
	}
	public double getAverage() {
		double b = 0;
		for (double a : numbers) {
			b += a;
		}
		return b / numbers.size();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getAverage()+"";
	}
}
