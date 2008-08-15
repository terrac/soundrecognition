package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.imageio.plugins.png.PNGImageWriter;

public class Visual extends JFrame {

	private static final int h = 1100;
	private static final int w = 700;

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		// super.paint(g);
		g.setColor(Color.black);
		g.fillRect(0, 0, w, h);
		Graphics2D g2 = (Graphics2D) g;

		for (linecolor a : linecols) {
			g2.setColor(a.col);
			for (int i = 0; i < a.lines.size(); i++) {
				Line2D line2D = a.lines.get(i);

				g2.draw(line2D);
				// System.out.println(line2D);

			}
		}
		g2.setColor(Color.white);
		int b = 0;
		int e = 0;
		for (String a : textOutput) {
			g2.drawString(a, e, 250 + b);
			b += 20;
			if(b == 500){
				e+= 130;
				b = 0;
			}
		}
	}

	public List<String> textOutput = new ArrayList<String>();

	public void save(String name) {
		BufferedImage awtImage = new BufferedImage(getWidth(), getHeight(),
				BufferedImage.TYPE_INT_RGB);

		Graphics g = awtImage.getGraphics();
		paint(g);

		try {
			ImageIO.write(awtImage, "PNG", new File("pics\\" + name + ".png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public List<linecolor> linecols = new ArrayList<linecolor>();
	{
		// lines..add(new Line2D.Double(0,0,100,100));
	}

	public Visual() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// v.setPreferredSize(new Dimension(50,50));
		setSize(w, h);
		show();
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				save("piconexit");

			}
		});
	}

	public static void main(String[] args) {
		Visual v = new Visual();
		v.setForeground(Color.black);

	}

}
