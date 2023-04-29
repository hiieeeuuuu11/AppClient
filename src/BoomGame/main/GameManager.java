/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.Integer.SIZE;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import static BoomGame.main.Gui.H_FRAME;
import static BoomGame.main.Gui.W_FRAME;
import static BoomGame.main.Player.*;
import BoomGame.sounds.Sound;
/**
 *
 * author Admin
 */

public class GameManager {
    public static String[] mapList = {
        "map1.txt",
        "map2.txt"
    };
   public static int currentMapIndex = 0; // chỉ số của map hiện tạ
    private  static ArrayList<MapItem> arrMapItem;
    private  static ArrayList<Item> arrItem;
    private static Player player;
   private static ArrayList<Boss> arrBoss;
   private  static ArrayList<Boom> arrBoom;
     private  static ArrayList<BoomBang> arrBoomBang;

    private static String Path;
    private ArrayList<Integer> timeBoom;
    private ArrayList<Integer> timeBang;
    private ArrayList<Integer> timeWave;
    public Clip clip1=Sound.getSound(this.getClass().getResource("/BoomGame/sounds/game.wav"));

private int timeLimit = 2000; // Thời gian giới hạn đếm ngược
private int countDownNumber=timeLimit; // Số đếm hiện tại


    // private int timeLimit=;
    public static final int TIME_BANG=120;
    public static final int TIME_WAVE=15;

    private Random random = new Random();
    private boolean checkWin;
    private int timeDie;
    private Clip clip;
    Time time = new Time();
    Thread tp;
      public final Image[] MY_IMAGE={
        new ImageIcon(getClass().getResource("/BoomGame/images/sango.jpeg")).getImage()
    };

    public void draw(Graphics2D g2d) {
        g2d.drawImage(MY_IMAGE[0], 0, 0, W_FRAME, H_FRAME, null);
        try {
           for(Boom boom : arrBoom){
            boom.drawBoom(g2d);
            }
         for(BoomBang boomBang : arrBoomBang){
            boomBang.drawBoomBang(g2d,arrMapItem);
             }
           for (Item item: arrItem) {
            item.drawItem(g2d);
        }
         for(MapItem mapItem : arrMapItem) {
             mapItem.drawMapItem(g2d);
        }
          for (Boss boss : arrBoss) {
            boss.drawBoss(g2d);
         }

        } catch (Exception e) {
        }

        player.drawPlayer(g2d);
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        g2d.setColor(Color.RED);
        g2d.drawString("Score: "+BoomBang.score, 600, 60);
        g2d.drawString(String.valueOf(BoomBang.TIME_PLAY),600,80);
       // countDown(g2d);

      }

    void initGame() {

         clip1.start();
         clip1.loop(100);

        arrMapItem=new ArrayList<>();
        arrBoss=new ArrayList<>();
        arrItem=new ArrayList<>();
        arrBoom=new ArrayList<>();
        arrBoomBang=new ArrayList<>();

        tp= new Thread(time);
        tp.start();

        timeBoom=new ArrayList<>();
        timeWave=new ArrayList<>();

        player = new Player(W_FRAME / 2, H_FRAME - 90 - SIZE, LEFT, 0.1);
        String mapPath = "src\\BoomGame\\data\\" + mapList[currentMapIndex];
        readMap(mapPath);
        initBoss();
        initItem();
    }

      public void myPlayerBoom(int t) {
      Boom boom=player.DatBoom(arrBoom);
      if(arrBoom.size()<player.getSoBom()){
            arrBoom.add(boom);
            Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/setBoom.wav"));
            clip.start();
            timeBoom.add(t);
      }

  }


    public static void readMap(String mapPath) {
         int intLine = 0;
    try {
        FileReader is = new FileReader(mapPath);
        BufferedReader br = new BufferedReader(is);

        String line = br.readLine();
        while (line != null) {
            for (int i = 0; i < line.length(); i++) {
                arrMapItem.add(new MapItem(i * SIZE, intLine * SIZE, Integer.parseInt(String.valueOf(line.charAt(i)))));
            }
            line = br.readLine();
            intLine++;
        }
        br.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
   }

    public boolean isCheckWin() {
        return checkWin;

    }

    public void setCheckWin(boolean checkWin) {
        this.checkWin = checkWin;

    }

private void initBoss(){
 Boss boss1 = new Boss(80, 90, SIZE);
        Boss boss2 = new Boss(500, 320, SIZE);
        Boss boss3 = new Boss(300, 460, SIZE);
        Boss boss4 = new Boss(290, 80, SIZE);
        Boss boss5 = new Boss(200, 520, SIZE);
        Boss boss6 = new Boss(540, 80, SIZE);
        Boss boss7 = new Boss(440, 340, SIZE);
        Boss boss8 = new Boss(35, 360, SIZE);
        Boss boss9 = new Boss(330, 230, SIZE);
        Boss boss10 = new Boss(40, 420, SIZE);

        arrBoss.add(boss1);
        arrBoss.add(boss2);
        arrBoss.add(boss3);
        arrBoss.add(boss4);
        arrBoss.add(boss5);
        arrBoss.add(boss6);
        arrBoss.add(boss7);
        arrBoss.add(boss8);
        arrBoss.add(boss9);
        arrBoss.add(boss10);

}

    private void initItem() {
           for(int i=0;i<arrMapItem.size();i++){
            int show=random.nextInt(100+1);
            if(show>80 &&(arrMapItem.get(i).bit==2||arrMapItem.get(i).bit==4 ||arrMapItem.get(i).bit==5)){
                int xRaw=arrMapItem.get(i).getX();
                int yRaw=arrMapItem.get(i).getY();
                Item item=new Item(xRaw, yRaw);
                arrItem.add(item);
            }
        }
    }
public boolean AI(int t) {

    for(int i = arrBoss.size() - 1; i >= 0; i--){
        arrBoss.get(i).moveBoss(arrMapItem, arrBoom, t);
    }
    for(int i = 0; i < arrBoom.size(); i++){
        if(t - timeBoom.get(i) >= TIME_BANG){
            BoomBang boomBang = arrBoom.get(i).boomBang();
            arrBoom.remove(i);
            Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/bang.wav"));
            clip.start();
            arrBoomBang.add(boomBang);
            timeBoom.remove(i);
            try {
                boomBang.checkBoomToBoom(arrBoom, timeBoom);
            } catch (Exception e) {
            }
            timeWave.add(t);
        }
    }
    for(int i = 0; i < arrBoomBang.size(); i++){
        arrBoomBang.get(i).checkBoomToBoss(arrBoss);
        if(t - timeWave.get(i) >= TIME_WAVE){
            arrBoomBang.remove(i);
            timeWave.remove(i);
        }
    }
    if(player.checkDieToBoss(arrBoss)){
        return false;
    }
    for(int i = 0; i < arrBoomBang.size(); i++){
        if(arrBoomBang.get(i).checkBoomToPlayer(arrBoomBang, player)){
            timeDie = t;
            return false;
        }
    }
    if(arrBoss.isEmpty()){
        setCheckWin(true);
        return false;
    }
    if(!tp.isAlive()){
        setCheckWin(false);
        return false;
    }
    if(BoomBang.TIME_PLAY==0){
        setCheckWin(false);
        return false;
    }
    return true;

}


   public void movePlayer (int newOrient){
       player.changeOrient(newOrient);
       player.move(arrMapItem,arrBoom);
       player.moveItem(arrItem);
   }

    }























