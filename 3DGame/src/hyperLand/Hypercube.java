package hyperLand;

import javagame.Player;
import javagame.Point;
import javagame.Vector;

import org.newdawn.slick.Graphics;

public class Hypercube {
	double x, y, z, a;
	point4D[] points;
	Plane4D[] planes;
	public Hypercube(double x, double y, double z, double a)
	{
		this.x=x;this.y=y;this.z=z;this.a=a;
		
		setUpPoints();
		setUpPlanes();
		
		
	}
	public void PrintPlanes(){
		for(int c=0; c<planes.length; c++){
			System.out.println(planes[c]);
		}
	}
	void setUpPoints(){
		points=new point4D[16];
		int c =0;
		for (int a=0; a<2; a++){
			for (int x=0; x<2; x++){
				for (int y=0; y<2; y++){
					for (int z=0; z<2; z++){
						points[c]=new point4D(2*(x-.5),2*(y-.5), 2*(z-.5),2*(a-.5) );
						c++;
					}
				}
			}
		}
	}
	int c =0;
	void setUpPlanes(){
		planes = new Plane4D[24];
		c=0;
		for(int a = 0; a<4; a++){
			planes[c]=new Plane4D(points[a], points[a+4], points[a+8], points[a+12]);
			c++;
		}
		for(int a = 0; a<4; a++){
			planes[c]=new Plane4D(points[a*4], points[a*4 + 1], points[a*4 + 2], points[a*4 + 3]);
			c++;
		}
		
		addPlane(1,2,5,6);
		addPlane(3,4,7,8);
		addPlane(9,10,13,14);
		addPlane(11,12,15,16);

		addPlane(6,8,14,16);
		addPlane(5,7,13,15);
		addPlane(2,4,10,12);
		addPlane(1,3,9,11);
		
		addPlane(1,2,9,10);
		addPlane(3,4,11,12);
		addPlane(5,6,13,14);
		addPlane(7,8,15,16);
		
		addPlane(1,3,5,7);
		addPlane(2,4,6,8);
		addPlane(9,11,13,15);
		addPlane(10,12,14,16);
		
		
		
		
	}
	void addPlane(int a, int b,int c2,int d){
		planes[c]=new Plane4D(points[a-1], points[b-1], points[c2-1], points[d-1]);
		c++;
		
	}
	public void Render(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		for (int c=0; c<planes.length;c++){
			planes[c].Render(dir, pos, g, k, l, disx, disy);
		}
	}
	public void update(double cosy, double siny){//int delta){
		for (int c =0; c<points.length; c++){
			points[c].set(siny * points[c].y + cosy * points[c].x,
					cosy * points[c].y + (-siny)* points[c].x,
					points[c].z,
					points[c].a
					);
		}
		
		
	}
	
}
