package Drawing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import org.newdawn.slick.Color;

import javagame.Game;

public class FileLoader {
	
	public static String toLoad = "Drawing4.txt";
	
	public static ArrayList<DrawingObject> Open (){
		Scanner scan = new Scanner(System.in);
		try {
			scan = new Scanner(new File(toLoad));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<DrawingObject> obj= new ArrayList<DrawingObject>();
		
		while (scan.hasNextLine()){
			obj.add(ReadLine(scan.nextLine()));
		}
		
		return obj;
		
		
	}static Formatter text;
	public static void Save (ArrayList<DrawingObject> ob){
		
		try {
			text= new Formatter(toLoad);
		} catch (FileNotFoundException e) {}
		
		for (int c=0; c<ob.size(); c++){
			
			if(ob.get(c).getClass().equals(Circle.class)){
				text.format("%s %s %s %s %s %s %s \n", "Circle", ob.get(c).c.r, ob.get(c).c.g, ob.get(c).c.b, ob.get(c).x, ob.get(c).y, ob.get(c).s);
			}if(ob.get(c).getClass().equals(DrawingRect.class)){
				text.format("%s %s %s %s %s %s %s %s %s \n", "Rect", ob.get(c).c.r, ob.get(c).c.g, ob.get(c).c.b, ob.get(c).x, ob.get(c).y, ob.get(c).x1, ob.get(c).y1, ob.get(c).s);
			}if(ob.get(c).getClass().equals(DrawingLine.class)){
				text.format("%s %s %s %s %s %s %s %s %s \n", "Line", ob.get(c).c.r, ob.get(c).c.g, ob.get(c).c.b, ob.get(c).x, ob.get(c).y, ob.get(c).x1, ob.get(c).y1, ob.get(c).s);
			}
		}
		
		text.close();
		
	}
	
	public static DrawingObject ReadLine(String line){
		Scanner s = new Scanner(line);
		String type = s.next();
		Color c = new Color(s.nextFloat(), s.nextFloat(), s.nextFloat());
		
		
		if(type.equals("Circle")){
			return new Circle(s.nextDouble(), s.nextDouble(), s.nextDouble(), c);
		}if(type.equals("Rect")){
			return new DrawingRect(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), c);
		}if(type.equals("Line")){
			return new DrawingLine(s.nextDouble(), s.nextDouble(), s.nextDouble(), s.nextDouble(), c);
		}
		return null;
		
	}
	
		
}
