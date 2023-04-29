package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;

public class Boss {
private int x, y;
private int orient;
private Image image;
private Random random = new Random();
public static final int LEFT = 0, RIGHT = 1, UP = 2, DOWN = 3;
public static final int SIZE = MapItem.SIZE;

public final Image[] BOSS = {
    new ImageIcon(getClass().getResource("/BoomGame/images/quaivat1_left.png")).getImage(),
    new ImageIcon(getClass().getResource("/BoomGame/images/quaivat1_right.png")).getImage(),
    new ImageIcon(getClass().getResource("/BoomGame/images/quaivat1_up.png")).getImage(),
    new ImageIcon(getClass().getResource("/BoomGame/images/quaivat1_down.png")).getImage(),
};

public Boss(int x, int y, int orient) {
    this.x = x;
    this.y = y;
    this.orient = orient;
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

public void changeOrient(int newOrient) {
    orient = newOrient;
}

public void creatOrient() {
    int percent = random.nextInt(100);
    if (percent > 95) {
        int newOrient = random.nextInt(4);
        changeOrient(newOrient);
        image = BOSS[newOrient];
    }
}

public void drawBoss(Graphics2D g2d) {
    creatOrient();
    g2d.drawImage(image, x, y, SIZE-3, SIZE-3, null);
}

public boolean checkMove(ArrayList<MapItem> arrMapItem) {
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
public boolean checkMoveBoom(ArrayList<Boom>arrBoom){
    for(int i=0;i<arrBoom.size();i++){
        Rectangle rectangle =getRect().intersection(arrBoom.get(i).getRect());
          if( rectangle.isEmpty()==false &&arrBoom.get(i).isCheckBoom()==0){
              return false;
          }
    }
    return true;
}

public void moveBoss(ArrayList<MapItem> arrMapItem,ArrayList<Boom> arrBoom, int t) {
    int speed = 1;
    int xRaw = x;
    int yRaw = y;
    switch (orient) {
        case LEFT -> xRaw -= speed;
        case RIGHT -> xRaw += speed;
        case UP -> yRaw -= speed;
        case DOWN -> yRaw += speed;
    }

    int xRaw1 = x;
    int yRaw1 = y;
    x = xRaw;
    y = yRaw;
    boolean checkMoveBoss = checkMove(arrMapItem);
    boolean checkMoveBossBoom = checkMoveBoom(arrBoom);
  
    if (checkMoveBoss == true) {
        x = xRaw1;
        y = yRaw1;
      
    }
    if (checkMoveBossBoom == false) {
        x = xRaw1;
        y = yRaw1;
    }
    creatOrient();
}

public Rectangle getRect() {
    Rectangle rectangle = new Rectangle(x , y, SIZE+3, SIZE+3);
    return rectangle;
    }
}
