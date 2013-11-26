package com.gannon.asm.classgenerator;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class DisplayClock{

    public static void main(String args[]){
        //Create one window frame
        Locale.setDefault(new   Locale( "USA "));
        JFrame JFObj =  new JFrame();
        JFObj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFObj.setSize(400,400);
        JFObj.setLocation(200, 200);
        JFObj.setVisible(true);


        int hour = 0, minute = 0;
        //Create two dialog for input
       //try{
            hour =  Integer.parseInt(JOptionPane.showInputDialog("Input number of hour:"));
            minute =  Integer.parseInt(JOptionPane.showInputDialog("Input number of minute:"));
            if(hour>12 || hour <0)
                main(args);
            if(minute>60 || minute<0)
                main(args);
        //}catch(Exception e){
        //    main(args);
        //}

        
        Graphics g = JFObj.getGraphics();
        //Draw clock
        SimpleClock s = new SimpleClock();
        s.setHour(hour);
        s.setMinute(minute);
        s.draw(g);
    }

}
