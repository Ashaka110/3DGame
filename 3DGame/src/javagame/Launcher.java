package javagame;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Launcher extends Entity{
	ArrayList<box> boxes = new ArrayList<box>();
	Launcher(double x, double y, double z) {
		super(x, y, z);
		boxes.add(new box(x, y, z, 0.01, .3,  .5));
		boxes.add(new box(x, y, z, 0.02, .25, .75));
		boxes.add(new box(x, y, z, 0  , .2, 1));
		boxes.add(new box(x, y, z, 0.015  ,.35, .25));
		
		boxes.add(new box(x, y, z, .5 ,  .25, .75));
		boxes.add(new box(x, y, z, .51 , .2, 1));
		boxes.add(new box(x, y, z, .52 , .3,.5));
		boxes.add(new box(x, y, z, .515 ,.35, .25));
		
		boxes.add(new box(x, y, z, .25 ,.25, .75));
		boxes.add(new box(x, y, z, .26 ,.3, .5));
		boxes.add(new box(x, y, z, .26 ,.2, 1));
		boxes.add(new box(x, y, z, .27 ,.35, .25));
		
		boxes.add(new box(x, y, z, .75 ,.25, .75));
		boxes.add(new box(x, y, z, .75 ,.3, .5));
		boxes.add(new box(x, y, z, .76 ,.2, 1));
		boxes.add(new box(x, y, z, .77 ,.35, .25));
		//boxes.add(new box(x, y, z, .25, .75));
		//boxes.add(new box(x, y, z, .75, .75));
	}

	@Override
	public void update(double cosy, double siny, Player player, double delta) {
		if ( player.pos.x < x + .25 && player.pos.x > x- .25 && player.pos.y < y + .25 && player.pos.y > y- .25 && player.pos.z < z + .25 && player.pos.z > z- .25){
			player.vz=.01;
		}
		for (int c = 0;c< boxes.size(); c++){
			boxes.get(c).update(siny);
		}
	}

	@Override
	public void draw(Vector dir, Point pos, Graphics g, double k, double l,
			
			double disx, double disy) {
		g.setColor(Color.blue);
			for (int c = 0;c< boxes.size(); c++){
				boxes.get(c).draw(dir, pos, g, k, l, disx, disy, disy, disy, disy, disy, disy, disy, disy, disy, disy, disy);//,  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
			}
		double cs=.35;
		Point[] p=new Point[4];
		p[0]=new Point(x -cs, y -cs, z-.5);
		p[1]=new Point(x -cs, y +cs, z-.5);
		p[2]=new Point(x +cs, y +cs, z-.5);
		p[3]=new Point(x +cs, y -cs, z-.5);
		
		Vector[] v = new Vector[4];
		
		for(int c=0; c<4; c++){
			v[c]=dir.rotateGlobal(pos, p[c],  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
		}
		int t =5;
		for(int c=0; c<4; c++){
			if(v[c].dx < t &&  v[c].dx > -t  && v[c].dy < t && v[c].dy>-t && v[(c+1)%4].dx < t &&  v[(c+1)%4].dx > -t  && v[(c+1)%4].dy < t && v[(c+1)%4].dy>-t)
			g.drawLine((float)(( v[c].dx + k)*disx), (float)(( v[c].dy + l)* disy), (float)(( v[(c+1)%4].dx + k)* disx),(float)(( v[(c+1)%4].dy + l)*disy));
		}
		
	}
	private class box{
		double x, y, z, dz, size,  length;
		
		box(double x, double y, double z, double dz, double size, double length){
			this.x=x;
			this.y=y;
			this.z=z;
			this.dz=dz;
			this.size=size;
			this.length=length;
		}
		void draw (Vector dir, Point pos, Graphics g, double k, double l,
				double disx, double disy, double cosx, double sinx, double cosy,
				double siny, double cosrx, double sinrx, double cosry, double sinry, double cosrz, double sinrz){
			double cs=size;
			Point[] p=new Point[4];
			p[0]=new Point(x -cs, y -cs, z-.5 + dz);
			p[1]=new Point(x -cs, y +cs, z-.5 + dz);
			p[2]=new Point(x +cs, y +cs, z-.5 + dz);
			p[3]=new Point(x +cs, y -cs, z-.5 + dz);
			
			Vector[] v = new Vector[4];
			
			for(int c=0; c<4; c++){
				v[c]=dir.rotateGlobal(pos, p[c],  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
			}
			int t =5;
			for(int c=0; c<4; c++){
				if(v[c].dx < t &&  v[c].dx > -t  && v[c].dy < t && v[c].dy>-t && v[(c+1)%4].dx < t &&  v[(c+1)%4].dx > -t  && v[(c+1)%4].dy < t && v[(c+1)%4].dy>-t)
				g.drawLine((float)(( v[c].dx + k)*disx), (float)(( v[c].dy + l)* disy), (float)(( v[(c+1)%4].dx + k)* disx),(float)(( v[(c+1)%4].dy + l)*disy));
			}
		}
		void update(double delta){
			dz+=delta;
			dz%=length;
		}
		
		
	}
	

}
