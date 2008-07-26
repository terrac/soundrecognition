package main;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import old.dir;


import PatternMatch.SoundBit;

public class refinery {

	public static void main(String[] args) {

		List<SoundBit> clist = new ArrayList<SoundBit>();

		List<SoundBit> slist = mergedups(clist);
		clist = slist;
	}

	public static List<SoundBit> mergedups(List<SoundBit> clist) {
		SoundBit d = null;
		List<SoundBit> slist = new ArrayList<SoundBit>();
		for (SoundBit c : clist) {
			if (d != null) {
				if (d.compare(c)) {

					continue;
				}
			}
			d = c;
			slist.add(c);
		}
		return slist;
	}

	public static List<SoundBit> refinefurther(List<SoundBit> slist) {

		SoundBit soundBit = new SoundBit();
		int tState = 1;
		int lastnum = 0;
		int len = 1;
		int highest = 0;
		List<SoundBit> clist = new ArrayList<SoundBit>();

		int lastheight = 0;
		for (SoundBit c : slist) {
			int num = c.height;

			if (tState == 0) {
				if (lastnum < num) {

				} else {
					tState = 1;
					soundBit.height = lastnum;
					clist.add(soundBit);
					soundBit = new SoundBit();
				}

			} else if (tState == 1) {
				if (lastnum > num) {

				} else {
					tState = 0;
					soundBit.height = lastnum;
					clist.add(soundBit);
					soundBit = new SoundBit();
				}
			}

			lastnum = num;
			// clist.add(c);
		}
		// if (tState == 1) {
		clist.add(new SoundBit(0));
		// }
		return clist;
	}

	public static linecolor addlist(List<SoundBit> clist, int vheight) {
		SoundBit prev = new SoundBit(0);
		linecolor linecolor = new linecolor();
		linecolor.col = new Color((int) (Math.random() * 100) + 155,
				(int) (Math.random() * 255), (int) (Math.random() * 255));
		int b = 0;
		prev = null;
		vheight +=100;
		if (clist.size() > 0) {
			prev = clist.get(0);
		}
		for (SoundBit a : clist) {
			int len = a.length();
			linecolor.lines.add(new Line2D.Double(b,
					prev.height * 10 + vheight, b + len, a.height * 10
							+ vheight));
			prev = a;
			b += len;
		}
		return linecolor;
	}

	public static List refinePatterns(List<SoundBit> slist) {

		//System.out.println(slist);
		// slist.add(0, new SoundBit());

		int lastnum = 0;
		List<Pattern> clist = new ArrayList<Pattern>();
		List<Integer> hlist = new ArrayList<Integer>();
		List<Integer> llist = new ArrayList<Integer>();
		Pattern curP = new Pattern();
		int length = 0;
		int count = 0;
		for (SoundBit c : slist) {
			int a;

			int num = c.height;
			if (curP.list.size() == 3) {
				if (curP.list.get(0) == dir.up
						&& curP.list.get(1) == dir.middle
						&& curP.list.get(2) == dir.down) {
					curP.length = llist.remove(0) + llist.remove(0) + length;
					curP.height = getHeight(hlist, 3);
					clist.add(curP);
					//System.out.println(curP);
					curP = new Pattern();

					length = 0;
					hlist.clear();
				} else if (curP.list.get(0) == dir.middle
						&& curP.list.get(1) == dir.up) {
					Pattern p = new Pattern();

					p.length = llist.remove(0);

					p.list.add(dir.middle);

					p.height = curP.height = getHeight(hlist, 1);
					hlist.remove(0);

					clist.add(p);
					curP.list.remove(0);

				} else {
					Pattern p = new Pattern();

					p.list.add(curP.list.remove(2));

					curP.height = getHeight(hlist, 2);

					hlist.remove(0);
					hlist.remove(0);

					curP.length = llist.remove(0) + llist.remove(0);
					clist.add(curP);
					curP = p;
				}

			}

			//System.out.println(c);

			int state = determinestate(num, lastnum);

			switch (state) {
			case 1:

				length = add(curP, dir.up, hlist, num, llist, length);

				break;
			case 0:

				length = add(curP, dir.middle, hlist, num, llist, length);

				break;
			case -1:
				length = add(curP, dir.down, hlist, num, llist, length);

				break;

			default:
				break;
			}
			length += c.length();
			lastnum = num;
			count++;
		}
		curP.height = getHeight(hlist, curP.list.size());
		curP.length = length;
		for (Integer integer : llist) {
			curP.length += integer;
		}
		clist.add(curP);
		return clist;
	}

	private static int getHeight(List<Integer> hlist, int a) {
		int height = 0;
		for (int i = 0; i < a; i++) {
			height = Math.max(height, hlist.get(i));
		}
		return height;
	}

	protected static int add(Pattern curP, dir a, List<Integer> hlist, int num,
			List<Integer> llist, int length) {
		if (curP.list.size() == 0 || curP.list.get(curP.list.size() - 1) != a) {
			curP.list.add(a);
			hlist.add(num);
			if (length != 0) {
				llist.add(length);
				length = 0;
			}
		}
		return length;
	}

	private static int determinestate(int c, int last) {
		int state;
		if (c == last) {
			state = 0;
		} else if (c < last) {
			state = -1;
		} else {
			state = 1;

		}
		return state;
	}

}