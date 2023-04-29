/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

/**
 *
 * @author Admin
 */
public class RunGame implements Runnable {

    @Override
    public void run() {
        new Gui().setVisible(true);
    }
}
