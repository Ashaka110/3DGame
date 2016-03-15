package javagame;

import org.newdawn.slick.Graphics;

public class Box {
	Point[] p=new Point[4];
	public Box(double x1, double y1, double z1, double x2, double y2, double z2){
		p[0]=new Point(x1, y1, z1);
		p[1]=new Point(x1, y1, z2);
		p[2]=new Point(x2, y2, z2);
		p[3]=new Point(x2, y2, z1);
		
	}
	void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		Vector[] v = new Vector[4];
		for(int c=0; c<4; c++){
			v[c]=dir.rotateView(pos, p[c]);
		}
		for(int c=0; c<4; c++){
			if(v[c].dx < 1 &&  v[c].dx > -1  && v[c].dy < 1 && v[c].dy>-1)
			g.drawLine((float)(( v[c].dx + k)*disx), (float)(( v[c].dy + l)* disy), (float)(( v[(c+1)%4].dx + k)* disx),(float)(( v[(c+1)%4].dy + l)*disy));
		}
		
	}
}
