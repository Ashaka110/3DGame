package javagame;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public class MovingPoint extends spot{
	ArrayList<spot> frames = new ArrayList<spot>();
	public MovingPoint(double x1, double y1, double z1) {
		super(x1, y1, z1);
		
	}
	void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		frames.get((int)Menu.frame % frames.size()).draw(dir,  pos,  g,  k, l, disx, disy);
	}
	/**
	 * @param args
	 */
	

}
