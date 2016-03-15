package Drawing;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author ahietane
 */
public class Circle extends DrawingObject{
    
   
    public Circle(double x, double y, double s){
        this.x=x; this.y=y; this.s=s;
    }
    public Circle(double x, double y, double s, Color c){
        this.x=x; this.y=y; this.s=s; this.c=c;
    }
    
    public void Draw (double dx, double dy, double ds, Graphics g){
    	
    	Point2D p = new Point2D(x, y).toViewSpace(dx, dy, ds);
        double ns=s*ds;
        
        //g.setColor(new Color(.3f, .5f, .67f));
       
        g.setColor(c);
        g.fillOval((float)p.x- .5f* (float)ns, (float)p.y - .5f *(float)ns, (float)ns, (float)ns);
    }
    
}