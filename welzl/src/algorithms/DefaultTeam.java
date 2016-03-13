package algorithms;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import supportGUI.Circle;
import supportGUI.Line;
import supportGUI.Variables;

public class DefaultTeam {

	public Circle calculCercleMin(ArrayList<Point> points) {
		if (points.isEmpty()) {
			return null;
		}
		// return calculCercleMinNaif(points);
		return welzl(points);
	}

	/**
	 * ========= Circle calculCercleMinWelzl ==============
	 */
	public Circle welzl(ArrayList<Point> points) {
		
		return bMinDisk(points, new ArrayList<Point>());
		
	}

	private Circle bMinDisk(ArrayList<Point> Ps, ArrayList<Point> R) {
		ArrayList<Point> P = new ArrayList<Point>(Ps);
		Random r = new Random();
		Circle D = null;

		if (P.isEmpty() || R.size() == 3) {
			D = bmd(new ArrayList<Point>(), R);

		} else {
			Point p = P.get((r.nextInt(P.size())));
			P.remove(p);

			D = bMinDisk(P, R);
			if (D != null && !contains(D, p)) {
				R.add(p);
				D = bMinDisk(P, R);
				R.remove(p);
			}
		}

		return D;
	}

	private Circle bmd(ArrayList<Point> P, ArrayList<Point> R) {
		if (P.isEmpty() && R.size() == 0)
			return new Circle(new Point(0, 0), 10);
		Random r = new Random();
		Circle D = null;
		if (R.size() == 1) {
			D = new Circle(R.get(0), 0);
		}
		if (R.size() == 2) {

			double cx = (R.get(0).x + R.get(1).x) / 2;
			double cy = (R.get(0).y + R.get(1).y) / 2;
			double d = R.get(0).distance(R.get(1)) / 2;
			Point p = new Point((int) cx, (int) cy);
			D = new Circle(p, (int) Math.ceil(d));
		} else {
			if (R.size() == 3)
				D = circle3point(R.get(0), R.get(1), R.get(2));
		}
		return D;
	}

	/**
	 * ========= Circle calculCercleMinNaif ==============
	 */

	public Circle calculCercleMinNaif(ArrayList<Point> points) {
		if (points.isEmpty()) {
			return null;
		}
		Circle c = null;
		Circle cc = null;
		int diam = Integer.MAX_VALUE;
		for (Point p : points) {
			for (Point q : points) {
				if (p != null && q != null && !p.equals(q)) {
					cc = new Circle(new Point(((p.x + q.x) / 2),
							((p.y + q.y) / 2)), ((int) (p.distance(q)) / 2));
					if (containsAll(cc, points) && cc.getRadius() < diam) {
						diam = cc.getRadius();
						c = cc;
					}
				}
			}
		}

		for (Point p : points) {
			for (Point q : points) {
				for (Point r : points) {
					if ((!p.equals(q) && !q.equals(r) && !r.equals(p))) {
						cc = circle3point(p, q, r);
						if (containsAll(cc, points) && cc.getRadius() < diam) {
							diam = cc.getRadius();
							c = cc;
						}
					}
				}
			}
		}
		return c;
	}

	private boolean containsAll(Circle c, ArrayList<Point> points) {
		if (c == null)
			return false;
		for (Point p : points) {
			if (p == null)
				continue;
			if (p.distance(c.getCenter()) - c.getRadius() - 1 > 0.00001) {
				return false;
			}
		}
		return true;
	}

	/**
	 * == :: methodes Utils :: ==
	 */

	private boolean contains(Circle c, Point p) {
		if (p.distance(c.getCenter()) - c.getRadius() < 0.00001) {
			return true;
		}
		return false;
	}

	private int norm(Point a) {
		return (a.x * a.x) + (a.y * a.y);
	}

	private Circle circle3point(Point a, Point b, Point c) {
		double d = (a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) * 2;
		if (d == 0)
			return null;
		double x = ((norm(a) * (b.y - c.y)) + (norm(b) * (c.y - a.y)) + (norm(c) * (a.y - b.y)))
				/ d;
		double y = ((norm(a) * (c.x - b.x)) + (norm(b) * (a.x - c.x)) + (norm(c) * (b.x - a.x)))
				/ d;
		Point p = new Point((int) x, (int) y);
		return new Circle(p, (int) Math.ceil(p.distance(a)));

	}

	/**
	 * ##################################################
	 */

	public Line calculDiametre(ArrayList<Point> points) {
		if (points.size() < 3) {
			return null;
		}

		Point p = points.get(0);
		Point q = points.get(1);

		/*******************
		 * PARTIE A ECRIRE *
		 *******************/
		return new Line(p, q);
	}

	public Line calculDiametreOptimise(ArrayList<Point> points) {
		if (points.size() < 3) {
			return null;
		}

		Point p = points.get(1);
		Point q = points.get(2);

		/*******************
		 * PARTIE A ECRIRE *
		 *******************/
		return new Line(p, q);
	}

	public ArrayList<Point> enveloppeConvexe(ArrayList<Point> points) {
		if (points.size() < 3) {
			return null;
		}

		ArrayList<Point> enveloppe = new ArrayList<Point>();

		enveloppe.add(points.get(0));
		enveloppe.add(points.get(1));
		enveloppe.add(points.get(2));

		/*******************
		 * PARTIE A ECRIRE *
		 *******************/
		return points;
	}

}
