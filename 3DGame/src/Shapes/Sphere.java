package Shapes;

import javagame.Point;

public class Sphere extends Mesh{

	public Sphere(double x, double y, double z) {
		super(x, y, z);
		int sx = 50, sy = 50;
		map = new Point[sx][sy];
		for(int c=0; c<map.length; c++){
			for(int d = 0; d < map[c].length; d++)
				map[c][d]= 
				//new Point((x-25)+ 25 * Math.cos(c) * Math.sin(d), 
				//(y-25) + 25 * Math.sin(c) * Math.sin(d),
				//z + 25 * Math.cos(d));
				
				new Point((x-25)+ 25 * Math.cos((double)c/49 * 2* 3.14) * Math.sin((double)d/49 * 3.14), 
						(y-25) + 25 * Math.sin((double)c/49 * 2* 3.14) * Math.sin((double)d/49 * 3.14),
						z + 25 * Math.cos((double)d/49 * 3.14));
	
		}
	}

}
