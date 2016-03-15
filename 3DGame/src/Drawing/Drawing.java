package Drawing;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JOptionPane;
import java.util.*;
import net.java.games.input.Component;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Drawing extends BasicGameState{
	public ArrayList<Color> colors;
    int ip, state = 1, palateoffset = 100, palateheight = javagame.Game.Y - 200;
    double dx =0, dy=0, ds=0, nsds =0, brushsize =1;
    Color color = Color.white;
    DrawingObject temp;
    Boolean Selecting=false;
    
    Circle tc = new Circle (0,0, brushsize*10);
    
    
    ArrayList<DrawingObject> cs = new ArrayList<DrawingObject>();
    public Drawing(int i){
        this.ip = i;
    }
    @Override
    public int getID() {
        return ip;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        cs = FileLoader.Open();
        colors = new ArrayList<Color>();
        
        colors.add(new Color(0,0,0));
        colors.add(new Color(128,128,128));
        colors.add(new Color(192,192,192));
        colors.add(new Color(255,255,255));
        colors.add(new Color(128,0,0));
        colors.add(new Color(255,0,0));
        colors.add(new Color(128,128,0));
        colors.add(new Color(255,255,0));
        colors.add(new Color(0,128,0));
        colors.add(new Color(0,255,0));
        colors.add(new Color(0,128,128));
        colors.add(new Color(0,255,255));
        colors.add(new Color(0,0,128));
        colors.add(new Color(0,0,255));
        colors.add(new Color(128,0,128));
        colors.add(new Color(255,0,255));
        colors.add(new Color(139,69,19));
        colors.add(new Color(133,94,66));
        colors.add(new Color(166,128,100));
        
        
        
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //g.drawOval(3, 3, 3, 3);
         
        for (int c =0; c< cs.size(); c++){
            cs.get(c).Draw(dx, dy, nsds, g);
        }
        g.setColor(color);
        g.drawString("DS: " + ds, 40, 40);
        tc.c=color;
        tc.Draw(-330, 200, 1, g);
        
        for (int c = 0; c < colors.size(); c++){
        	g.setColor(colors.get(c));
        	g.fillRect(palateoffset + c*10, palateheight, 10, 200);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        try {
			Thread.sleep(25);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Input input = gc.getInput();
        int x = input.getMouseX();
        int y = input.getMouseY();
        
        ds+=Mouse.getDWheel()/120;
        nsds = Math.pow(2, ds);
        
        if( input.isMousePressed(0) || (input.isMouseButtonDown(0) && input.isKeyDown(Input.KEY_LSHIFT))){
            if(x <100 ){
                color = new Color(y*40000 );
                colors.add(color);
            }else if(y > javagame.Game.Y - 200 ){
            	if((x - 100)/10 < colors.size()){
            		color = colors.get((x-100)/10);
            	}
        	}else{
            	if(state == 1)
            		cs.add(new Circle((x - javagame.Game.X*.5)/ nsds- dx , (y  - javagame.Game.Y*.5) / nsds- dy, (brushsize*10)/nsds , color));
            	if(state == 3 || state == 2){
            		if(!Selecting){
            			if(state == 3)
            				temp = new DrawingLine((x - javagame.Game.X*.5)/ nsds- dx , (y  - javagame.Game.Y*.5) / nsds- dy, (x - javagame.Game.X*.5)/ nsds- dx , (y  - javagame.Game.Y*.5) / nsds- dy);
            			if(state == 2)
            				temp = new DrawingRect((x - javagame.Game.X*.5)/ nsds- dx , (y  - javagame.Game.Y*.5) / nsds- dy, (x - javagame.Game.X*.5)/ nsds- dx , (y  - javagame.Game.Y*.5) / nsds- dy);
            				
            			Selecting = true;
            			cs.add(temp);
            			temp.c=color;
            		}else{
            			temp.x1=(x - javagame.Game.X*.5)/ nsds- dx;
            			temp.y1=(y  - javagame.Game.Y*.5) / nsds- dy;
            			
            			Selecting = false;
            		}
            	}
            
            }
        }
        
        if(Selecting){
        	temp.x1=(x - javagame.Game.X*.5)/ nsds- dx;
			temp.y1=(y  - javagame.Game.Y*.5) / nsds- dy;
        }
        if (input.isKeyPressed(Input.KEY_1)){
            state=1;
        }if (input.isKeyPressed(Input.KEY_2)){
            state=2;
        }if (input.isKeyPressed(Input.KEY_3)){
            state=3;
        }
        
        
        
        
        if (input.isKeyPressed(Input.KEY_G)){
            color=Color.green;
        }if (input.isKeyPressed(Input.KEY_S)){
            FileLoader.Save(cs);
        }
        if (input.isKeyPressed(Input.KEY_W)){
            color=Color.white;
        }
        if (input.isKeyPressed(Input.KEY_Y)){
            color=Color.yellow;
        }
        if (input.isKeyPressed(Input.KEY_B)){
            color=Color.blue;
        }if (input.isKeyPressed(Input.KEY_O)){
            color=Color.orange;
        }if (input.isKeyPressed(Input.KEY_R)){
            color=Color.red;
        }if (input.isKeyPressed(Input.KEY_UP)){
            brushsize++;
            tc.s=brushsize*10;
        }if (input.isKeyPressed(Input.KEY_DOWN)){
            brushsize--;
            tc.s=brushsize*10;
        }if (input.isKeyPressed(Input.KEY_C)){
            color=Color.cyan;
        }if (input.isKeyPressed(Input.KEY_BACK)){
            cs.remove(cs.size()-1);
        }
        
        
        if(input.isMouseButtonDown(2)){
            dx += Mouse.getDX() / nsds;
            dy -= Mouse.getDY() / nsds;
        }
        
    }
    
}
