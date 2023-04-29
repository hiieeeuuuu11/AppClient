/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BoomGame.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import static BoomGame.main.Container.PANEL_MENU;
import static BoomGame.main.Gui.H_FRAME;
import static BoomGame.main.Gui.W_FRAME;
import BoomGame.sounds.Sound;
/**
 *
 * @author Admin
 */
public class PanelHelp extends JPanel implements ActionListener{
       private JButton bBack;
       private Container container;
       public static final String BACK = "back";
            
         public final Image[] image={
         new ImageIcon(getClass().getResource("/BoomGame/images/backgroundHelp.jpg")).getImage()
        };
        
        public final Icon[] icons={
            new ImageIcon(getClass().getResource("/BoomGame/images/skip.png"))
         };
        
        public PanelHelp(Container container){
            setLayout(null);
            initComponents();
            initListener();
            this.container=container;
        }
        
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String run=e.getActionCommand();
        switch (run) {
            case BACK:
//               Clip clip=Sound.getSound(getClass().getResource("/sounds/click.wav"));
//               clip.start();

                container.showCardLayout(PANEL_MENU);
                break;
            default:
                throw new AssertionError();
        }
     }
     @Override
    protected void paintComponent(Graphics g) {
		super.paintChildren(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(image[0], 0, 0, Gui.W_FRAME,Gui.H_FRAME, null);
		//g2d.drawIma
	}
    private void initComponents() {
            bBack=new JButton(icons[0]);
            bBack.setSize(icons[0].getIconWidth(),icons[0].getIconHeight());
            bBack.setLocation(450, 490);
            add(bBack);
            
            
    }

    private void initListener() {
        bBack.addActionListener(this);
        bBack.setActionCommand(BACK);
    }
    
}
