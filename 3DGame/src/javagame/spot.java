package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class spot extends Box{
	Point p;
	Color c;
	public spot(double x1, double y1, double z1){
		super(x1, y1, z1, x1, y1, z1);
		p = new Point(x1, y1, z1);
		c=Color.white;
	}
	public spot(double x1, double y1, double z1, Color c){
		super(x1, y1, z1, x1, y1, z1);
		p = new Point(x1, y1, z1);
		this.c=c;
	}
	void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		Vector v = dir.rotateView(pos, p);
			g.setColor(c);
			if(v.dx < 1 &&  v.dx > -1  && v.dy < 1 && v.dy>-1)
			g.drawLine((float)(( v.dx + k)*disx), (float)(( v.dy + l)* disy), (float)(( v.dx + k)* disx),(float)(( v.dy + l)*disy));
		
		
	}
}