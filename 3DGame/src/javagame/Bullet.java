package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Bullet extends Chip{
    Point unitdir;
	double speed;
	boolean points;
	
	public Bullet(Player p){
		super(p.pos.x, p.pos.y, p.pos.z);
		setUp(p);
	}
	public Bullet(Player p, boolean add){
		super(p.pos.x, p.pos.y, p.pos.z);
		setUp(p);
		speed = .08;
		this.add=true;
	}
	public Bullet(Player p, double sped, boolean bol){
		super(p.pos.x, p.pos.y, p.pos.z);
		setUp(p);
		speed = sped;
		points = bol;
	}
	public void setUp (Player p){
		unitdir = p.rotateG(p.getForward(),  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
	//	this.dx = v.dx;
		//this.dy = v.dy;
	}
	
	@Override
	public void update(double cosy, double siny, Player player, double delta) {
		
		moveRelative(unitdir.x *speed, unitdir.y *speed, unitdir.z *speed);
		Point[] n=new Point[8];
		for (int c =0; c<p.length; c++){
			n[c]=new Point( cosy * p[c].x + (-siny)* p[c].y, siny * p[c].x + cosy * p[c].y, p[c].z);
			n[c]=new Point( cosy * n[c].x + (-siny)* n[c].z, n[c].y, siny * n[c].x + cosy * n[c].z);
			n[c]=new Point(  n[c].x, cosy * n[c].y + (-siny)* n[c].z, siny * n[c].y + cosy * n[c].z);
		}
		p=n;
	}
	@Override
	public void draw(Vector dir, Point pos, Graphics g, double k, double l,
			double disx, double disy) {
		if(points){
			Vector v = dir.rotateView(pos, new Point(this.x, this.y, this.z));
			g.setColor(Color.white);
			if(v.dx < 1 &&  v.dx > -1  && v.dy < 1 && v.dy>-1)
			g.drawLine((float)(( v.dx + k)*disx), (float)(( v.dy + l)* disy), (float)(( v.dx + k)* disx),(float)(( v.dy + l)*disy));
		}else{
			g.setColor(Color.blue);
			super.draw(dir, pos, g, k, l, disx, disy);
		}
	}
	
	public Point getSide(){
		Point n = new Point((x+.5)%1 -.5,(y+.5)%1 -.5,(z+.5)%1 -.5 );
		if (Math.abs(n.y) > Math.abs(n.x) && Math.abs(n.y) > Math.abs(n.z)){
			if(n.y >= 0){
				return new Point (0, 1, 0);
			}else{
				return new Point (0, -1, 0);
			}
		}if (Math.abs(n.x) > Math.abs(n.y) && Math.abs(n.x) > Math.abs(n.z)){
			if(n.x >= 0){
				return new Point (1, 0, 0);
			}else{
				return new Point (-1, 0, 0);
			}
		}if (Math.abs(n.z) > Math.abs(n.x) && Math.abs(n.z) > Math.abs(n.y)){
			if(n.z >= 0){
				return new Point (0, 0, 1);
			}else{
				return new Point (0, 0, -1);
			}
		}
		
		return null;
	}
	
	
}
