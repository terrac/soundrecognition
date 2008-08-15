package catalogues;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;


import main.Pattern;
import main.Recorder;
import main.dir;
import main.linecolor;
import main.refinery;

import test.bl;

import PatternMatch.BlockCompare;
import PatternMatch.NameList;
import PatternMatch.SoundBit;
import PatternMatch.ITuple;
import PatternMatch.Tuple;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.org.apache.regexp.internal.recompile;

import compare.compare;
import compare.compareDots;

public abstract class Catalogue {
	public Map<String, List<compare>> compMap = new HashMap<String, List<compare>>();

	public boolean accepted;
	// List<bl> blist = new ArrayList<bl>();
	// List<Integer> tdifList = new ArrayList<Integer>();
	public String testname = "";
	public int diff = 0;
	public static String printout = "";
	public void end(String name) {
		beforeMatching();
		diff = 0;
		// int count = 0;
		accepted = false;
		printout += "\n"+testname+name;
		List<ITuple<String>> contList = null;
		List<ITuple<String>> guessList = new ArrayList<ITuple<String>>();
		for (String key : ccMap.keySet()) {
			List<SoundBit> clist = ccMap.get(key);
			visualstuff(name, clist);

			clist.add(new SoundBit());
			clist.add(0, new SoundBit());

			accepted = true;
			
			for (compare compare : compMap.get(key)) {
				contList = compare.compare(name, clist, contList);
				int b = buildDiffForTests(contList);
				printout+=testname +" "+b;
				for (int i = 0; i < 3 && i < contList.size(); i++) {
					ITuple<String> tuple = contList.get(i);
					int a = guessList.indexOf(tuple);

					if (a != -1) {
						guessList.get(a).in += i - compare.getSignificance();
					} else {
						guessList.add(new ITuple(tuple.getValue(), i
								- compare.getSignificance()));
					}
				}
				 printstuff(name, contList, b, compare);

			}

			Collections.sort(guessList, Collections.reverseOrder());
			diff = 0;
			buildDiffForTests(guessList);
			if (testname.equals("")) {
				diff = 0;
			} else{
				//System.out.println(diff + " " + testname + " " + guessList);
			}
			//System.out.println(guessList);
			// if (!Recorder.console) {
			// System.out.println(testname);
			// System.out.println(contList);
			// System.out.println(clist);
			// count++;
			// }
		}
		// System.out.println(diff);

		lastname = name;
		ccMap.clear();
		aftermatching();
	}

	protected int buildDiffForTests(List<ITuple<String>> contList) {
		ITuple tup = null;
		for (ITuple<String> tuple : contList) {
			if (tuple.getValue().equals(testname)) {
				tup = tuple;
			}
		}
		int b = -1;
		if (tup != null) {
			b = contList.indexOf(tup);
		}
		if (b == -1) {
			b = 10;
		}

		diff += b;
		return b;
	}

	protected void printstuff(String name, List<ITuple<String>> contList,
			int b, compare compare) {
		if (name.equals("")) {
			System.out.println(b + " " + testname);
			System.out.println(compare.getClass().getSimpleName() + " "
					+ contList);
		}
	}

	public abstract void aftermatching();

	public abstract void beforeMatching();

	protected void visualstuff(String name, List<SoundBit> clist) {
		if (Recorder.getVisual() == null)
			return;
		linecolor linecolor = refinery.addlist(clist, 0);
		vheight += 1;
		Recorder.getVisual().save(lastname + vheight);
		Recorder.visual.linecols.clear();
		if (!lastname.equals(name) || name.equals("")) {

			vheight = 0;
		}
		Recorder.visual.linecols.add(linecolor);
		Recorder.visual.repaint();
		SoundBit d = new SoundBit(0);
		List<Pattern> refinePatterns = refinery.refinePatterns(clist);
		Recorder.visual.textOutput.clear();
		for (Object object : refinePatterns) {
			Recorder.visual.textOutput.add(object + "\n");
		}
	}

	public void addavgfreq(int num, String lname) {
		if(!ccMap.containsKey(lname)){
			ccMap.put(lname, new ArrayList<SoundBit>());
		}
		ccMap.get(lname).add(new SoundBit(num));

	}

	int state = 0;
	public Map<String, List<SoundBit>> ccMap = new HashMap();

	String lastname = "";
	int vheight = 0;

	public void setCompares(List<compare> comlist) {
		for (compare a : comlist) {
			if (!compMap.containsKey(a.getName())) {
				compMap.put(a.getName(), new ArrayList<compare>());
			}
			compMap.get(a.getName()).add(a);
		}
	}

	public void add(String a, compare b) {
		if (!compMap.containsKey(a)) {
			compMap.put(a, new ArrayList<compare>());
		}
		compMap.get(a).add(b);
	}
}
