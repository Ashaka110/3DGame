package javagame;

import org.newdawn.slick.Graphics;

public class SpinningCube {
	Point[] p=new Point[8];
	double x, y, z;
	public SpinningCube(double x,double  y,double z){
		this.x=x;
		this.y=y;
		this.z=z;
		double a =5;
		double b =5;
		double c =5;
		p[0]=new Point(.05 * b, .05*a, .05 * c);
		p[1]=new Point(.05 * b, .05*a,-.05 * c);
		p[2]=new Point(.05 * b,-.05*a,-.05 * c);
		p[3]=new Point(.05 * b,-.05*a, .05 * c);
		p[4]=new Point(-.05 * b, .05*a, .05 * c);
		p[5]=new Point(-.05 * b, .05*a,-.05 * c);
		p[6]=new Point(-.05 * b,-.05*a,-.05 * c);
		p[7]=new Point(-.05 * b,-.05*a, .05 * c);
		
	}
	void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		Vector[] v = new Vector[8];
		for(int c=0; c<8; c++){
			v[c]=dir.rotateView(pos, new Point(p[c].x+x, p[c].y+y, p[c].z + z));
		}
		for(int c=0; c<4; c++){
			if(v[c].dx < 2 &&  v[c].dx > -2  && v[c].dy < 2 && v[c].dy>-2)
			g.drawLine((int)(( v[c].dx + k)*disx), (int)(( v[c].dy + l)* disy), (int)(( v[(c+1)%4].dx + k)* disx),(int)(( v[(c+1)%4].dy + l)*disy));
		}for(int c=0; c<4; c++){
			if(v[c+4].dx < 2 &&  v[c+4].dx > -2  && v[c+4].dy < 2 && v[c+4].dy>-2)
			g.drawLine((int)(( v[c+4].dx + k)*disx), (int)(( v[c+4].dy + l)* disy), (int)(( v[(c+1)%4 +4].dx + k)* disx),(int)(( v[(c+1)%4 +4].dy + l)*disy));
		}for(int c=0; c<4; c++){
			if(v[c+4].dx < 2 &&  v[c+4].dx > -2  && v[c+4].dy < 2 && v[c+4].dy>-2)
			g.drawLine((int)(( v[c].dx + k)*disx), (int)(( v[c].dy + l)* disy), (int)(( v[(c)%4 +4].dx + k)* disx),(int)(( v[(c)%4 +4].dy + l)*disy));
		}
		
	}
	void update(double cosy, double siny){//int delta){
		
		Point[] n=new Point[8];
		for (int c =0; c<p.length; c++){
			n[c]=new Point( cosy * p[c].x + (-siny)* p[c].y, siny * p[c].x + cosy * p[c].y, p[c].z);
		}
		p=n;
	}
}
