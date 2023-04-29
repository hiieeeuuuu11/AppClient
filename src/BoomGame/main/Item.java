package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import static java.lang.Integer.SIZE;
import java.util.Random;
import javax.swing.ImageIcon;


public class Item {
    private int x, y;
    private int bitItem;
    private Image image;
    private Random random = new Random();

    public final Image[] ITEM_IMAGE = {
        new ImageIcon(getClass().getResource("/BoomGame/images/gomu.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/item_shoe.gif")).getImage(),
    };

    public Item(int x, int y) {
        int rd = random.nextInt(2);
        this.x = x;
        this.y = y;
        this.bitItem = rd;
        this.image = ITEM_IMAGE[rd];
    }

    public int getBitItem() {
        return bitItem;
    }

    public void setBitItem(int bitItem) {
        this.bitItem = bitItem;
    }

   
   public Rectangle getRect() {
    Rectangle rectangle = new Rectangle(x, y, SIZE - 5, SIZE - 3);
    return rectangle;
}

    
    void drawItem(Graphics2D g2d) {
        for (int i = 0; i < ITEM_IMAGE.length; i++) {
            if (bitItem == i) {
                g2d.drawImage(ITEM_IMAGE[i], x, y, SIZE-3 , SIZE-3, null);
                break;
            }
        }
    }
}
