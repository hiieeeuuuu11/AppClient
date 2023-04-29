/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.lang.Integer.SIZE;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Boom {
    private int x,y;
    public int checkBoom;
    private Image image;
    private int imageIndex=0;
    private int lengthBoom;
    public final Image[] IMAGE_BOOM={
        new ImageIcon(getClass().getResource("/BoomGame/images/bomb.gif")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/item_bomb.gif")).getImage(),

    };
    
    public Boom(int x,int y,int lengthBoom){
        this.x=x;
        this.y=y;
        this.lengthBoom=lengthBoom;
        this.checkBoom=1;
        this.image=IMAGE_BOOM[0];
        boomBang();
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

    public int isCheckBoom() {
        return checkBoom;
    }

    public void setCheckBoom(int checkBoom) {
        this.checkBoom = checkBoom;
    }
    
    public void drawBoom(Graphics2D g2d){
        imageIndex++;
        int ImageIndex = 0;
        image=IMAGE_BOOM[ImageIndex/5%IMAGE_BOOM.length];
        g2d.drawImage(image, x, y,SIZE,SIZE,null); 
    }
    public Rectangle getRect(){
        Rectangle rectangle=new Rectangle(x,y,SIZE,SIZE);
        return rectangle;
    }
    public BoomBang boomBang(){
        int xRaw=x;
        int yRaw=y;
        int lenghtWave=this.lengthBoom;
        BoomBang boomBang=new BoomBang(xRaw, yRaw, lenghtWave);
        return boomBang;
    }
}
