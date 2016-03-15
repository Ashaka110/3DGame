package hyperLand;

import java.util.ArrayList;

import javagame.Point;
import javagame.Vector;

import org.newdawn.slick.Graphics;

public class Plane4D {
	point4D[] points;
	Plane4D(point4D x, point4D y, point4D z, point4D a)
	{
		points = new point4D[4];
		points[0]=x;
		points[1]=y;
		points[3]=z;
		points[2]=a;
		
		
	}
	
	public String toString(){
		return "--\n" + points[0] + "\n" +   points[1] + "\n" + points[2] + "\n" + points[3];
	}
	public void Render(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy){
		ArrayList<Vector> v = new ArrayList<Vector>();
		for (int c =0; c<points.length; c++){
			if(points[c].a > 0  != points[(c +1) % 4].a >0 ){
				Point f =findIntersect(points[c], points[(c+1)%4]);
				//
				v.add(dir.rotateView(pos, (f)));
			}
		}
		if(v.size()>1){
			if(v.get(0).dx < 1 &&  v.get(0).dx > -1  && v.get(0).dy < 1 && v.get(0).dy>-1)
				g.drawLine((float)(( v.get(0).dx + k)*disx), (float)(( v.get(0).dy + l)* disy), (float)(( v.get(1).dx + k)* disx),(float)(( v.get(1).dy + l)*disy));
			
		}
		
	}
	Point findIntersect(point4D a, point4D b){
		point4D d = b.subtract(a), f = d.divide(d.a/a.a); //, f = e.divide(1) ;
		System.out.println(f);
		return new Point (f.x + a.x, f.y +a.y, f.z + a.z);
	}
	
}
