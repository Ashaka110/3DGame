package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Rotator extends Entity{
	boolean cooldown;
	Point[] p=new Point[8];
	public Rotator (double x,double  y,double z){
		super(x, y, z);
		double a =1.5;
		double b =1.5;
		double c =1.5;
		p[0]=new Point(.05 * b, .05*a, .05 * c);
		p[1]=new Point(.05 * b, .05*a,-.05 * c);
		p[2]=new Point(.05 * b,-.05*a,-.05 * c);
		p[3]=new Point(.05 * b,-.05*a, .05 * c);
		p[4]=new Point(-.05 * b, .05*a, .05 * c);
		p[5]=new Point(-.05 * b, .05*a,-.05 * c);
		p[6]=new Point(-.05 * b,-.05*a,-.05 * c);
		p[7]=new Point(-.05 * b,-.05*a, .05 * c);
		
	}
	public void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		g.setColor(Color.red);
		Vector[] v = new Vector[8];
		for(int c=0; c<8; c++){
			v[c]=dir.rotateGlobal(pos, new Point(p[c].x+x, p[c].y+y, p[c].z + z),  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
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
	public void update(double cosy, double siny, Player player, double delta){//int delta){
		
		Point[] n=new Point[8];
		for (int c =0; c<p.length; c++){
			n[c]=new Point( cosy * p[c].x + (-siny)* p[c].y, siny * p[c].x + cosy * p[c].y, p[c].z);
			//n[c]=new Point( cosy * n[c].x + (-siny)* n[c].z, n[c].y, siny * n[c].x + cosy * n[c].z);
			//n[c]=new Point(  n[c].x, cosy * n[c].y + (-siny)* n[c].z, siny * n[c].y + cosy * n[c].z);
		}
		if ( player.pos.x < x + .25 && player.pos.x > x- .25 && player.pos.y < y + .25 && player.pos.y > y- .25 && player.pos.z < z + .25 && player.pos.z > z- .25){
			if(!cooldown){
			player.gravx +=90;
			Level.roty+=180;
			player.dir.dy=-player.dir.dy;
			
			//player.dir.dx+=180;
			cooldown=true;
			}
		}else{
			cooldown=false;
		}
		p=n;
	}
}
