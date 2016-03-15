package Shapes;

import javagame.Point;

public class Torus extends Mesh{

	public Torus(double x, double y, double z) {
		super(x, y, z);
		int sx = 50, sy = 50;
		map = new Point[sx][sy];
		for(int c=0; c<map.length; c++){
			for(int d = 0; d < map[c].length; d++)
				map[c][d]= new Point((x)+ (25 + 16 * Math.cos((double)c/sx * 2* 3.14)) * Math.cos((double)d/sy * 2 * 3.14), 
						(y) + (25 + 16 * Math.cos((double)c/sx * 2* 3.14)) * Math.sin((double)d/sy * 2 * 3.14),
						z + 16 * Math.sin((double)c/sx * 2* 3.14));//
				
				
				
						//new Point((x)+ (25 + 16 * Math.cos(c)) * Math.cos(d), 
						//(y) + (25 + 16 * Math.cos(c)) * Math.sin(d),
						//z + 16 * Math.sin(c)); 
						
						
		}
	}

}
