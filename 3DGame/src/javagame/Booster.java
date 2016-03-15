package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Booster extends Entity{
	
	int dirr;
	double speed;
	Point[] p=new Point[12];
	Booster(double x, double y, double z, int dir, double speed) {
		super(x, y, z);
		this.dirr = dir;
		this.speed = speed;
		p[0]=new Point(0 + x,y -.45  , z +.5);
		p[1]=new Point(x -.45,  0 + y, z +.5);
		p[2]=new Point (x -.45, 0 + y, z +.5);
		p[3]=new Point(0 + x, y+ .45, z+ .5);
		
		p[4]=new Point(x+ .45, y - .45, z+.5);
		p[5]=new Point(x, y, z+.5);
		p[6]=new Point(x, y, z+.5);
		p[7]=new Point(x +.45, y+.45, z+.5);
		
		p[8]=new Point ( x+.9  ,y - .45  , z+.5);
		p[9]=new Point ( x+.45  ,y   , z+.5);
		p[10]=new Point( x+.45  ,y   , z+.5);
		p[11]=new Point( x+.9  ,y + .45  , z+.5);
	}

	@Override
	public void update(double cosy, double siny, Player player, double delta) {
		double dx = 0.5, dy = .5;
		if(dirr ==0){
			dx = 0.75;
		}else if(dirr == 1){
			dy = 0.75;
		}else if (dirr == 2){
			dx = .75;
		}else if (dirr == 3){
			dy = .75;
		}
		
		if ( player.pos.x < x + dx && player.pos.x > x- dx && player.pos.y < y + dy && player.pos.y > y- dy && player.pos.z < z + .25 && player.pos.z > z- .25){
			if(dirr == 0){
				player.pos.x -=delta * speed;
			}else if(dirr == 1){
				player.pos.y -=delta * speed; 
			}else if(dirr == 2){
				player.pos.x +=delta * speed; 
			}else if(dirr == 3){
				player.pos.y +=delta * speed; 
			}
		}
		for (int c= 0; c<3; c++){
			for (int d =0; d<4; d++){
				p[c * 4 + d].x-=delta * speed;
			}
			//p[c].y=(p[c].x - x) + y;
			if(p[c*4 ].x - x < -.45){
				for (int d =0; d<4; d++){
					p[c * 4 + d].x+=.9;//9;//
				}
				p[c*4 + 1].y = y;
				p[c*4 + 2].y = y;
			}
			if(p[c*4  +1].x - x < -.45){
				
				p[c*4 + 1].y += p[c*4 + 1].x - x + .45;
				p[c*4 + 2].y -= p[c*4 + 1].x - x + .45;
				p[c*4 + 1].x = x -.45;
				p[c*4 + 2].x = x -.45;
				
			}else if(p[c*4 + 1].x - x > 0){
				
				p[c* 4 + 3].y     =y + .45 -  (p[c*4 + 1].x - x ) ;
			    p[c*4 ].y = y - .45  + (p[c*4 + 1].x - x);
				p[c*4].x = x + .45;
				p[c*4 + 3].x = x +.45;
			}else{
				
				p[c*4 + 1].y = y;
				p[c*4 + 2].y = y;
				p[c*4 + 1].x = p[c*4].x - .45;
				p[c*4 + 2].x = p[c*4].x - .45;
			}
			
		}
		
	}

	@Override
	public void draw(Vector dir, Point pos, Graphics g, double k, double l,
			double disx, double disy) {
		
		Vector[] v = new Vector[p.length];
		Point[] p = new Point[this.p.length];
		for(int c=0; c<v.length; c++){
			if (dirr == 0){
				p[c]=new Point (this.p[c].x, this.p[c].y, this.p[c].z - 1);
			}else if(dirr == 1){
				p[c]=new Point (this.p[c].y - y + x, this.p[c].x - x + y, this.p[c].z - 1);
			}else if(dirr == 2){
				p[c]=new Point (-(this.p[c].x - x) + x, this.p[c].y - y + y, this.p[c].z - 1);
			}else {
				p[c]=new Point ((this.p[c].y - y) + x, -(this.p[c].x - x) + y, this.p[c].z - 1);
			}
			v[c]=dir.rotateGlobal(pos, new Point(p[c].x, p[c].y, p[c].z ),  Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
		}
		int t=5;
		g.setColor(Color.red);
		for(int c=0; c<6; c++){
			if(v[c*2].dx < t &&  v[c*2].dx > -t  && v[c*2].dy < t && v[c*2].dy>-t && v[c*2 +1].dx < t &&  v[(c*2) +1].dx > -t  && v[c*2 +1].dy < t && v[c *2 +1].dy>-t)
				g.drawLine((float)(( v[c * 2].dx + k)*disx), (float)(( v[c * 2].dy + l)* disy), (float)(( v[c * 2 + 1].dx + k)* disx),(float)(( v[c * 2 + 1].dy + l)*disy));
			
		}
		
		
	}

}
