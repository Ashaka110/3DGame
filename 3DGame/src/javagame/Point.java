package javagame;

public class Point {
	public double x, y, z;
	public Point(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Point add(Point a){
		return new Point (a.x = x, a.y + y, a.z+ z);
	}
	public Point add(double nx, double ny, double nz){
		return new Point (nx + x, ny + y, nz+ z);
	}
	public String toString(){
		return x + " " + y + " " + z;
	}
}
