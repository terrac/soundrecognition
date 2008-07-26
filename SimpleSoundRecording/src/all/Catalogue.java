package all;

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

import old.dir;

import main.Pattern;
import main.Recorder;
import main.linecolor;
import main.refinery;

import test.bl;

import PatternMatch.BlockCompare;
import PatternMatch.NameList;
import PatternMatch.SoundBit;
import PatternMatch.ITuple;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;
import com.sun.org.apache.regexp.internal.recompile;

import compare.compare;
import compare.compareDots;

public abstract class Catalogue {
	public List<compare> compList = new ArrayList<compare>();

	public boolean accepted;
	// List<bl> blist = new ArrayList<bl>();
	// List<Integer> tdifList = new ArrayList<Integer>();
	public String testname = "";
	public int diff = 0;

	public void end(String name) {
		beforeMatching();
		diff = 0;
		// int count = 0;
		accepted = false;
		for (List<SoundBit> clist : cclist) {

			visualstuff(name, clist);

			clist.add(new SoundBit());
			clist.add(0, new SoundBit());

			accepted = true;
			List<ITuple<String>> contList = null;
			for (compare compare : compList) {
				contList = compare.compare(name, clist, contList);
				ITuple tup = null;
				for (ITuple<String> tuple : contList) {
					if (tuple.getValue().equals(new ITuple<String>(testname,0))) {
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
				if (!(b < 3 && accepted)) {
					accepted = false;
				}
				diff += b;
				printstuff(name, contList, b);
			}

			// if (!Recorder.console) {
			// System.out.println(testname);
			// System.out.println(contList);
			// System.out.println(clist);
			// count++;
			// }
		}
		// System.out.println(diff);
		lastname = name;
		cclist.clear();
		aftermatching();
	}

	protected void printstuff(String name, List<ITuple<String>> contList, int b) {
		System.out.println(b + name + testname);
		System.out.println(contList);
		
	}

	public abstract void aftermatching();

	public abstract void beforeMatching();

	protected void visualstuff(String name, List<SoundBit> clist) {
		linecolor linecolor = refinery.addlist(clist, 0);
		vheight += 1;
		Recorder.visual.save(lastname + vheight);
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

	public void addavgfreq(int num) {
		cclist.get(cclist.size() - 1).add(new SoundBit(num));

	}

	public void incrementList() {
		cclist.add(new ArrayList<SoundBit>());

	}

	int state = 0;
	List<List<SoundBit>> cclist = new ArrayList<List<SoundBit>>();

	String lastname = "";
	int vheight = 0;

}
