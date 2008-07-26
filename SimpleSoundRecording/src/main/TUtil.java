package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sound.sampled.AudioSystem;

import all.State;

public class TUtil {
	public static void runFile(Recorder recorder, State state, File a,
			String name) {
		byte[] audioBytes = null;

		try {
			recorder.audioInputStream = AudioSystem.getAudioInputStream(a);
			while (recorder.audioInputStream.available() > 0) {
				audioBytes = new byte[2756];
				recorder.audioInputStream.read(audioBytes);
				state.addBytes(audioBytes, name);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static String getname(File a) {
		int j = 0;
		char[] charArray = a.getName().toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (!Character.isLetter(charArray[i])) {
				j = i;
				break;
			}
		}
		String name = a.getName().substring(0, j);
		return name;
	}

	public static void writeStringToFile(String filepath, Collection... coll) {
		try {

			FileWriter first = new FileWriter(new File(filepath + ".txt"));

			for (Collection a : coll)
				first.write(a + "\n");
			first.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static List<Integer>[] readFileAsString(String filePath) {
		List<Collection<Integer>> a = new ArrayList<Collection<Integer>>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String str = "";
			while ((str = in.readLine()) != null) {
				str = str.substring(1, str.length() - 1);
				System.out.println(str);
				String[] sta = str.split(",");
				List<Integer> b = new ArrayList<Integer>();
				for (String c : sta) {
					b.add(Integer.parseInt(c.trim()));
				}
				a.add(b);

			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(a);
		return (List<Integer>[]) a.toArray(new List[1]);
	}
	
	public static void buildsoundfortests(Recorder recorder, State state) {
		for (File a : new File("sounds").listFiles()) {

			String name = TUtil.getname(a);
			TUtil.runFile(recorder, state, a, name);

		}
	}
}
