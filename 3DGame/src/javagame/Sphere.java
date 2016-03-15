package javagame;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Sphere extends Entity{

	double size;
	Sphere(double x, double y, double z) {
		super(x, y, z);
		size = .4;
	}

	@Override
	public void update(double cosy, double siny, Player player, double delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Vector dir, Point pos, Graphics g, double k, double l,
			double disx, double disy) {
		Vector v = dir.rotateView(pos, new Point(this.x, this.y, this.z));
		double slope =  - pos.x-x / pos.y-y;
		double rat = Math.sqrt(((pos.x-x) *(pos.x-x) + (pos.y-y) * (pos.y-y))) / .4;
		double r = size;
		double xi = pos.x -x;
		double yi = pos.y -y;
		double xn = (2 * r * r * xi + Math.sqrt(4 * r*r*r*r * xi*xi - 4 * (xi*xi + yi*yi) *(-r*r * yi*yi - r*r*r*r))) / (2* xi*xi + 2 *yi*yi); 
		
		Vector s = dir.rotateView(pos, new Point(x + xn, this.y + Math.sqrt(r*r - xn * xn), this.z));
		
		
		g.setColor(Color.white);
		if(v.dx < 1 &&  v.dx > -1  && v.dy < 1 && v.dy>-1)
		
		g.drawOval((float)(( v.dx + k)*disx), (float)(( v.dy + l)* disy),(float)(( v.dx + k)*disx) - (float)(( s.dx + k)*disx), (float)(( v.dx + k)*disx) - (float)(( s.dx + k)*disx));//(s.dx - v.dx),(float)( v.dx-s.dx));
	}

}
