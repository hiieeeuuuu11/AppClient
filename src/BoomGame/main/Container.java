
package BoomGame.main;

import java.awt.CardLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import BoomGame.sounds.Sound;


/**
 *
 * @author Admin
 */
public class Container extends JPanel implements ActionListener{
    public static final String PANEL_GAME="PanelGame";
    public static final String PANEL_MENU="PanelMenu";
    public static final String PANEL_HELP="PanelHelp";
    
    public  CardLayout cardLayout;
    private PanelHelp panelHelp;
    private PanelMenu panelMenu;
    private  PanelGame panelGame;
    private Clip clip;
    
    public Container(){
        cardLayout = new CardLayout();
        panelGame = new PanelGame();
        panelHelp=new PanelHelp(this);
        panelMenu=new PanelMenu(this);
   
        setLayout(cardLayout);
        add(PANEL_GAME,panelGame);
        add(PANEL_HELP,panelHelp);
        add(PANEL_MENU,panelMenu);
        
        cardLayout.show(this, PANEL_MENU);
        clip =Sound.getSound(getClass().getResource("/BoomGame/sounds/menu.wav"));
        clip.loop(100);
        addKeyListener(panelGame);
        setFocusable(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
    public void showCardLayout(String name){
     if(name == PANEL_GAME){
        cardLayout.show(this, name); 
        panelGame.initPanelGame();
        clip.stop();
        //while
    } else if(name == PANEL_MENU){
        cardLayout.show(this, name); 
    } else if(name == PANEL_HELP){
        cardLayout.show(this, name); 
    }
   }
    
}