/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Admin
 */
public class MapItem {
    private int x, y;
    public int bit;
    public static final int SIZE = 32;
       private boolean destroyable;
    
  



    public final Image[] MY_IMAGE = {
           new ImageIcon(getClass().getResource("/BoomGame/images/stone.png")).getImage(),

        new ImageIcon(getClass().getResource("/BoomGame/images/boxgo.png")).getImage(),
          new ImageIcon(getClass().getResource("/BoomGame/images/boxgo2.png")).getImage(),

        new ImageIcon(getClass().getResource("/BoomGame/images/boxgo2.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/boxsatmain.png")).getImage(),
       new ImageIcon(getClass().getResource("/BoomGame/images/boxsatmain.png")).getImage(),

       new ImageIcon(getClass().getResource("/BoomGame/images/onepiece.gif")).getImage(),


    }; 

    public MapItem(int x, int y, int bit) {
        this.x = x;
        this.y = y;
        this.bit = bit;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getBit() {
        return bit;
    }

    public void setBit(int bit) {
        this.bit = bit;
    }
    
     public void drawMapItem(Graphics2D g2d) {
        if (bit != 0) {
            g2d.drawImage(MY_IMAGE[bit - 1], x, y, SIZE, SIZE , null);
        }
     }
    public Rectangle getRect(){
         Rectangle rectangle = new Rectangle(x,y,SIZE,SIZE);
         return rectangle;
    }

 
     
     
}
