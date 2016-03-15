package Drawing;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class DrawingLine extends DrawingObject{
	
	public DrawingLine(double x, double y, double x1, double y1){
		this.x=x;this.y=y; this.x1=x1; this.y1=y1;
	}
	public DrawingLine(double x, double y, double x1, double y1, Color c){
		this.x=x;this.y=y; this.x1=x1; this.y1=y1; this.c=c;
	}
	@Override
	public void Draw(double dx, double dy, double ds, Graphics g) {
		// TODO Auto-generated method stub
		Point2D p = new Point2D(x, y).toViewSpace(dx, dy, ds);
		
		Point2D p1 = new Point2D(x1, y1).toViewSpace(dx, dy, ds);
		
		double ns=s*ds;
		g.setColor(c);
		g.drawLine((float)p.x- .5f* (float)ns, (float)p.y - .5f *(float)ns, (float)p1.x- .5f* (float)ns, (float)p1.y - .5f *(float)ns);
	}
	
}
