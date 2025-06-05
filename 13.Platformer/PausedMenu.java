//Graphics & GUI imports
import javax.swing.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
//sleep timer
import java.util.concurrent.TimeUnit;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class PausedMenu extends JFrame implements GameConstants{
    JFrame thisFrame;
    private DrawingPanel panel;
    private JPanel volumeSlider;
    Sound sound = new Sound();
    
    PausedMenu(){
        super("Grapple Zone");
        this.thisFrame = this;
        
        //Creates our panel
        panel = new DrawingPanel();
        this.add(panel);
        
        
        volumeSlider = new JPanel();
        
//        volumeSlider.setLayout(new FlowLayout());
//        volumeSlider.setPreferredSize(new Dimension(100, 100));
//        this.add(volumeSlider);
//                                           
//        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0); 
//        slider.setPreferredSize(new Dimension(50,50));
//        slider.setMajorTickSpacing(10);
//        slider.setMinorTickSpacing(1);
//        slider.setPaintTicks(true);
//        slider.setPaintLabels(true);
//        
//        volumeSlider.add(slider);  
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create mouse listener
        this.addMouseListener(new MyMouseListener());
        
        //Create key listener
        this.addKeyListener(new MyKeyListener());
        
        //Sets to fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        
        //Sets visibility to true
        this.setVisible(true);
    }
    
    private class DrawingPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image backgroundImg = Toolkit.getDefaultToolkit().getImage("Backgrounds/pausedImg.png"); 
            g.drawImage(backgroundImg,0,0,MAX_X,MAX_Y,this);
            Image resumeImg = Toolkit.getDefaultToolkit().getImage("Titles/resumeImg.png"); 
            hovering(RESUME_X,RESUME_Y,RESUME_L,RESUME_W,g,resumeImg);
            Image quitImg = Toolkit.getDefaultToolkit().getImage("Titles/quitImg.png"); 
            hovering(PQUIT_X,PQUIT_Y,PQUIT_L,PQUIT_W,g,quitImg);
        }
        public void hovering(int x, int y, int width, int height, Graphics g, Image img){
            try{ 
                TimeUnit.SECONDS.sleep(1/2); 
                double mouseX = MouseInfo.getPointerInfo().getLocation().getX(); 
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                
                if ((mouseX > x) && (mouseX < (x + width)) && (mouseY > y) && (mouseY < (y + height))){ 
                    g.drawImage(img,x-(MAX_X/70),y+(MAX_Y/50),width+(MAX_X/70),height+(MAX_Y/50),this);
                    repaint();
                }else{ 
                    g.drawImage(img,x,y,width,height,this);
                    repaint();
                } 
            }catch (InterruptedException e){ 
                throw new RuntimeException(e); 
            }
        }
    }
    
    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent keyEvent) {
//            if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
//                thisFrame.dispose();
//                repaint();
//            }
        }
        
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            
        }
    }
    
    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
          if (mouseEvent.getX() > RESUME_X && mouseEvent.getX() < RESUME_X+RESUME_L && mouseEvent.getY() > RESUME_Y && mouseEvent.getY() < RESUME_Y+RESUME_W){
            thisFrame.dispose();
//            repaint();
          }
          else if (mouseEvent.getX() > PQUIT_X && mouseEvent.getX() < PQUIT_X+PQUIT_L && mouseEvent.getY() > PQUIT_Y && mouseEvent.getY() < PQUIT_Y+PQUIT_W){
//              sound.stopSound();
              System.exit(0);
          } 
        }
        
        @Override
        public void mouseEntered(MouseEvent mouseEvent) {
            
        }
        
        @Override
        public void mouseExited(MouseEvent mouseEvent) {
            
        }
        
        @Override
        public void mousePressed(MouseEvent mouseEvent) {
        }
        
        @Override
        public void mouseReleased(MouseEvent mouseEvent) {
            //use it here
        }
    }
}

