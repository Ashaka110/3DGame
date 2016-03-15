package javagame;

import org.newdawn.slick.Graphics;

public abstract class Entity {
	
	double x, y, z, dx, dy;
	boolean collected;
	boolean add;
	static int[][][] board;
	Entity(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Point getSide(){
		return null;
	}
	public abstract void update(double cosy, double siny, Player player, double delta);
	public abstract void draw(Vector dir, Point pos, Graphics g, double k, double l, double disx, double disy);
	public void moveRelative(double x, double y, double z){
		this.x+=x;
		this.y+=y;
		this.z+=z;
	}
	public void moveTo (double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}	
	public boolean checkBlock (int[][][] board, double cx, double cy, double cz){
		int x=(int) cx;
		int y=(int) cy;
		int z=(int) cz;
		
		if (board[z][y][x]==0){
			return true;
		}
		
		
		
		return false;
	}
}
