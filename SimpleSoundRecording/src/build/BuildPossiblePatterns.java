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
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;


import main.BasicCatalogue;
import main.Recorder;
import main.TUtil;


import PatternMatch.Block;
import PatternMatch.BlockCompare;
import PatternMatch.SoundBit;
import all.Catalogue;
import all.State;

import catalogues.PCatalogue;

import com.sun.org.apache.bcel.internal.generic.LLOAD;

public class BuildPossiblePatterns extends Run {

	
	
	public static void main(String[] args) {
		Recorder recorder = new Recorder(true);
		BuildPossiblePatterns bpp = new BuildPossiblePatterns();
		String string = "list.txt";
		bpp.run(recorder);
		TUtil.save(bpp.vrun, string);
	}

	List<IRun> lrun = new ArrayList<IRun>();{
		lrun.add(new BlockCompareRun());
		lrun.add(new CountLengthRun());
		lrun.add(new LengthBetweenSizeAvgRun());
	}
	List<IRun> vrun = new ArrayList<IRun>();
	protected void run(Recorder recorder) {
		
		for (IRun a : lrun) {
			//if(
					a.execute(recorder);
				//	< 3000000){
				vrun.add(a);
			//}
		}
		System.out.println(vrun);
	}
	protected void setuplogic() {
		
	}



}
