package Shapes;

import javagame.Point;
import javagame.Vector;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Mesh {
Point[][] map;
	
	Point pos;
	public Mesh(double x, double y, double z){
		pos = new Point(x, y, z);
		
				
	}
	
	public void update(double cosy, double siny, double delta){
		Point[][] n=new Point[map.length][map[0].length];
		for (int c =0; c<n.length; c++){
			for (int d =0; d<n[c].length; d++){
		
				//n[c][d]=new Point( cosy * (map[c][d].x) + (-siny)* map[c][d].y, siny * map[c][d].x + cosy * map[c][d].y, map[c][d].z);
			}
		}
		//map=n;
	}
	
	public void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy, double cosx, double sinx, double cosy, double siny){
		Vector[][] v = new Vector[map.length][map[0].length];
		for(int x=0; x<v.length; x++){
			for(int y = 0; y < v[x].length; y++)
				v[x][y]=dir.rotateView(pos, map[x][y]);
		}
		for(int x=0; x<v.length -1; x++){
			for(int y = 0; y < v[x].length-1; y++){
				if(v[x][y].dx < 5 &&  v[x][y].dx > -5  && v[x][y+1].dy < 5 && v[x][y+1].dy>-5){
					//g.setColor(Color.blue);
					g.drawLine((float)(( v[x][y].dx + k)*disx), (float)(( v[x][y].dy + l)* disy), (float)(( v[x][y+1].dx + k)* disx),(float)(( v[x][y+1].dy + l)*disy));
				}
				if(v[x][y].dx < 5 &&  v[x][y].dx > -5  && v[x+1][y].dy < 5 && v[x+1][y].dy>-5)
					//g.setColor(Color.red);
					g.drawLine((float)(( v[x][y].dx + k)*disx), (float)(( v[x][y].dy + l)* disy), (float)(( v[x+1][y].dx + k)* disx),(float)(( v[x+1][y].dy + l)*disy));
				if(v[x][y].dx < 5 &&  v[x][y].dx > -5  && v[x+1][y + 1].dy < 5 && v[x+1][y + 1].dy>-5)
					//g.setColor(Color.green);
					g.drawLine((float)(( v[x][y].dx + k)*disx), (float)(( v[x][y].dy + l)* disy), (float)(( v[x+1][y + 1].dx + k)* disx),(float)(( v[x+1][y + 1].dy + l)*disy));
			}
		}
		
	}
	
	
}
