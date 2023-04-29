/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import static java.lang.Integer.SIZE;
import static BoomGame.main.BoomBang.score;

import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import BoomGame.sounds.Sound;
/**
 * @author Admin
 */
public class Player {
    private int x, y;
    private int orient;
    public Image image;
    private double timeMove;
    private boolean isPlayerRun = false;
    public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
    private int imageIndex = 0;
    private int lengthBoomBang = 1;
    private int SoBom = 30;
    private int speed = 2;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public final Image[] IMAGES_PLAYER_LEFT = {
            new ImageIcon(getClass().getResource("/BoomGame/images/khokho_left.png")).getImage(),

    };
    public final Image[] IMAGES_PLAYER_RIGHT = {
            new ImageIcon(getClass().getResource("/BoomGame/images/khokho_right.png")).getImage(),
    };
    public final Image[] IMAGES_PLAYER_UP = {
            new ImageIcon(getClass().getResource("/BoomGame/images/khokho_up.png")).getImage(),
    };
    public final Image[] IMAGES_PLAYER_DOWN = {
            new ImageIcon(getClass().getResource("/BoomGame/images/khokho_down.png")).getImage(),
    };


    public Player(int x, int y, int orient, double timeMove) {
        this.x = x;
        this.y = y;
        this.orient = orient;
        this.timeMove = timeMove;
    }

    public void changeOrient(int newOrient) {
        orient = newOrient;
        isPlayerRun = true;
    }

    public boolean isRun() {
        return isPlayerRun;
    }

    public int getSoBom() {
        return SoBom;
    }

    public void setSoBom(int SoBom) {
        this.SoBom = SoBom + 1;
    }


    public void setMoveBoom(ArrayList<Boom> arrBoom) {
        for (int i = 0; i < arrBoom.size() - 1; i++) {
            Rectangle rectangle = getRect().intersection(arrBoom.get(i).getRect());
            if (rectangle.isEmpty() == true) {
                arrBoom.get(i).setCheckBoom(0);
            }
        }
    }

    public boolean checkMoveBoom(ArrayList<Boom> arrBoom) {
        setMoveBoom(arrBoom);
        for (int i = 0; i < arrBoom.size() - 1; i++) {
            Rectangle rectangle = getRect().intersection(arrBoom.get(i).getRect());
            //khi mới đặt boom thì sẽ có va chạm tại chỗ đặt nhưng khi player đi tiếp thì setcheckBoom=0
            if (rectangle.isEmpty() == false && arrBoom.get(i).isCheckBoom() == 0) {
                return false;
            }
        }
        return true;
    }

    public void drawPlayer(Graphics2D g2d) {
        switch (orient) {
            case LEFT:
               
                    g2d.drawImage(IMAGES_PLAYER_LEFT[0], x, y, SIZE, SIZE, null);

               
                break;
            case RIGHT:
                
                    g2d.drawImage(IMAGES_PLAYER_RIGHT[0], x, y, SIZE , SIZE, null);
               
                break;
            case UP:
               
                    g2d.drawImage(IMAGES_PLAYER_UP[0], x, y,SIZE, SIZE, null);
               
                break;
            case DOWN:
                
                    g2d.drawImage(IMAGES_PLAYER_DOWN[0], x, y, SIZE, SIZE , null);
                
                break;
            default:
                throw new AssertionError();
        }
        isPlayerRun = false;
        imageIndex++;
        // g2d.drawImage(IMAGES_EFFECT[imageIndex/7%IMAGES_EFFECT.length], x=5, y,SIZE+20,SIZE+20,null);


    }


    void move(ArrayList<MapItem> arrMapItem, ArrayList<Boom> arrBoom) {
        int dx = x;
        int dy = y;

        switch (orient) {
            case LEFT:
                dx -= speed;
                break;
            case RIGHT:
                dx += speed;
                break;
            case DOWN:
                dy += speed;
                break;
            case UP:
                dy -= speed;
                break;
            default:
                throw new AssertionError();
        }
        int dx1=x;
        int dy1=y;
        x = dx;
        y = dy;
        boolean checkMovePlayer = checkMoveMap(arrMapItem);
        boolean checkMovePlayerToBoom = checkMoveBoom(arrBoom);
        if (checkMovePlayer == true) {
           x = dx1;
           y = dy1;
        }
        if (checkMovePlayerToBoom == false) {
            x = dx;
            y = dy;
        }
        //System.out.println(x+" "+y);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, SIZE-3, SIZE-3 );
        return rectangle;
    }

    public Boom DatBoom(ArrayList<Boom> arrBoom) {
        int xRaw = this.x + SIZE / 2;
        int yRaw = this.y + SIZE / 2;
        int xBoom = xRaw - xRaw % SIZE ;
        int yBoom = yRaw - yRaw % SIZE ;
        int lengthBoom = this.lengthBoomBang;
        Boom boom = new Boom(xBoom, yBoom, lengthBoom);
        return boom;
    }

    public boolean checkMoveMap(ArrayList<MapItem> arrMapItem) {
        for (MapItem mapItem : arrMapItem) {
            if (mapItem.bit >= 1 && mapItem.bit <= 9) {
                Rectangle rectangle = getRect().intersection(mapItem.getRect());
                if (rectangle.isEmpty() == false) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkDieToBoss(ArrayList<Boss> arrBoss) {
        for (int i = 0; i < arrBoss.size(); i++) {
            Rectangle rectangle = getRect().intersection(arrBoss.get(i).getRect());
            if (rectangle.isEmpty() == false) {
                return true;
            }
        }
        return false;
    }

    public void moveItem(ArrayList<Item> arrItem) {
        for (int i = 0; i < arrItem.size(); i++) {
            Rectangle rectangle = getRect().intersection(arrItem.get(i).getRect());
            if (rectangle.isEmpty() == false) {
                if (arrItem.get(i).getBitItem() == 0) {
                    score+=50;
                    arrItem.remove(i);
//
                } else if (arrItem.get(i).getBitItem() == 1) {
                    setSpeed(getSpeed() + 1);
                    //System.out.println(getSpeed());
                    arrItem.remove(i);
                }
                          Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/moveItem.wav"));
                          clip.start();

            }
        }
    }

}