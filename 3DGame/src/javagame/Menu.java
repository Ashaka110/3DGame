package javagame;
import hyperLand.Hypercube;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

import Shapes.*;

public class Menu extends BasicGameState{
	Point pos = new Point(0, 0, 0);
	Vector dir = new Vector(0, 0);
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
	boolean paused;
	Mesh mymap;
	Hypercube hypercube;
	public Menu(int state){
		
	}
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		try {
			robot=new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		drawAxies();
		
		/*
		String stringg = "0  58645    	1  229693    	1  304954    	2  344540    	3  373862    	5  410515    	8  439838    	13  468183    	21  500438    	34  533670    	55  571789    	89  612840    	144  658779    	233  710582    	377  783888    	610  868924    	987  1001364    	1597  1161660    	2584  1457329    	4181  1913782    	6765  2599440    	10946  3915532    	17711  6007204    	28657  6256934    	46368  6615646    	75025  7074054    	121393  7659527    	196418  8694122    	317811  10133369    	514229  12760665    	832040  17750866    	1346269  26356532    	2178309  37869525    	3524578  53579056    	5702887  80231257    	9227465  122458594    	14930352  191887930    	24157817  304987269    	39088169  490194555    	63245986  792546220    	";
		String s2 = "0  18571    	1  38120    	1  56202    	2  75750    	3  99697    	5  118757    	8  137816    	13  156387    	21  175447    	34  194506    	55  213077    	89  231648    	144  250708    	233  269279    	377  288338    	610  307398    	987  326458    	1597  346983    	2584  365554    	4181  384614    	6765  404162    	10946  423711    	17711  443259    	28657  462807    	46368  482356    	75025  501904    	121393  521452    	196418  541001    	317811  561038    	514229  582052    	832040  602089    	1346269  622126    	2178309  643141    	3524578  663178    	5702887  683215    	9227465  703252    	14930352  723289    	24157817  743815    	39088169  768250    	63245986  788776    	";
		Scanner scan = new Scanner(stringg);
		Scanner sc = new Scanner(s2);
		
		for (double c =-20; c<20; c+=.002){
			b.add(new spot(c, -9.8*c*c + 15*c, 0));
		}
		
		
		
		for(int c = 0; c<80; c++){
			System.out.print("t");
			try{ scan.nextInt();
				sc.nextInt();
			//chip.add(new SpinningCube(c,/100000, 0));
			chip.add(new SpinningCube(c, ((double)scan.nextInt())/100000, 0));
		//	chip.add(new SpinningCube(c, ((double)), 0));
			chip.add(new SpinningCube(c, ((double)sc.nextInt())/ 100000, 0));
			
			}catch(Exception e){}
		}
		
		
		//hypercube = new Hypercube(0,0,0,0);
		//hypercube.PrintPlanes();
		//mathclassgraph();
		/*Random rand = new Random();
		for(int c =0; c<500; c++){
			chip.add(new SpinningCube(50000000 - rand.nextInt(100000000),50000000 - rand.nextInt(100000000),50000000 - rand.nextInt(100000000)));
		}
		for(int c =0; c<400; c++){
			chip.add(new SpinningCube(500000000 - rand.nextInt(1000000000),500000000 - rand.nextInt(1000000000),500000000 - rand.nextInt(1000000000)));
		}
		//
		//graph();
		//mymap = new HeightMap(0, 0, 0);
		//double[][] map = {{0, 1, 0, 0, 0},
		//			{0, 0, 0, 0, 0},
		//			{0, 0, 0, 0, 0},
		//			{0, 0, 0, 0, 0},
		//			{0, 0, 0, 0, 0}};

		//mymap.map =		map;
		
		chip.add(new SpinningCube(2,0, 0));
		chip.add(new SpinningCube(3,0, 0));
		chip.add(new SpinningCube(4,0, 0));
		chip.add(new SpinningCube(4,1, 0));
		chip.add(new SpinningCube(4,2, 0));
		chip.add(new SpinningCube(4,3, 0));
		chip.add(new SpinningCube(4,4, 0));
		chip.add(new SpinningCube(4,4, 1));
		chip.add(new SpinningCube(4,4, 2));
		chip.add(new SpinningCube(4,3, 2));
		chip.add(new SpinningCube(4,2, 2));
		chip.add(new SpinningCube(4,2, 1));
		chip.add(new SpinningCube(-5,2, -1));
		chip.add(new SpinningCube(-5,2, 1));
		chip.add(new SpinningCube(-5,3, -1));
		chip.add(new SpinningCube(-5,3, 1));
		chip.add(new SpinningCube(-5,1, -1));
		chip.add(new SpinningCube(-5,1, 1));
		chip.add(new SpinningCube(-5,-2, 0));
		chip.add(new SpinningCube(-5,-2, -1));
		chip.add(new SpinningCube(-5,-2, 1));
		chip.add(new SpinningCube(-5,-1, 2));
		chip.add(new SpinningCube(-5,-1, -2));

		b.add(new Box(6, 2, -1, 8,  2, 1));
		b.add(new Box(6, 2, -1, 6, -2, 1));
		b.add(new Box(8,-2, -1, 6, -2, 1));
		b.add(new Box(8, 2, -1, 8, -2, 1));
		b.add(new Box(0, 0, 0, 0, 0, 0));
		b.add(new Box(1.0, 0, 0, 1.05, 0.05, 0.05));
		b.add(new Box(0, 1, 0, 0.05, 1.05, 0.05));
		b.add(new Box(0, -1, 0, 0.05, -1.05, 0.05));
		b.add(new Box(0, 0, 1, 0.05, 0.05, 1.05));
		b.add(new Box(-1, 0, 0, -1.05, 0.05, 0.05));
		//b.add(new Box(1000, 10000, -1000000, 8, -10000, 100000));
		 * 
		 */
		
		mymap =new Shapes.Sphere(0, 0, 0);
		//mandelbulb(100, 100, 100);
	}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		cosx = Math.cos(Math.toRadians(dir.dx - 90)); 
		sinx = Math.sin(Math.toRadians(dir.dx - 90));
		cosy = Math.cos(Math.toRadians(dir.dy));
		siny = Math.sin(Math.toRadians(dir.dy));
		dir.updateTrig();
		g.setColor(Color.white);
		if(pos.x<1 && pos.x>-1 && pos.y<1 && pos.y>-1){
			
		}else{
			
		}
		//hypercube.Render(dir, pos, g, k, l, disx, disy);
		for (int c =0; c<b.size(); c++){
			b.get(c).draw(dir, pos, g, k, l, disx, disy);
		}
		g.setColor(Color.green);
		for (int c =0; c<chip.size(); c++){
			chip.get(c).draw(dir, pos, g, k, l, disx, disy);
		}
		g.setColor(Color.blue);
		g.drawString(sinx + " " + cosx, 20, 40);
		g.drawString(mouse + "  " + dir.dx + "  " + pos.x + "  " + pos.y , 20, 20);
		g.drawLine(Game.X/2+5, Game.Y/2, Game.X/2 -5, Game.Y/2);
		g.drawLine(Game.X/2, Game.Y/2 + 5, Game.X/2, Game.Y/2 -5);
		if(mymap != null)
			mymap.draw(dir, pos, g, k, l, disx, disy, cosx, sinx, cosy, siny);
	}
	double speed = 1;
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		cosx = Math.cos(Math.toRadians(dir.dx - 90)); 
		sinx = Math.sin(Math.toRadians(dir.dx - 90));
		if(dir.dy>90){
			dir.dy=90;
		}else if(dir.dy<-90){
			dir.dy=-90;
		}
		double coscx = Math.cos(Math.toRadians(delta/20.0));
		double sincx = Math.sin(Math.toRadians(delta/20.0));
		//hypercube.update(coscx, sincx);
		for (int c =0; c<chip.size(); c++){
			chip.get(c).update(coscx, sincx);
		}
		dir.dx%=360;
		Input input= gc.getInput();
		if(input.isKeyDown(Input.KEY_W)){
			move(cosx, sinx, (int) (delta * speed));//pos.x+=delta * .0005;
		}if(input.isKeyDown(Input.KEY_S)){
			move(-cosx, -sinx, (int) (delta * speed));
		}if(input.isKeyDown(Input.KEY_D)){
			move( sinx,-cosx, (int) (delta * speed));//pos.y-=delta * .0005;
		}if(input.isKeyDown(Input.KEY_A)){
			move(-sinx, cosx,  (int) (delta *speed));//pos.y+=delta * .0005;
		} if(input.isKeyDown(Input.KEY_SPACE)){
			pos.z+=delta * .0005 * speed;
		} if(input.isKeyDown(Input.KEY_LSHIFT)){
			pos.z-=delta * .0005 * speed;
		}  if(input.isKeyDown(Input.KEY_J)){
			dir.dx+=delta * .05;//90;//
		}   if(input.isKeyDown(Input.KEY_L)){
			dir.dx-=delta * .05;//90;//
		}  if (input.isKeyPressed(Input.KEY_C)){
			chip.add(new SpinningCube(pos.x, pos.y, pos.z));
		} if (input.isKeyPressed(Input.KEY_Z)){
			chip.clear();
		} if (input.isKeyPressed(Input.KEY_N)){
			sbg.enterState(1);
		}if (input.isKeyPressed(Input.KEY_P)){
			if(paused){
				paused=false;
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
		//mymap.update(coscx, sincx, delta);
		frame+=delta/40.0;
		
	}

int centered=0;
int xcenter, ycenter;
	private void RecenterMouse(GameContainer gc) {
		
		dir.dx-=(Mouse.getX()-xcenter)*0.08;
		dir.dy+=(Game.Y-Mouse.getY()-ycenter)*0.08;
		//System.out.println(Display.getX());
		if(robot != null){
			robot.mouseMove(Game.X/2, Game.Y/2);
		}
		if(centered==2){
			setCenter(gc);
		}
		centered ++;
	}
	private void setCenter(GameContainer gc){
		if(robot != null){
			
		}robot.mouseMove(Game.X/2, Game.Y/2);
		xcenter = Mouse.getX();
		ycenter = Game.Y - Mouse.getY();
	}
	public int getID(){
		return 0;
	}
	
	void findAngle(int x, int y, int z){
		
	}
	public void move(double cosx, double sinx, int delta){
		pos.x-= sinx * (double)delta * .0005;
		pos.y+= cosx * (double)delta * .0005;
	}
	public void drawAxies(){
		for (int c =-20; c<=20; c++){
				for(double d = -.05; d<.05; d+=.01){
					b.add(new spot(c, d, 0));
					b.add(new spot(c, 0, d));
					
					b.add(new spot(0, c, d));
					b.add(new spot(d, c, 0));
					
					b.add(new spot(d, 0, c));
					b.add(new spot(0, d, c));
					
					
				}
			}
	}
	public void mathclassgraph (){
		/*for (double c =-20; c<20; c+=.002){
			b.add(new spot(c, 0, 0, Color.green));
			b.add(new spot(0, c, 0));
			b.add(new spot(0, 0, c));
		}*/
			
			
		for (double c =-20; c<20; c+=.02){
			//b.add(new spot(Math.log(c/2)/Math.log(Math.E), (c-2)/(c+8), c*Math.log(c/2)/Math.log(Math.E) ));
			//b.add(new spot(c/2, c/10, c, Color.yellow));
			//b.add(new spot(Math.cos(c),Math.sin(c), c));
			//b.add(new spot(-Math.sin(c), Math.cos(c), 1));
		}
		for (double x =-10; x<10; x+=.1){
			for (double y =-10; y<10; y+=.1){
				b.add(new spot(x, y, fxy(x,y)));//x*x*x - 3*x*y +y*y*y));
				
				//b.add(new spot(x, y, (x-y+10*Math.sqrt(x)-10*Math.sqrt(y))/(Math.sqrt(x)-10*Math.sqrt(y)) ));// Math.log((y*y)/(x*x))));	Math.log(-25 + (y*y)+(x*x)))    (3* Math.pow(Math.E, 2*y)* Math.sin(-2*x))/-x
				//b.add(new spot(x, y, 0));//.2*(x+1) + .5*(y)));
				//b.add(new spot(x, 0, y));//1.2*(x+5) + .7*(y)));
				//b.add(new spot(0, x, y));
				//b.add(new spot(x, y, x * Math.pow(Math.E, y) + y + 1));
			}
		}
		for(double t =-10; t<10; t+=.005){
			double x = t;
			double y = Math.sqrt(34-(x-3)*(x-3));
			if(y<10 && y>-10)
				b.add(new spot(x, y,  fxy(x,y), Color.red));//x*x*x - 3*x*y +y*y*y));
			x = t;
			y = -Math.sqrt(34-(x-3)*(x-3));
			if(y<10 && y>-10)
				b.add(new spot(x, y,  fxy(x, y), Color.red));//x*x*x - 3*x*y +y*y*y));
			MovingPoint p = new MovingPoint(x, y, 0);
			
			for(double c = 0; c<5; c+=.04){
				p.frames.add(new spot(x, c,  fxy(x, c), Color.blue)); }
			b.add(p);		
			
			p = new MovingPoint(x, y, 0);
			for(double c = 0; c<5; c+=.04){
				p.frames.add(new spot(x, -c,  fxy(x, -c), Color.blue));}
			b.add(p);
			p = new MovingPoint(x, y, 0);
			for(double c = 0; c<5; c+=.04){
				p.frames.add(new spot(c, x,  fxy(c, x), Color.blue)); }
			//b.add(p);		
			
			p = new MovingPoint(x, y, 0);
			for(double c = 0; c<5; c+=.04){
				p.frames.add(new spot(-c, x,  fxy(-c,x), Color.blue));}
			//b.add(p);
		}
		double x = 1, y = 8;
		chip.add(new SpinningCube(x, y, fxy(x,y) ));
		//chip.add(new SpinningCube(-4, -4, -8));
		/*for (double x =-10; x<10; x+=.2){
			for (double y =-10; y<10; y+=.2){
				MovingPoint p = new MovingPoint(x, y, 0);
				b.add(p);
				for(double c = 0; c<2*Math.PI; c+=.1){
					double dx = Math.cos(c), dy = Math.sin(c);
					
					
					p.frames.add(new spot(x, y, dx*Math.sin(Math.sqrt(x*x + y*y))/Math.sqrt(x*x+y*y)*x + dy*Math.sin(Math.sqrt(x*x+y*y))/Math.sqrt(x*x+y*y)*y));
				}
			}
		}*/
		
				
		/*
		for (double x =-10; x<10; x+=.05){
			for (double y =-10; y<10; y+=.05){
				for (double z =-10; z<10; z+=.05){
					double d = x*y + z* z*z *x -2*y*z;
					double c = 0;
					if(d<(c+.01) && d>(c-.01)){
					
					b.add(new spot(x, z, y) );
					}
				}
			}
		}*/
	}
	
	public double fxy (double x, double y){
		return Math.sqrt(25-x*x-y*y);//1/Math.sin(2	*Math.sqrt(x*x + y*y)) ;
	}
	public static double frame=0;										
	
	public void graph(){
		int scale = 10;
		//for (int x = -2000; x<2000; x++){
		for(double x = -10; x<10; x+= .2){
			for(double y = -10; y<10; y+= .2){
				for(double z = -10; z<10; z+= .2){
		
					double r = Math.sqrt(x* x  + y * y + z* z);
					double o = Math.atan(y/x);
					double p = Math.atan(Math.sqrt(x* x+ y*y)/z);
					double n = 8;
					b.add(new spot(Math.pow(r, n)* Math.sin(n*p)*Math.cos(n* o), Math.pow(r, n)*Math.sin(n*p)*Math.sin(n*o), Math.pow(r, n) * Math.cos(n* p)     ));
					//b.add(new spot ( (( (3* z* z - x* x - y* y)*x * (x*x - 3 * y*y ))/(x* x + y* y)), 
					//		(( (3* z* z - x* x - y* y)*y * ( 3* x*x -  y*y ))/(x* x + y* y))
					//		, z*(z* z- 3* x* x- 3* y* y)  ));
					System.out.println(x + " " + y + " " + z);
					//Vector f = function(x, y);
				//	b.add(new spot(x/ scale, y/ scale, f.dy/ scale));//(((double) x/ (scale * 100)), f.dx / scale, f.dy / scale));//, 
				//	f.dy= - f.dy;
				//	b.add(new spot(x/ scale, y / scale, f.dy / scale));
				}
			}
		}
	}
	public Vector function(double x, double y){
		double f = Math.sqrt(16 - y * y - x* x);//Math.sqrt(-Math.pow((5 - Math.sqrt(x* x + y* y)), 2)  + 1);//(Math.tan(Math.sqrt( x  * x + y * y)));// + Math.cos(y);//
		System.out.println(f + " " + x + " " + y);
		return new Vector(0,  f );
	}
	public Vector function (double x){
		//Vector num = new Vector(.9510565163, .3090169944);
		Vector num = new Vector(.9995065604, .0314107591);
		double f = Math.pow(2, x/100);
		Vector t = new Vector (num.dx, num.dy);
		
		for(int c = 0; c<x; c++){
			t = mult(num, t);
		
		System.out.println(x/10 + "   " + num.dx + " " +  num.dy + "") ;
		}
		t.dx *=f;
		t.dy *=f;
		
		
		return t;
	}
	public Vector mult(Vector a, Vector b){
		double fir = a.dx * b.dx;
		fir -= a.dy * b.dy;
		double im = b.dy * a.dx + a.dy * b.dx ;
		
		return new Vector(fir, im);
		
	}
	
	public void mandelbulb(double width, double height, double depth){
		double param = 6;
		for(double dx =0; dx<width; dx+=.5){
			double x =(double) dx * 2.0 / width - 1.0;
			for(double dy = 0; dy < height; dy+=.5){
				double y = (double) dy * 2.0 /width - 1.0;
				for(double dz = 0; dz < height; dz +=.5){
					double z = (double) dz * 2.0 / depth - 1.0;
					
					int iterations = -1;
					Point C = new Point(x, y, z);
					Point Z = new Point(0, 0, 0);
					
					while (r(Z) < 1000 && iterations < 21){
						Z = add(exp(Z, param), C);
						iterations ++;
					}
					if(iterations < 21 && iterations >= 5){
						b.add(new spot(dx, dy, dz));
						
					}
					
				}
			}
		}
		
	}
	
	public double r (Point p){
		return Math.sqrt(p.x* p.x +  p.y * p.y + p.z* p.z);
	}
	public Point add (Point a, Point b){
		return new Point(a.x + b.x, a.y + b.y, a.z + b.z);
	}
	public Point exp(Point v, double n){
		double t = theta(v);
		double p = phi(v);
		
		double k = Math.pow(r(v), n);
		
		return new Point (k * Math.sin(n*t) * Math.cos(n*p), k * Math.sin(n*t)*Math.sin(n * p), k *Math.cos(n* t));
	}
	public double phi(Point p){
		return Math.atan(p.y/(p.x + .000001));
	}
	public double theta (Point p){
		return Math.acos(p.z / (r(p)  + .0000001));
	}
	
	
	
	
	
	
	
}
