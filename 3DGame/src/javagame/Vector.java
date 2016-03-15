package javagame;

public class Vector {
	public double dx, dy, cosx, cosy, sinx, siny;
	Vector(double x, double y){
		dx=x;
		dy=y;
	}
	public Vector rotateView (Point p, Point o){
		//double cosx = Math.cos(Math.toRadians(dx - 90));
		//double sinx = Math.sin(Math.toRadians(dx - 90));
		//double cosy = Math.cos(Math.toRadians(dy));
		//double siny = Math.sin(Math.toRadians(dy));
		Point y=new Point((o.x-p.x) , (o.y-p.y) ,(o.z-p.z));
		Point n=new Point(y.x * cosx  + y.y*  sinx , y.y * cosx + y.x * -sinx, y.z);
		Point t=new Point( n.x,cosy * n.y + (-siny)* n.z, siny * n.y + cosy * n.z);
		//System.out.println(t.x + " " + t.y + " " + t.z + " " + Math.sin(Math.toRadians(dx)));
		//Point v=toPoint(this.dx, this.dy);
		double dx = t.x/t.y;// + Game.X/2;//Math.toDegrees(Math.atan2(t.x, t.y));///o.x - p.x, o.y - p.y));
		//dx+=this.dx;
		//while(dx < 0){
	    //    dx += 360;
	    //}dx%=360;
		double dy = -t.z/t.y;// + Game.Y/2;//Math.toDegrees(Math.atan2(t.x, t.z));// o.x - p.x, o.z - p.z));
	    //dy+=this.dy;
	    //while(dy < 0){
	     //   dy += 360;
	    //}dy%=360;
		//System.out.println(t.x+ " , " +t.y + " , " + t.z + " : " + this.dx + ", " + dy);
		if (t.y > 0)
			return new Vector(dx, dy);
		else
			return new Vector(5, 5);
	}
	public Vector rotateGlobal(Point p, Point o, double cosrx, double sinrx, double cosry,
			double sinry, double cosrz, double sinrz) {
		Point y=new Point((o.x-p.x) , (o.y-p.y) ,(o.z-p.z) );
		
		Point c = rotateG(y, cosrx, sinrx, cosry, sinry, cosrz, sinrz);
		
		return rotateView(new Point(0, 0, 0), c);
	}
	
	
	public Point rotateG (Point y, double cosrx, double sinrx, double cosry,
			double sinry, double cosrz, double sinrz) {
		
		Point a=new Point((y.x * cosrz)  + y.y*  sinrz , y.y * cosrz + y.x * -sinrz, y.z);
		Point b=new Point(a.x * cosry  + a.z*  -sinry , a.y , a.z * cosry + a.x * sinry);
		Point c=new Point(b.x, b.y * cosrx  + b.z*  sinrx , b.z * cosrx + b.y * -sinrx);
		
		return c;
	}
	
	public void updateTrig(){
		cosx = Math.cos(Math.toRadians(dx-90)); 
		sinx = Math.sin(Math.toRadians(dx-90));
		cosy = Math.cos(Math.toRadians(dy));
		siny = Math.sin(Math.toRadians(dy));
	}
	public Point getForward(){
		double a, b, c, d;
		a = Math.sin(Math.toRadians(-dy));
		b = Math.cos(Math.toRadians(-dy));
		c = b * Math.sin(Math.toRadians(-dx+90));
		d = b * Math.cos(Math.toRadians(-dx+90));
		return new Point (c, d, a);
	}
}
