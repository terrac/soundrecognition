package old;
import java.util.ArrayList;
import java.util.List;



public class Comparison {
	List<Integer> avgFreq = new ArrayList<Integer>();
	double average;
	String name = "undefined";
	int highestPos = 0;

	public Comparison(List<Integer> pfreqPos, String pname) {
		avgFreq = pfreqPos;
		average = average(avgFreq);
		name = pname;
		highestPos = getHighestPos();
	}

	public int getHighestPos() {
		int b = 0;
		int c = 0;
		int d = 0;
		for (int a : avgFreq) {
			if (c < a) {
				c = a;
				d = b;
			}
			b++;
		}
		return d;
	}

	public double compare(Comparison a) {
		double diff = Math.abs(a.avgFreq.size() - avgFreq.size());
		
		//scale by a.highest and highest
		int b = highestPos;
		int c = highestPos;
		int d = highestPos;
		int e = highestPos;
		while (true) {
			boolean e2 = a.avgFreq.size() > c&&avgFreq.size() > e;
			if (e2) {
				diff += Math.abs(a.avgFreq.get(c) - avgFreq.get(e));
			}
			boolean f = a.avgFreq.size() < b&&avgFreq.size() < d;
			if (f) {
				diff += Math.abs(a.avgFreq.get(b) - avgFreq.get(d));
			}
			if(!e2&&!f){
				break;
			}
			c++;
			b--;
			d--;
			e++;
		}
		return diff;

	}

	public double compare01(Comparison a) {
		double diff = Math.abs(a.avgFreq.size() - avgFreq.size());
		// diff += diff * 10;
		if (a.avgFreq.size() > avgFreq.size() * 2) {
			diff = Integer.MAX_VALUE;
		}
		List<List<Integer>> list = split(a.avgFreq, 4);
		// for(List<Integer> b : list){
		// scale(a.average, b);
		// }
		diff += findandcompare(list, avgFreq);

		return diff;

	}

	private void scale(double paverage, List<Integer> sub) {
		for (int b = 0; b < sub.size(); b++) {
			sub.set(b, (int) (sub.get(b) * average / paverage));
		}
	}

	public static double average(List<Integer> a) {
		double c = 0;
		for (int b : a) {
			c += b;
		}
		return c / a.size();
	}

	public static List<List<Integer>> split(List<Integer> a, int numofparts) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		int size = a.size() / numofparts;
		for (int b = 0; b < numofparts; b++) {
			int i = (b + 1) * size;
			if (i > a.size()) {
				i = a.size() - 1;
			}
			list.add(a.subList(b * size, i));
		}
		return list;
	}

	public static double findandcompare(List<List<Integer>> list,
			List<Integer> a) {
		double diff = 0;

		for (List<Integer> sub : list) {

			diff += subcompare(sub, a);
		}

		return diff;
	}

	private static double subcompare(List<Integer> subA, List<Integer> totalB) {
		int lowestDiff = Integer.MAX_VALUE;
		int index = -1;
		for (int b = 0; b < totalB.size(); b++) {

			int d = 0;
			int diff = 0;
			for (int c : subA) {
				if (b + d >= totalB.size()) {
					diff = Integer.MAX_VALUE;
					break;
				}
				diff += Math.abs(c - totalB.get(b + d));

				d++;
			}
			if (diff < lowestDiff) {
				index = b;
				lowestDiff = diff;
			}
		}

		return lowestDiff;
	}
}
