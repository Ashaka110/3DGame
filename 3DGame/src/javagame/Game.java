package javagame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

	public static final String gamename = "3D TEST";	
	public static final int menu = 0;
	public static final int level = 1;
	public static final int X = 1500;
	public static final int Y = 1000;
	
	public GameContainer gc;
	public Game(String gamename){
		super(gamename);
		this.addState(new Menu(0));
		this.addState(new Level(1));
		this.addState(new Heigthtest(2));
		this.addState(new Drawing.Drawing(3));
		
		//System.out.println((int)3.5);
	}
	
	public void initStatesList(GameContainer gc) throws SlickException{
		this.gc=gc;
		//this.getState(0).init(gc, this);
		this.getState(1).init(gc, this);
		this.getState(2).init(gc, this);
		this.getState(3).init(gc, this);
		
	}
	
	public static void main(String[] args) {
		System.out.println("running...");
		AppGameContainer appgc;
		try{
			
			appgc = new AppGameContainer(new Game(gamename));
			appgc.setDisplayMode(X, Y,  false);
			appgc.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
		
	}

}
