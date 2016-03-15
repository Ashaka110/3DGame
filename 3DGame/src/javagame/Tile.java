package javagame;

import org.newdawn.slick.Graphics;

public abstract class Tile {
	double x, y, z;
	
	
	abstract void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy, double cosx,double sinx,double cosy,double siny);
	abstract void update();
}
