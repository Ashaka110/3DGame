package Drawing;

public class Point2D {
	double x, y;
	public Point2D(double x, double y){
		this.x=x;
		this.y=y;
	}
	
	public Point2D toViewSpace(double dx, double dy, double ds){
		
		return new Point2D((x + dx) * ds + javagame.Game.X *.5, (y + dy) * ds + javagame.Game.Y *.5);
		
	}
	
}
