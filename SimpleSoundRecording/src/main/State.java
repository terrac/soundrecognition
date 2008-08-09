package main;

import java.awt.Color;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;

import old.Comparison;

import PatternMatch.LTuple;
import catalogues.Catalogue;

import com.sun.org.apache.regexp.internal.recompile;
import compare.compare;

public class State {

	public Catalogue catalogue;


	public List<LTuple> rlist = new ArrayList();

	public int precision = 1000;
	public int countlength = 9900;
	int dfreq = 0;
	int freq = 0;
	int localHigh = 0;
	int addcount = 0;

	List<Integer> freqPos = new ArrayList<Integer>();
	List<Integer> avgFreq = new ArrayList<Integer>();

	public void addBytes(byte[] data, String name) {

		int state;

		if (addcount > countlength && startline && localHigh < 5) {
			end(name);
		}

		for (int b = 0; b < data.length; b += 1) {
			addcount++;
			freq++;

			byte c = data[b];

			state = determinestate(c);

			switch (state) {
			case 1:

				if (dfreq == 0) {
					dfreq++;
				}
				if (dfreq == 1) {
					if (c > localHigh) {
						localHigh = c;
					}

				}

				break;
			case 0:

				if (dfreq == 1) {
					if (addcount < countlength) {
						addcount = 0;

					}

					freqPos.add(freq);
					if (!startline && localHigh > 5) {
						start();
					}
					// System.out.println(i);

					dfreq++;
				}
				break;
			case -1:

				if (dfreq == 2) {
					dfreq = 0;
					localHigh = 0;

				}
				break;

			default:
				break;
			}
		}
		for (int i = 0; i < rlist.size(); i++) {
			
			int lengthbetween = rlist.get(i).getLengthBetween();
			int sizeavg = rlist.get(i).getSizeAverage();
			
			if(lengthbetween == 0||sizeavg ==0){
				throw new RuntimeException("cannot be 0"+rlist);
			}
			
			int y = 0;

			List<Integer> tfpos = new ArrayList<Integer>();

			int t = 0;

			for (int z = freq - data.length; z < freq && startline; z += lengthbetween) {

				while (freqPos.size() > t && z + sizeavg > freqPos.get(t)) {
					tfpos.add(freqPos.get(t));
					t++;
				}
				while (tfpos.size() > 0 && z - sizeavg > tfpos.get(0)) {
					tfpos.remove(0);
				}

				if (tfpos.size() > 0)
					avgFreq.add(tfpos.size());

				y += 3;

				if (tfpos.size() < 5 && avgFreq.size() > 0) {
					int avg = 0;
					for (int a : avgFreq) {
						avg += a;
					}

					avg = avg / avgFreq.size();
					catalogue.addavgfreq(avg,rlist.get(i).getName());
					// System.out.println(avg);
					avgFreq.clear();
				}
			}
		}
		// setAvg(y, v);

		// System.out.println(avgfreq*3);

	}

	public void start() {
		catalogue.ccMap.clear();

		startline = true;
		avgFreq = new ArrayList<Integer>();
		freqPos = new ArrayList<Integer>();
		
		addcount = 0;
		freq = 0;
	}

	public void end(String name) {
		// if (startline)
		// System.out.println(simpleRep);
		startline = false;

		catalogue.end(name);

	}

	boolean startline = false;

	private int determinestate(byte c) {
		int state;
		if (c == 0) {
			state = 0;
		} else if (c < 0) {
			state = -1;
		} else {
			state = 1;

		}
		return state;
	}

	public void add(Object ...objects ){
		rlist.add(new LTuple(objects));
	}
	public void setCompares(List<compare> comlist) {
		for (compare a : comlist) {
			add(a.getName(),a.getLengthBetween(),a.getSizeAverage());
		}
	}
}
