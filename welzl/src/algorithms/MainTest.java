package algorithms;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import supportGUI.*;

public class MainTest {
	private static int width = 0;
	private static int height = 0;
	private static String title = "Welzl";
	private static String filename = "samples/test-19.points";
	private static FramedDiamRace f;

	private static int nbPoints = 100;

	public static void main(String[] args) {
		
		File[] fi = Utils.listeRepertoire("samples/") ;
		
		
		for(File x:fi){
			System.out.println(x.getAbsolutePath());
		}
		for (int i = 0; i < args.length; i++) {
			if (args[i].charAt(0) == '-') {
				if (args[i + 1].charAt(0) == '-') {
					System.err.println("Option " + args[i]
							+ " expects an argument but received none");
					return;
				}
				switch (args[i]) {
				case "-nbPoints":
					try {
						Integer.parseInt(args[i + 1]);
					} catch (Exception e) {
						System.err.println("Invalid argument for option "
								+ args[i] + ": Integer type expected");
						return;
					}
					break;
				default:
					System.err.println("Unknown option " + args[i]);
					return;
				}
				i++;
			}
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				// framedGUI = new FramedGUI(width,height,title,nbPoints);
				f = new FramedDiamRace(width, height, title, nbPoints);

			}
		});

		synchronized (Variables.lock) {
			try {
				Variables.lock.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}

		readFile();
	}

	public static void readFile() {
		String line;
		String[] coordinates;
		ArrayList<Point> points = new ArrayList<Point>();
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename)));
			try {
				while ((line = input.readLine()) != null) {
					coordinates = line.split("\\s+");
					points.add(new Point(Integer.parseInt(coordinates[0]),
							Integer.parseInt(coordinates[1])));
				}
				// framedGUI.drawPoints(points);
				f.drawPoints(points);
				synchronized (Variables.lock2) {
					Variables.lock2.notify();
				}
			} catch (IOException e) {
				System.err.println("Exception: interrupted I/O.");
			} finally {
				try {
					input.close();
				} catch (IOException e) {
					System.err.println("I/O exception: unable to close "
							+ filename);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Input file not found.");
		}
	}

}
