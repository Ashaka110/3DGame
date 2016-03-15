package javagame;

import Shapes.HeightMap;


public class Player {
	int goal;
	Point pos;
	double gravx, gravy, gravz;
	boolean grounded;
	Vector dir;
	double vx, vy, vz;
	
	public Player(double x, double y, double z, double dx, double dy, int g){
		pos = new Point(x, y, z);
		dir = new Vector(dx, dy);
		goal=g;
	}
	public void update(int[][][] board, double delta){
		double fall = 	fall(delta);
		Point p ;
		Point t = rotateG(new Point (0, 0, fall), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
		if(	vz<=0){
			p= rotateG(new Point (0, 0,  -.5), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
			//System.out.print("do");
			
		}else{
			p= rotateG(new Point (0, 0,  .5), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
			//System.out.print("up");
		}
			//t.y=-t.y;
			//t.x=-t.x;
			//p.y=-p.y;
			//p.x=-p.x;
			//p.z=-Math.abs(p.z);
		
		//Point test= new Point (t.x + pos.x + .5, t.y + pos.y + .5, t.z + pos.z +.5 );
		try{
		
		if(!checkBlock(p, board)){// && vz<=0){ //checkBlock(p, board) &&//board[(int) (pos.z )][(int) (pos.x +.5)][(int) (pos.y+.5)] == 1 && 
			
				
			//if(!checkBlock(p, board)){
			vz=0;
			grounded=true;//}
			
			
		}else{
			move(t.x, t.y, t.z);
			//pos.z+=fall(delta);
		}}
		
		catch(Exception e){
			move(t.x, t.y, t.z);
			//pos.z +=fall(delta);
		}
	}	
	public void update(HeightMap map, double delta){
		double fall = 	fall(delta);
		if(grounded && pos.z<map.getH(pos.x, pos.y, pos.z)){
			pos.z = map.getH(pos.x, pos.y, pos.z);
		}
		Point p ;
		Point t = rotateG(new Point (0, 0, fall), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
		if(	vz<=0){
			p= rotateG(new Point (0, 0,  -.5), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
			//System.out.print("do");
			
		}else{
			p= rotateG(new Point (0, 0,  .5), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);	
			//System.out.print("up");
		}
			//t.y=-t.y;
			//t.x=-t.x;
			//p.y=-p.y;
			//p.x=-p.x;
			//p.z=-Math.abs(p.z);
		
		//Point test= new Point (t.x + pos.x + .5, t.y + pos.y + .5, t.z + pos.z +.5 );
		try{
		
		if(!checkPoint(p, map)){// && vz<=0){ //checkBlock(p, board) &&//board[(int) (pos.z )][(int) (pos.x +.5)][(int) (pos.y+.5)] == 1 && 
			
				
			//if(!checkBlock(p, board)){
			vz=0;
			grounded=true;//}
			
			
		}else{
			move(t.x, t.y, t.z);
			//pos.z+=fall(delta);
		}}
		
		catch(Exception e){
			move(t.x, t.y, t.z);
			//pos.z +=fall(delta);
		}
		if(map.testSlope((int)pos.x,(int) pos.y, 2)){
			Point grad = map.getGradient((int)pos.x,(int) pos.y);
			pos = new Point ((grad.x)*(double)delta * .0002+pos.x, (grad.y)*(double)delta * .0002+pos.y, pos.z);
		}
		
	}
	public void testmove(double cosx, double sinx, int delta, int[][][] board){
		
		double dx = cosx * (double)delta * .002;
		double dy = sinx * (double)delta * .002;
		Point t = rotateG(new Point (dx, dy, 0), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
		
		//Point test= new Point (t.x + pos.x + .5, t.y + pos.y + .5,- t.z + pos.z + .1);
		
		try{
		
		if (checkBlock(t, board))//board[(int) (pos.z +.1)][(int) (pos.x + dx + .5)][(int) (pos.y + dy + .5)] != 1){
			move(t.x, t.y, t.z);
		
		}catch(Exception e){move(t.x, t.y, t.z);}
		
		
	}public void testmove(double cosx, double sinx, int delta, HeightMap map){
		
		double dx = cosx * (double)delta * .002;
		double dy = sinx * (double)delta * .002;
		Point t = rotateG(new Point (dx, dy, 0), Level.cosrx, Level.sinrx, Level.cosry, Level.sinry, Level.cosrz, Level.sinrz);
		
		//Point test= new Point (t.x + pos.x + .5, t.y + pos.y + .5,- t.z + pos.z + .1);
		
		try{
		t.z= -.5;
		if (checkPoint(t, map))//board[(int) (pos.z +.1)][(int) (pos.x + dx + .5)][(int) (pos.y + dy + .5)] != 1){
			move(t.x, t.y, 0);
		else if((map.getH(t.x + pos.x, t.y + pos.y, t.z + pos.z) - pos.z)/ Math.sqrt( t.x * t.x + t.y * t.y )< 1.5){
			move(t.x, t.y, map.getH(t.x + pos.x, t.y + pos.y, t.z + pos.z) - pos.z);
		}
		}catch(Exception e){move(t.x, t.y, 0);}
		
		
	}
	
	public void testmove(int x, int y, int z, double cosx, double sinx, int delta, int[][][] board){
		
		
		
	}
	public void move (double dx, double dy, double dz){
		pos.x+=dx;
		pos.y+=dy;
		pos.z+=dz;
	}
	
	
	public double fall(double delta){
		vz-= 9.8 * delta * .000001;
		return vz * delta - .5 * 9.8 * Math.pow(delta *.0001 , 2);
		
	}
	
	
	public boolean checkBlock (Point p, int[][][] board){
		double x= (p.x + pos.x + .5);
		double y= (p.y + pos.y + .5); 
		double z= (p.z + pos.z + .5);
		//System.out.println((x -.5) + " " + (y-.5) + " " + (z-.5) +"    pos " + pos.x + " " + pos.y + " " + pos.z);
		return board[(int)z][(int)x][(int)y]==0;
		
	}
	
	
	public boolean checkPoint (Point p, HeightMap map){
		double x= (p.x + pos.x );
		double y= (p.y + pos.y ); 
		double z= (p.z + pos.z + .5);
		//System.out.println((x -.5) + " " + (y-.5) + " " + (z-.5) +"    pos " + pos.x + " " + pos.y + " " + pos.z);
		if (map.getH(x, y, z) < z){
			return true;
		}
		
		
		
		return false;
	}
	
	public Point rotateG (Point b, double cosrx, double sinrx, double cosry,
			double sinry, double cosrz, double sinrz) {
		
		Point a=new Point(b.x, b.y * cosrx  + b.z*  -sinrx , b.z * cosrx + b.y * sinrx);
		Point y=new Point(a.x * cosry  + a.z*  sinry , a.y , a.z * cosry + a.x * -sinry);
		Point c=new Point((y.x * cosrz)  + y.y*  -sinrz , y.y * cosrz + y.x * sinrz, y.z);
		
		return c;
	}
	public Point getForward(){
		double a, b, c, d;
		a = Math.sin(Math.toRadians(-dir.dy));
		b = Math.cos(Math.toRadians(-dir.dy));
		c = b * Math.sin(Math.toRadians(-dir.dx+90));
		d = b * Math.cos(Math.toRadians(-dir.dx+90));
		return new Point (c, d, a);
	}
	
}
