package javagame;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
//import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Shapes.*;

public class Heigthtest extends BasicGameState{
	Player p;
	//Point pos = new Point(20, 20, .8);
	//Vector dir = new Vector(0, 0);
	ArrayList<Box> b=new ArrayList<Box>();
	ArrayList<SpinningCube> chip=new ArrayList<SpinningCube>();
	double k=1;//-50;//Game.X/2;
	double l=1;//-50;//Game.Y/2;
	double disy=Game.Y/2;//1;//
	double disx=Game.X/2;//1;//
	public String mouse = "noinp";
	public Robot robot;
	double cosx;
	double sinx;
	double cosy;
	double siny;
	int xcenter, ycenter;
	boolean paused;
	HeightMap mymap;
	public Heigthtest(int state){
		p = new Player(16.5,7.5 , 1.8, 0, 0, 0);
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		try {
			robot=new Robot();
			
		} catch (AWTException e) {
			e.printStackTrace();
		}
	//setCenter(gc);
		//graph();
		mymap = new HeightMap(0, 0, 0);
		
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		p.dir.updateTrig();
		g.setColor(Color.white);
		if(p.pos.x<1 && p.pos.x>-1 && p.pos.y<1 && p.pos.y>-1){
			
		}else{
			
		}
		for (int c =0; c<b.size(); c++){
			b.get(c).draw(p.dir, p.pos, g, k, l, disx, disy);
		}
		g.setColor(Color.green);
		for (int c =0; c<chip.size(); c++){
			chip.get(c).draw(p.dir, p.pos, g, k, l, disx, disy);
		}
		g.setColor(Color.blue);
		g.drawString(sinx + " " + cosx, 20, 40);
		g.drawString(mouse + " " + xcenter + " " + ycenter + "  " + p.dir.dx + "  " + p.pos.x + "  " + p.pos.y , 20, 20);
		g.drawLine(Game.X/2+5, Game.Y/2, Game.X/2 -5, Game.Y/2);
		g.drawLine(Game.X/2, Game.Y/2 + 5, Game.X/2, Game.Y/2 -5);
		mymap.draw(p.dir, p.pos, g, k, l, disx, disy, cosx, sinx, cosy, siny);
	}
	double speed = 5;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		cosx = Math.cos(Math.toRadians(p.dir.dx )); 
		sinx = Math.sin(Math.toRadians(p.dir.dx));
		if(p.dir.dy>90){
			p.dir.dy=90;
		}else if(p.dir.dy<-90){
			p.dir.dy=-90;
		}
		double coscx = Math.cos(Math.toRadians(delta/20.0));
		double sincx = Math.sin(Math.toRadians(delta/20.0));
		
		for (int c =0; c<chip.size(); c++){
			chip.get(c).update(coscx, sincx);
		}
		p.dir.dx%=360;
		Input input= gc.getInput();
		if(input.isKeyDown(Input.KEY_W)){
			p.testmove(cosx, sinx, delta, mymap);//pos.x+=delta * .0005;
		}if(input.isKeyDown(Input.KEY_S)){
			p.testmove(-cosx, -sinx, delta, mymap);
		}if(input.isKeyDown(Input.KEY_D)){
			p.testmove( sinx,-cosx, delta, mymap);//pos.y-=delta * .0005;
		}if(input.isKeyDown(Input.KEY_A)){
			p.testmove(-sinx, cosx,  delta, mymap);//pos.y+=delta * .0005;
		} if(input.isKeyDown(Input.KEY_SPACE) && p.grounded){
			p.vz+=.005;
			p.grounded=false;
			//p.pos.z+=
		}if(input.isKeyDown(Input.KEY_LSHIFT)){
			//pos.z-=delta * .0005 * speed;
		}  if(input.isKeyDown(Input.KEY_J)){
			p.dir.dx+=delta * .05;//90;//
		}   if(input.isKeyDown(Input.KEY_L)){
			p.dir.dx-=delta * .05;//90;//
		}  if (input.isKeyPressed(Input.KEY_C)){
			chip.add(new SpinningCube(p.pos.x, p.pos.y, p.pos.z));
		} if (input.isKeyPressed(Input.KEY_Z)){
			chip.clear();
		} if (input.isKeyPressed(Input.KEY_N)){
			sbg.enterState(3);
		}if (input.isKeyPressed(Input.KEY_P)){
			if(paused){
				paused=false;
				System.out.println(mymap);
			}else{
				paused=true;
			}
		}if(input.isKeyPressed(Input.KEY_V)){
			speed *=10;
		}if(input.isKeyPressed(Input.KEY_B)){
			speed /=10;
		}
		if(!paused){
		mouse=Mouse.getX()+" , "+ (Game.Y-Mouse.getY());
		RecenterMouse(gc);
		}
		mymap.update(coscx, sincx, delta);
		//p.pos.z = mymap.getH(p.pos.x,p.pos.y, p.pos.z);
		p.update(mymap, delta);
	}
	
	boolean centered;
	
	private void RecenterMouse(GameContainer gc) {
		
		p.dir.dx-=(Mouse.getX()-xcenter)*0.08;
		p.dir.dy+=(Game.Y-Mouse.getY()-ycenter	)*0.08;
		//System.out.println(Display.getX());
		if(robot != null){
			robot.mouseMove(Game.X/2, Game.Y/2);
		}
		if(!centered){
			setCenter(gc);
			centered = true;
		}
		
	}
	private void setCenter(GameContainer gc){
		if(robot != null){
			
		}robot.mouseMove(Game.X/2, Game.Y/2);
		xcenter = Mouse.getX();
		ycenter = Game.Y - Mouse.getY();
	}
	
	public int getID(){
		return 2;
	}
	public void move(double cosx, double sinx, int delta){
		p.pos.x-= sinx * (double)delta * .0005;
		p.pos.y+= cosx * (double)delta * .0005;
	}
	
}