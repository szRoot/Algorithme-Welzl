package algorithms;

import java.awt.Point;
import java.io.File;
import java.util.ArrayList;

import supportGUI.Circle;

public class main {

	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		DefaultTeam dt = new DefaultTeam() ;
		String path = "samples/" ;
		File[] listFic =Utils.listeRepertoire(path);
		ArrayList<Point> points = new ArrayList<Point>() ;
		for(File x:listFic){
			points = Utils.readFile(x.getPath());
			
			System.out.println();
			//long debutW = System.currentTimeMillis();
			long debutW = System.nanoTime();
			Circle cw= dt.welzl(points);
			long tempsW=System.nanoTime() -debutW;
			long debutN = System.nanoTime();
			Circle cn= dt.calculCercleMinNaif(points);
			long tempsN=System.nanoTime() -debutN;
			
			
			//System.out.println("fichier : "+x.getPath()+ " welzl :: "+tempsW+" naif :: "+tempsN);
			System.out.println("fichier : "+x.getPath()+ " :"+tempsW+" "+tempsN);
			
		}
		System.out.println(System.currentTimeMillis() -debut);
	}

}
