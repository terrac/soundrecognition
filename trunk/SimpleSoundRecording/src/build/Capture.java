package build;
/*
 * @(#)CapturePlayback.java	1.11	99/12/03
 *
 * Copyright (c) 1999 Sun Microsystems, Inc. All Rights Reserved.
 *
 * Sun grants you ("Licensee") a non-exclusive, royalty free, license to use,
 * modify and redistribute this software in source and binary code form,
 * provided that i) this copyright notice and license appear on all copies of
 * the software; and ii) Licensee does not utilize the software in a manner
 * which is disparaging to Sun.
 *
 * This software is provided "AS IS," without a warranty of any kind. ALL
 * EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 * IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR
 * NON-INFRINGEMENT, ARE HEREBY EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE
 * LIABLE FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING
 * OR DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN OR ITS
 * LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 * INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 * CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 * OR INABILITY TO USE SOFTWARE, EVEN IF SUN HAS BEEN ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 *
 * This software is not designed or intended for use in on-line control of
 * aircraft, air traffic, aircraft navigation or aircraft communications; or in
 * the design, construction, operation or maintenance of any nuclear
 * facility. Licensee represents and warrants that it will not use or
 * redistribute the Software for such purposes.
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.Enumeration;
import java.io.*;

import javax.sound.sampled.*;

import all.State;

import catalogues.StanCata;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.awt.font.*;
import java.text.*;


	public class Capture implements Runnable {
		String filename = "filename";
		AudioInputStream audioInputStream;
		AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
				44100, 8, 2, 2, 44100, true);
		File file;
		public void createAudioInputStream(File file) {
			if (file != null && file.isFile()) {
				try {
					this.file = file;
					
					audioInputStream = AudioSystem.getAudioInputStream(file);
					
				} catch (Exception ex) {
					
				}
			} else {

			}

		}

		public void saveToFile(String name, AudioFileFormat.Type fileType) {
			System.out.println(name);
			if (audioInputStream == null) {

				return;
			} else if (file != null) {
				createAudioInputStream(file);
			}

			// reset to the beginnning of the captured data
			try {
				audioInputStream.reset();
			} catch (Exception e) {

				return;
			}

			File file = new File(name);
			try {
				if (AudioSystem.write(audioInputStream, fileType, file) == -1) {
					throw new IOException("Problems writing to file");
				}
			} catch (Exception ex) {

			}

		}


		public byte[] getDataFromStream(AudioInputStream audioInputStream) {
			byte[] audioBytes = null;
			AudioFormat format = audioInputStream.getFormat();

			try {
				audioBytes = new byte[(int) (audioInputStream.getFrameLength() * format
						.getFrameSize())];
				audioInputStream.read(audioBytes);
			} catch (Exception ex) {
				
				return null;
			}

			return audioBytes;
		}
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
			State state = new State(){
				@Override
				public void end(String name) {
					// TODO Auto-generated method stub
					super.end(name);
					endcapture();
				}
			};
			state.catalogue = new StanCata();
			while (thread != null) {
				if ((numBytesRead = line.read(data, 0, bufferLengthInBytes)) == -1) {
					break;
				}
				
				out.write(data, 0, numBytesRead);
				state.addBytes(data, filename);
					
				


				
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

		public void endcapture() {

			int frameSizeInBytes = format.getFrameSize();
			// load bytes into the audio input stream for playback
			byte audioBytes[] = out.toByteArray();
			// byte audioBytes[] = out.toByteArray();
			ByteArrayInputStream bais = new ByteArrayInputStream(audioBytes);
			audioInputStream = new AudioInputStream(bais, format,
					audioBytes.length / frameSizeInBytes);

			out.reset();

			try {
				audioInputStream.reset();
			} catch (Exception ex) {
				ex.printStackTrace();
				return;
			}

			
			saveToFile("gsounds\\"+ filename.trim()+Math.random()+".au", AudioFileFormat.Type.AU);
		
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		public static void main(String s[]) {
			Capture a= new Capture();
			a.filename = s[0];
			a.filename = "three";
			a.start();


		}
		
	} // End class Capture



