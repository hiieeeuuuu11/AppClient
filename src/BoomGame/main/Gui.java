/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import javax.swing.JFrame;


/**
 *
 * @author Admin
 */
public class Gui extends JFrame{
    public static int W_FRAME=720;
    public static int H_FRAME=672;

    public Gui() {
        setTitle("Boom Boom");
        setSize(W_FRAME, H_FRAME);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        add(new Container());
       
    }
    
    
}
