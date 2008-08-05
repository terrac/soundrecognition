package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import run.IRun;


import compare.compare;
import compare.compareDots;
import compare.comparePattern;

import catalogues.Catalogue;
import catalogues.StanCata;


public class Recorder {

	public Recorder() {
		visual = new Visual();
	}

	public Recorder(boolean a) {
		console = a;
		if (!console) {
			visual = new Visual();
		}
	}

	Capture capture = new Capture();
	AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
			44100, 8, 2, 2, 44100, true);

	public byte[] getDataFromStream(AudioInputStream audioInputStream) {
		byte[] audioBytes = null;
		AudioFormat format = audioInputStream.getFormat();

		try {
			audioBytes = new byte[(int) (audioInputStream.getFrameLength() * format
					.getFrameSize())];
			audioInputStream.read(audioBytes);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return audioBytes;
	}

	State state = new State();
	public AudioInputStream audioInputStream;

	public static void main(String s[]) {
		List<compare> comlist = new ArrayList();
		List<IRun> rlist = new ArrayList();
		Recorder recorder = new Recorder(true);
		String string = "Recorder.txt";
		rlist = (List<IRun>) TUtil.load(string);

		comlist.add(new compareDots());
		
		for (IRun a : rlist) {
			if (a.getCompare() != null)
				comlist.add(a.getCompare());
		}

		State state = new State();

		Catalogue catalogue = new StanCata();
		state.catalogue = catalogue;
		 catalogue.setCompares(comlist);
		catalogue.compMap.put("default",  comlist);
		for (IRun a : rlist) {
			a.getNext();
			a.setup(state);
		}

		TUtil.buildsoundfortests(recorder, state);

		recorder.console = false;
		recorder.visual = new Visual();
		visual.repaint();
		// System.exit(0);
		recorder.state = state;
		recorder.capture.start();
	}

	

	public static boolean console = false;

	/**
	 * Reads data from the input channel and writes to the output stream
	 */
	class Capture implements Runnable {

		TargetDataLine line;
		Thread thread;

		public void start() {
			thread = new Thread(this);
			thread.setName("Capture");
			thread.start();
		}

		public void stop() {
			thread = null;
		}

		private void shutDown(String message) {
			thread = null;

		}

		public void run() {

			audioInputStream = null;

			// define the required attributes for our line,
			// and make sure a compatible line is supported.

			DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

			if (!AudioSystem.isLineSupported(info)) {
				shutDown("Line matching " + info + " not supported.");
				return;
			}

			// get and open the target data line for capture.

			try {
				line = (TargetDataLine) AudioSystem.getLine(info);
				line.open(format, 16384);
			} catch (LineUnavailableException ex) {
				shutDown("Unable to open the line: " + ex);
				return;
			} catch (SecurityException ex) {
				shutDown(ex.toString());
				return;
			} catch (Exception ex) {
				shutDown(ex.toString());
				return;
			}

			// play back the captured audio data

			int bufferLengthInFrames = line.getBufferSize() / 8;
			int bufferLengthInBytes = bufferLengthInFrames / 2;
			byte[] data = new byte[bufferLengthInBytes];
			int numBytesRead;

			line.start();

			boolean sending = false;
			double second = 0;
			boolean prevSend = false;

			while (thread != null) {
				if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
					break;
				}

				out.write(data, 0, numBytesRead);

				state.addBytes(data, "");

			}
			try {
				out.flush();
				out.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}

			// we reached the end of the stream. stop and close the line.
			line.stop();
			line.close();
			line = null;

		}

		public void endcapture(String name) {

			int frameSizeInBytes = format.getFrameSize();
			// load bytes into the audio input stream for playback
			byte audioBytes[] = out.toByteArray();
			// byte audioBytes[] = out.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
			audioInputStream = new AudioInputStream(bais, format,
					audioBytes.length / frameSizeInBytes);

			long milliseconds = (long) ((audioInputStream.getFrameLength() * 1000) / format
					.getFrameRate());
			out.reset();
			// playback.start();
			// try {
			// thread.sleep(1500);
			// } catch (InterruptedException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// playback.stop();
			try {
				audioInputStream.reset();
			} catch (Exception ex) {
				ex.printStackTrace();
				return;
			}
			saveToFile("gsounds\\" + Math.random() + name + ".au",
					AudioFileFormat.Type.AU, audioInputStream);

		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

	} // End class Capture

	public void saveToFile(String name, AudioFileFormat.Type fileType,
			AudioInputStream audioInputStream) {

		// reset to the beginnning of the captured data
		try {
			audioInputStream.reset();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		File file = new File(name);
		try {
			if (AudioSystem.write(audioInputStream, fileType, file) == -1) {
				throw new IOException("Problems writing to file");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static Visual visual;

	public static Visual getVisual() {
		// if(visual == null)
		// visual = new Visual();
		return visual;
	}
}
