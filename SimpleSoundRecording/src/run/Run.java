package run;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import catalogues.Catalogue;



import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;
import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;
import all.State;

public class Run {

	

	public Run() {
		super();
	}

	enum bl{
		and,or
	}
	List<bl> blist = new ArrayList<bl>();


	protected void run(Recorder recorder) {

	}


	public static int runtests(Recorder recorder, State state) {
		 int tdiff = 0;
		for (File a : new File("tsounds").listFiles()) {

			state.catalogue.testname = TUtil.getname(a);
			TUtil.runFile(recorder, state, a, "");
			tdiff += state.catalogue.diff;
		}
		return tdiff;
	}
	public static int runtests(Recorder recorder, State state,
			Catalogue catalogue) {
		int count = 0;
		int todiff = 0;
		for (File a : new File("tsounds").listFiles()) {

			catalogue.testname = TUtil.getname(a);
			TUtil.runFile(recorder, state, a, "");
			// System.out.println(catalogue.accepted + " "
			// + catalogue.diff);
			if (!catalogue.accepted) {
				count++;
			}
			todiff += catalogue.diff;
		}
		return todiff;
	}
}