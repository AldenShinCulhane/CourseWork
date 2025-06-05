/**
 * 
 * @Gerred G ICS4U
 * @version Jan 2020
 */

//Graphics & GUI imports
import javax.swing.*; 
import java.awt.*;

//sleep timer
import java.util.concurrent.TimeUnit;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class MapSel extends JFrame implements GameConstants{
    JFrame mapsSel;
    private DrawingPanel panel;
    Sound sound = new Sound();
    
    MapSel() {
        super("Grapple Zone");
//        sound.playSound();
        this.mapsSel = this;
        
        //Creates our panel
        panel = new DrawingPanel();
        this.add(panel);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create mouse listener
        this.addMouseListener(new MyMouseListener());
        
        //Sets to fullscreen
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        
        //Sets visibility to true
        this.setVisible(true);
    }
    
    public void refresh () {
        panel.repaint();
    }
    
    private class DrawingPanel extends JPanel {
        private JTextField tf;
        DrawingPanel () {
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Drawing images into program
            Image backgroundImg = Toolkit.getDefaultToolkit().getImage("Backgrounds/mapselectionImg.png"); 
            g.drawImage(backgroundImg,0,0,MAX_X,MAX_Y,this);
            Image practicePrevImg = Toolkit.getDefaultToolkit().getImage("Map Prev/practicePrev.png"); 
            g.drawImage(practicePrevImg,PRACTICE_X,PRACTICE_Y - 250,300,250,this);
            Image practiceImg = Toolkit.getDefaultToolkit().getImage("Titles/practiceImg.png"); 
            hovering(PRACTICE_X,PRACTICE_Y,PRACTICE_L,PRACTICE_W,g,practiceImg);
            Image factoryImg = Toolkit.getDefaultToolkit().getImage("Titles/factoryImg.png"); 
            hovering(FACTORY_X,FACTORY_Y,FACTORY_L,FACTORY_W,g,factoryImg);
            Image lastmanImg = Toolkit.getDefaultToolkit().getImage("Titles/lastmanImg.png"); 
            hovering(LASTMAN_X,LASTMAN_Y,LASTMAN_L,LASTMAN_W,g,lastmanImg);
            Image backImg = Toolkit.getDefaultToolkit().getImage("Titles/backImg.png"); 
            hovering(BACK_X,BACK_Y,BACK_L,BACK_W,g,backImg);
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
    
    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getX() > PRACTICE_X && mouseEvent.getX() < PRACTICE_X+PRACTICE_L && mouseEvent.getY() > PRACTICE_Y && mouseEvent.getY() < PRACTICE_Y+PRACTICE_W){
                mapsSel.dispose();
//                sound.stopSound();
                GameFrame game = new GameFrame();
                while(true){
                game.refresh();
                }
            }           
            else if (mouseEvent.getX() > FACTORY_X && mouseEvent.getX() < FACTORY_X+FACTORY_L && mouseEvent.getY() > FACTORY_Y && mouseEvent.getY() < FACTORY_Y+FACTORY_W){
                mapsSel.dispose();
//                sound.stopSound();
                new FactoryMap();
            }
            else if (mouseEvent.getX() > BACK_X && mouseEvent.getX() < BACK_X+BACK_L && mouseEvent.getY() > BACK_Y && mouseEvent.getY() < BACK_Y+BACK_W){
                mapsSel.dispose();
                new MenueFrame();
            }
            else if (mouseEvent.getX() > LASTMAN_X && mouseEvent.getX() < LASTMAN_X+LASTMAN_L && mouseEvent.getY() > LASTMAN_Y && mouseEvent.getY() < LASTMAN_Y+LASTMAN_W){
                mapsSel.dispose();
//                sound.stopSound();
                new LastManMap();
            } 
        }
        
        @Override
        public void mouseEntered(MouseEvent mouseEvent){}
        
        @Override
        public void mouseExited(MouseEvent mouseEvent){}
        
        @Override
        public void mousePressed(MouseEvent mouseEvent){}
        
        @Override
        public void mouseReleased(MouseEvent mouseEvent){}
    }
}
