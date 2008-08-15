package build;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import run.CompareRun;
import run.CountLengthRun;
import run.IRun;
import run.LengthBetweenSizeAvgCompDotsRun;
import run.Run;


import main.BasicCatalogue;
import main.Recorder;
import main.State;
import main.TUtil;


import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import PatternMatch.Tuple;

import catalogues.Catalogue;
import catalogues.PCatalogue;

import com.sun.org.apache.bcel.internal.generic.LLOAD;
import compare.compareDotsTop;
import compare.compareUpDownMiddle;
import compare.compareblock;

public class BuildPossiblePatterns extends Run {

	
	
	public static void main(String[] args) {
		Recorder recorder = new Recorder(true);
		BuildPossiblePatterns bpp = new BuildPossiblePatterns();
		String string = "list.txt";
		bpp.run(recorder);
		TUtil.save(bpp.vrun, string);
	}

	List<IRun> lrun = new ArrayList<IRun>();{
		//lrun.add(new CountLengthRun());
//		lrun.add(new LengthBetweenSizeAvgCompDotsRun());
	//	lrun.add(new LengthBetweenSizeAvgCompDotsRun(new compareDotsTop()));
	//	lrun.add(new run.PatternRun());
		
		lrun.add(new CompareRun(new compareUpDownMiddle()));
		//lrun.add(new CompareRun(new compareblock()));
		
	}
	List<Tuple<IRun, Integer>> vrun = new ArrayList();
	protected void run(Recorder recorder) {
		Map<String,Object> variables = new HashMap<String, Object>();
		ArrayList arrayList = new ArrayList();
		arrayList.add(120);
		variables.put("losalist", arrayList);
	    arrayList = new ArrayList();
		arrayList.add(281);
		variables.put("lolist", arrayList);
		for (IRun a : lrun) {
			//if(
					int execute = a.execute(recorder,variables);
				//	< 3000000){
				vrun.add(new Tuple<IRun, Integer>(a,execute));
			//}
		}
		System.out.println(vrun);
	}
	protected void setuplogic() {
		
	}



}
