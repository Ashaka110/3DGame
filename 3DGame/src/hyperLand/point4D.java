package hyperLand;

public class point4D {
	double x, y, z, a;
	point4D(double x, double y, double z, double a)
	{
		this.x=x;this.y=y;this.z=z;this.a=a;
		
		
		
		
	}
	
	public String toString(){
		return "{ " + x + ",  \t" + y + ", \t" + z + ", \t" + a + " }";
	}
	point4D subtract(point4D b){
		return new point4D(this.x - b.x, this.y-b.y, this.z-b.z, this.a-b.a);
	}
	
	point4D divide(double d){
		return new point4D(x/d, y/d, z/d, a/d);
	}
	void set(double x, double y, double z, double a){
		this.x=x;this.y=y;this.z=z;this.a=a;
	}
}
