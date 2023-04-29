/*

Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package BoomGame.main;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.time.Clock.system;
import static java.time.InstantSource.system;

import java.util.BitSet;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static BoomGame.main.GameManager.*;
import BoomGame.sounds.Sound;


/**
 * @author Admin
 */
public class PanelGame extends JPanel implements KeyListener, Runnable {
    private GameManager gameManager = new GameManager();
    private BitSet bitSet = new BitSet(256);
    boolean isRunning = true;
    public static final int TIME_DAT = 10;
    Thread t = new Thread(this);
    void initPanelGame() {
        gameManager.initGame();
        //t = new Thread(this);
        t.start();
        setFocusable(true);
//addKeyListener(this);
    }

   
    @Override
    protected void paintChildren(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gameManager.draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        bitSet.set(e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        bitSet.clear();
        e.getKeyCode();

    }

    @Override
   public void run() {
    int time = 0;
    int t = 0;
    while (isRunning) {
        t++;
        if (bitSet.get(KeyEvent.VK_A)) {
            gameManager.movePlayer(Player.LEFT);
        } else if (bitSet.get(KeyEvent.VK_D)) {
            gameManager.movePlayer(Player.RIGHT);
        } else if (bitSet.get(KeyEvent.VK_S)) {
            gameManager.movePlayer(Player.DOWN);
        } else if (bitSet.get(KeyEvent.VK_W)) {
            gameManager.movePlayer(Player.UP);
        }
        try {
            if (bitSet.get(KeyEvent.VK_SPACE)) {
                if (t - time >= TIME_DAT) {
                    gameManager.myPlayerBoom(t);
                }
                time = t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        isRunning = gameManager.AI(t);
        repaint();
        if (isRunning == false && gameManager.isCheckWin() == false) {

              
              Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/die.wav"));
              clip.start();
              gameManager.clip1.stop();
            int result = JOptionPane.showConfirmDialog(null, "Are You Want Play Again ?", "Game Over ", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                bitSet.clear();

                gameManager.initGame();
                BoomBang.score = 0;
                BoomBang.TIME_PLAY=100;
                isRunning = true;
            } else {
                System.exit(0);
            }
        }

        if (isRunning == false && gameManager.isCheckWin() == true) {
               Clip clip=Sound.getSound(getClass().getResource("/BoomGame/sounds/levelnext.wav"));
                clip.start();
                gameManager.clip1.stop();

            int result = JOptionPane.showConfirmDialog(null, "Do you want to play again ?", "Congratulations! You Win", JOptionPane.YES_NO_CANCEL_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                bitSet.clear();
                gameManager.initGame();
                BoomBang.score = 0;
                BoomBang.TIME_PLAY=100;
                isRunning = true;
            } else {

            }
        }
            
               
           
              
    
        repaint();
        try {
            Thread.sleep(TIME_DAT);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}
