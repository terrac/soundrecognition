package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import run.IRun;
import run.Run;

import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;

import PatternMatch.SoundBit;

import catalogues.Catalogue;
import catalogues.StanCata;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import compare.compare;
import compare.compareDots;
import compare.comparePattern;

public class TestPossiblePatterns extends Run {

	List<Boolean> bolist = new ArrayList<Boolean>();
	List<compare> comlist = new ArrayList();
	List<IRun> rlist = new ArrayList();

	public static void main(String[] args) {
		Recorder recorder = new Recorder(true);
		TestPossiblePatterns tpp = new TestPossiblePatterns();
		tpp.run(recorder);

	}

	protected void run(Recorder recorder) {

		String string = "list.txt";
		rlist = (List<IRun>) TUtil.load(string);

		
		for (IRun a : rlist) {
			if (a.getCompare() != null)
				comlist.add(a.getCompare());
		}
		List<IRun> blist = new ArrayList();
		int lowest = Integer.MAX_VALUE;
		int total = 0;
		for (int i = 0; i < 200; i++) {

			State state = new State();
			
			Catalogue catalogue = new StanCata();
			state.catalogue = catalogue;
			catalogue.setCompares(comlist);
			state.setCompares(comlist);
			for (IRun a : rlist) {
				a.getNext();
				a.setup(state);
			}
			for (compare a : comlist) {
				a.reset();
			}
			TUtil.buildsoundfortests(recorder, state);
			int todiff = 0;
			todiff = runtests(recorder, state);
			if (todiff < lowest) {
				lowest = todiff;
				//need to do a real copy
				blist = (List<IRun>) TUtil.deepCopy(rlist);
			}

			System.out.println(todiff);
			total += todiff;
		}
		System.out.println(total);
		System.out.println(lowest);
		System.out.println(blist);
		TUtil.save(blist, "Recorder.txt");
		System.exit(0);
	}

	

}
