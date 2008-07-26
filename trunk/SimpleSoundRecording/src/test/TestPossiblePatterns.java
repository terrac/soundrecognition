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

import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;

import PatternMatch.SoundBit;
import all.Catalogue;
import all.State;
import build.IRun;
import build.Run;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import compare.compare;

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
		int bestle = 0;
		int bestsi = 0;
		int bestdiff = Integer.MAX_VALUE;
		int pdiff = 0;

		List<Integer> lolist = new ArrayList<Integer>();
		List<Integer> losalist = new ArrayList<Integer>();
		List<Integer> lobelist = new ArrayList<Integer>();
		try {
			// Deserialize from a file
			File file = new File("filename.ser");
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(
					"list.txt"));
			// Deserialize the object
			rlist = (List<IRun>) in.readObject();
			in.close();

		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
		}

		for (IRun a : rlist) {
			if (a.getCompare() != null)
				comlist.add(a.getCompare());
		}
		int lowest = Integer.MAX_VALUE;
		for (int i = 0; i < 200; i++) {

			State state = new State();

			Catalogue catalogue = new BasicCatalogue();
			state.catalogue = catalogue;
			catalogue.compList = comlist;
			for (IRun a : rlist) {
				a.generateRandom();
				a.setup(state);
			}

			TUtil.buildsoundfortests(recorder, state);
			int todiff = 0;
			runtests(recorder, state, todiff);
			if (todiff < lowest) {
				lowest = todiff;

			}

			System.out.println(todiff);
		}
	}

}
