package com.gannon.asm.classgenerator;
import java.awt.Color;
import java.awt.Graphics;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class SimpleClock {
    double hour;
    double minute;
    
    public void setHour(double hour){
        this.hour = hour;
    }
    public void setMinute(double minute){
        this.minute = minute;
    }
    //Draw clock face
    private void drawClockFace(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(100, 100, 200,200);
        g.setColor(Color.black);
        g.drawOval(100, 100, 200,200);
        g.drawString("12", 200, 115);
        g.drawString("6",200, 295);
        g.drawString("3", 290, 200);
        g.drawString("9",105, 200);
        g.drawString("Current time:"+(int)this.hour+":"+(int)this.minute,150,350);
        
    }
    //Draw hour hand
    private void drawHourHand(Graphics g){
       double xNew = 200+70*Math.cos((90.0-(this.hour+this.minute/60.0)*30.0)*(Math.PI/180.0));
       double yNew = 200-70*Math.sin((90.0-(this.hour+this.minute/60.0)*30.0)*(Math.PI/180.0));
       g.drawLine(200, 200, (int)xNew, (int)yNew);    
    }
    //Draw minute hand
    private void drawMinuteHand(Graphics g){
       double xNew = 200+90*Math.cos((90.0-this.minute*6.0)*(Math.PI/180.0));
       double yNew = 200-90*Math.sin((90.0-this.minute*6.0)*(Math.PI/180.0));
       g.drawLine(200, 200, (int)xNew, (int)yNew);
    }
    
    public void draw(Graphics g){
        this.drawClockFace(g);
        this.drawHourHand(g);
        this.drawMinuteHand(g);
    }

}
