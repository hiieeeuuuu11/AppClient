package BoomGame.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import static BoomGame.main.MapItem.SIZE;
import BoomGame.sounds.Sound;
public class BoomBang {

    private int x, y;
    private int lenghtLeft ;
    private int lenghtRight ;
    private int lenghtDown ;
    private int lenghtUp ;

    private int imageIndex = 0;
    static int  score=0;
    static int TIME_PLAY=100;
    public final Image[] BOOM_BANG = {
        new ImageIcon(getClass().getResource("/BoomGame/images/bombbang_left1.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/bombbang_right1.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/bombbang_up1.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/bombbang_down1.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/bombbang.png")).getImage(), //        new ImageIcon(getClass().getResource("/images/bombbang_left2.png")).getImage(),
    //        new ImageIcon(getClass().getResource("/images/bombbang_right2.png")).getImage(),
    //        new ImageIcon(getClass().getResource("/images/bombbang_up2.png")).getImage(),
    //        new ImageIcon(getClass().getResource("/images/bombbang_down2.png")).getImage()
    };

    public final Image[] BOSS_DIE = {
        new ImageIcon(getClass().getResource("/BoomGame/images/ghost.png")).getImage(),
       // new ImageIcon(getClass().getResource("/images/ghost_1.png")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/ghost2.png")).getImage(),};
    public final Image[] MAP_DESTROY = {
        new ImageIcon(getClass().getResource("/BoomGame/images/")).getImage(),
        new ImageIcon(getClass().getResource("/BoomGame/images/")).getImage(),};
    private int xBossDie=0;
    private int yBossDie=0;

    public BoomBang(int x, int y, int lenghtWave) {
        //lenghtWave=2;
        this.x = x;
        this.y = y;
        this.lenghtDown = lenghtWave;
        this.lenghtRight = lenghtWave;
        this.lenghtUp = lenghtWave;
        this.lenghtLeft = lenghtWave;
        //System.out.println(lenghtLeft);
    }

    public static void time_p(){
        TIME_PLAY--;
    }
    public void drawBoomBang(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        //drawMid(g2d);
        drawLeft(g2d, arrMapItem);
        drawRight(g2d, arrMapItem);
        drawUp(g2d, arrMapItem);
        drawDown(g2d, arrMapItem);
        if (xBossDie != 0 || yBossDie != 0) {
            imageIndex++;
            Image image = BOSS_DIE[imageIndex / 50 % BOSS_DIE.length];
            g2d.drawImage(image, xBossDie,yBossDie, null);

        }
    }

    private void drawMid(Graphics2D g2d) {
        g2d.drawImage(BOOM_BANG[4], x , y, null);
    }

    private void drawLeft(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghtLeft; j++) {
            int xRaw = x - j * SIZE;
            int yRaw = y;
            if (j == lenghtLeft) {
                g2d.drawImage(BOOM_BANG[0], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4 || arrMapItem.get(i).bit == 5) {
                        arrMapItem.remove(i);
                        lenghtLeft = j;
                    } else if (arrMapItem.get(i).bit != 2 || arrMapItem.get(i).bit != 4 || arrMapItem.get(i).bit != 5 || arrMapItem.get(i).bit != 0) {
                        lenghtLeft = j;
                    }
                }
            }

        }
    }

    private void drawRight(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghtRight; j++) {
            int xRaw = x + j * SIZE;
            int yRaw = y;
            if (j == lenghtRight) {
                g2d.drawImage(BOOM_BANG[1], x, y, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4 || arrMapItem.get(i).bit == 5) {
                        arrMapItem.remove(i);
                        lenghtRight = j;
                    } else if (arrMapItem.get(i).bit != 2 || arrMapItem.get(i).bit != 4 || arrMapItem.get(i).bit != 5 || arrMapItem.get(i).bit != 0) {
                        lenghtRight = j;
                    }
                }
            }
        }
    }

    private void drawUp(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghtUp; j++) {
            int xRaw = x ;
            int yRaw = y - j * SIZE ;
            if (j == lenghtUp) {
                g2d.drawImage(BOOM_BANG[2], xRaw, yRaw, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4 || arrMapItem.get(i).bit == 5) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        //g2d.drawImage(MAP_DESTROY[imageIndex/20 % MAP_DESTROY.length], xRaw, yRaw, null);

                        lenghtUp = j;

                    } else if (arrMapItem.get(i).bit != 2 || arrMapItem.get(i).bit != 4 || arrMapItem.get(i).bit != 5 || arrMapItem.get(i).bit != 0) {
                         lenghtUp = j;
                    }
                }
            }
        }

    }

    private void drawDown(Graphics2D g2d, ArrayList<MapItem> arrMapItem) {
        for (int j = 1; j <= lenghtDown; j++) {
            int xRaw = x ;
            int yRaw = y + j * SIZE  ;
            if (j == lenghtDown) {
                g2d.drawImage(BOOM_BANG[3], x, y, null);
            }
            for (int i = 0; i < arrMapItem.size(); i++) {
                Rectangle rectangle = getRect(xRaw, yRaw).intersection(arrMapItem.get(i).getRect());
                if (rectangle.isEmpty() == false) {
                    if (arrMapItem.get(i).bit == 2 || arrMapItem.get(i).bit == 4 || arrMapItem.get(i).bit == 5) {
                        arrMapItem.remove(i);
                        imageIndex++;
                        g2d.drawImage(MAP_DESTROY[imageIndex / 20 % MAP_DESTROY.length], xRaw, yRaw, null);
                        lenghtDown = j;
                    } else if (arrMapItem.get(i).bit != 2 || arrMapItem.get(i).bit != 4 || arrMapItem.get(i).bit != 5 || arrMapItem.get(i).bit != 0) {
                        lenghtDown = j;
                    }
                }
            }
        }
    }

    public Rectangle getRect(int xRaw, int yRaw) {
        Rectangle rectangle = new Rectangle(xRaw , yRaw , SIZE , SIZE);
        return rectangle;
    }

    public void checkBoomToBoss(ArrayList<Boss> arrBoss) {
                  Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/bang.wav"));

        for (int i = 0; i < arrBoss.size(); i++) {
            try {
                Rectangle rectangle = getRect(x, y).intersection(arrBoss.get(i).getRect());
                if (rectangle.isEmpty() == false) {

                    xBossDie = arrBoss.get(i).getX();
                    yBossDie = arrBoss.get(i).getY();
                    arrBoss.remove(i);
                    score+=50;
                    clip.start();
                }
                for (int j = 1; j <= lenghtLeft; j++) {
                    int xRaw = x - j * SIZE ;
                    int yRaw = y ;
                    Rectangle rectangleLeft = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangleLeft.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        score+=50;
                        clip.start();

                    }
                }
                for (int j = 1; j <= lenghtRight; j++) {
                    int xRaw = x + j * SIZE ;
                    int yRaw = y ;
                    Rectangle rectangleRight = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangleRight.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        score+=50;
                         clip.start();

                    }
                }
                for (int j = 1; j <= lenghtUp; j++) {
                    int xRaw = x ;
                    int yRaw = y - j * SIZE ;
                    Rectangle rectangleUp = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangleUp.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        score+=50;
                        clip.start();

                    }
                }
                for (int j = 1; j <=lenghtDown; j++) {
                    int xRaw = x ;
                    int yRaw = y + j * SIZE ;
                    Rectangle rectangleDown = getRect(xRaw, yRaw).intersection(arrBoss.get(i).getRect());
                    if (rectangleDown.isEmpty() == false) {
                        xBossDie = arrBoss.get(i).getX();
                        yBossDie = arrBoss.get(i).getY();
                        arrBoss.remove(i);
                        score+=50;
                        clip.start();
                    }
                }
            } catch (Exception e) {

            }
        }
    }

    public boolean checkBoomToPlayer(ArrayList<BoomBang> arrBoomBang, Player player) {
                          
     
        for (int i = 0; i < arrBoomBang.size(); i++) {
            try {
                Rectangle rectangle = getRect(x , y ).intersection(player.getRect());

                if (rectangle.isEmpty() == false) {
                    return true;
                }

                for (int j = 1; j <= lenghtLeft; j++) {
                    int xRaw = x - j * SIZE ;
                    int yRaw = y ;
                    Rectangle rectangleLeft = getRect(xRaw, yRaw).intersection(player.getRect());
                    if (rectangleLeft.isEmpty() == false) {
                        return true;
                    }

                }

                for (int j = 1; j <= lenghtRight; j++) {
                    int xRaw = x + j * SIZE;
                    int yRaw = y;
                    Rectangle rectangleRight = getRect(xRaw, yRaw).intersection(player.getRect());
                    if (rectangleRight.isEmpty() == false) {
                        return true;
                    }

                }

                for (int j = 1; j <= lenghtUp; j++) {
                    int xRaw = x ;
                    int yRaw = y - j * SIZE ;
                    Rectangle rectangleUp = getRect(xRaw, yRaw).intersection(player.getRect());
                    if (rectangleUp.isEmpty() == false) {
                        return true;
                    }

                }

                for (int j = 1; j <= lenghtDown; j++) {
                    int xRaw = x ;
                    int yRaw = y + j * SIZE ;
                    Rectangle rectangleDown = getRect(xRaw, yRaw).intersection(player.getRect());
                    if (rectangleDown.isEmpty() == false) {
                        return true;
                    }

                }

            } catch (Exception e) {

            }
        }
        return false;

    }

    public void checkBoomToBoom(ArrayList<Boom> arrBoom, ArrayList<Integer> timeBoom) {
        for (int i = 0; i < arrBoom.size(); i++) {
            try {
                Rectangle rectangle = getRect(x , y).intersection(arrBoom.get(i).getRect());

                if (rectangle.isEmpty() == false) {
                    timeBoom.set(i, 0);
                }

                for (int j = 1; j <= lenghtLeft; j++) {
                    int xRaw = x - j * SIZE ;
                    int yRaw = y ;
                    Rectangle rectangleLeft = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                    if (rectangleLeft.isEmpty() == false) {
                        timeBoom.set(i, 0);
                    }

                }

                for (int j = 1; j <= lenghtRight; j++) {
                    int xRaw = x + j * SIZE ;
                    int yRaw = y ;
                    Rectangle rectangleRight = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                    if (rectangleRight.isEmpty() == false) {
                        timeBoom.set(i, 0);
                    }

                }

                for (int j = 1; j <= lenghtUp; j++) {
                    int xRaw = x ;
                    int yRaw = y - j * SIZE ;
                    Rectangle rectangleUp = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                    if (rectangleUp.isEmpty() == false) {
                        timeBoom.set(i, 0);
                    }

                }

                for (int j = 1; j <= lenghtDown; j++) {
                    int xRaw = x ;
                    int yRaw = y + j * SIZE;
                    Rectangle rectangleDown = getRect(xRaw, yRaw).intersection(arrBoom.get(i).getRect());
                    if (rectangleDown.isEmpty() == false) {
                        timeBoom.set(i, 0);
                    }

                }

            } catch (Exception e) {

            }
        }
    }
}


