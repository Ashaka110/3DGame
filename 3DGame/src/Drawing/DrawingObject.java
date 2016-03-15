package Drawing;

import java.awt.geom.Point2D;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class DrawingObject {
	double x, y, s, x1, y1;
	 Color c = Color.white;
	public abstract void Draw(double dx, double dy, double ds, Graphics g);
	
}
