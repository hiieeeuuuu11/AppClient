/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.sounds;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *
 * @author Admin
 */
public class Sound {
    public static Clip getSound(URL url){
        try {
            AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(url);
            Clip clip=AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch (Exception e) {
        }
        return null;
    }
}
