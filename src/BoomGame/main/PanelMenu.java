
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
import static BoomGame.main.Container.PANEL_GAME;
import static BoomGame.main.Container.PANEL_HELP;
import BoomGame.sounds.Sound;
/**
 *
 * @author Admin
 */
public class PanelMenu extends JPanel implements ActionListener{
       private Container container;
        private JButton bStart,bHelp,bExit;
        
        public static final String START="Start";
        public static final String HELP="Help";
        public static final String EXIT="Exit";
        
        public PanelMenu(Container container){
            setLayout(null);
            initComponets();
            initListener();
            this.container=container;
            
        }
        public final Image[] image={
             new ImageIcon(getClass().getResource("/BoomGame/images/background.jpg")).getImage()
        };
        
        public final Icon[] icons={
            new ImageIcon(getClass().getResource("/BoomGame/images/Play2.png")) ,
            new ImageIcon(getClass().getResource("/BoomGame/images/button_help.jpg")) ,
            new ImageIcon(getClass().getResource("/BoomGame/images/Exit.png")) ,
        
 
 };
        
        @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(image[0], 0, 0, Gui.W_FRAME,Gui.H_FRAME, null);
		//g2d.drawIma
	}
     
    private void initComponets() {
               bStart=new JButton(icons[0]);
		
		bStart.setSize(icons[0].getIconWidth(),icons[0].getIconHeight());
		bStart.setLocation(440,420);
		add(bStart);
		
		bExit=new JButton(icons[2]);
		bExit.setSize(icons[2].getIconWidth(),icons[0].getIconHeight());
		bExit.setLocation(440,560);
		add(bExit);
                bHelp=new JButton(icons[1]);
		bHelp.setSize(icons[1].getIconWidth(),icons[0].getIconHeight());
		bHelp.setLocation(440,490);
		add(bHelp);
		System.out.println();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String run=e.getActionCommand();
       //   Clip clip=Sound.getSound(getClass().getResource("/sounds/click.wav"));
        switch(run){
            case START:
             //   clip.start();
                container.showCardLayout(PANEL_GAME); 
                break;
            case HELP :
              //  clip.start();
                container.showCardLayout(PANEL_HELP);
                break;
             case EXIT:
               //  clip.start();
                 return;
             default:
                 throw new AssertionError();
        }
    }

    private void initListener() {
        bStart.addActionListener(this);
        bStart.setActionCommand(START);
        
        bHelp.addActionListener(this);
        bHelp.setActionCommand(HELP);
        
        bExit.addActionListener(this);
        bExit.setActionCommand(EXIT);
        
    }
    
    
}
