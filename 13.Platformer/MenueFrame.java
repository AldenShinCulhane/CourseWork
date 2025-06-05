import javax.swing.*; 
import java.awt.*; 
import java.awt.event.KeyEvent; 
import java.awt.event.KeyListener; 
import java.awt.event.MouseEvent; 
import java.awt.event.MouseListener; 
import java.util.ArrayList;  
import java.io.File; 
import java.io.PrintWriter; 
import static java.awt.MouseInfo.getPointerInfo; 
import java.awt.MouseInfo; 
import java.util.concurrent.TimeUnit;

class MenueFrame extends JFrame implements GameConstants{
    JFrame thisFrame;
    private DrawingPanel panel;
    private boolean start = true;
    private boolean inPlay = true;


    MenueFrame () {
        super("Frame");
        this.thisFrame = this;

        //Creates our panel
        panel = new DrawingPanel();
        this.add(panel);

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
    
    public static void main (String[] args) throws Exception{
        MenueFrame menue = new MenueFrame();
        Sound sound = new Sound();
        String menuMusic = "Music/Menu Music.wav";
        sound.loadSound(menuMusic);
        sound.playSound();
        
        while (menue.getStart()){
            menue.redraw();
        }
        
        GameFrame game = new GameFrame();
        while (menue.getInPlay()){
            game.refresh();
        }
    }
    
    public void redraw(){
        panel.repaint();
    }
    
    public boolean getStart(){
        return this.start;
    }
    
    public boolean getInPlay(){
        return this.inPlay;
    }
    
    public void setStart(boolean bool){
        this.start = bool;
    }
    
    public void setInPlay(boolean bool){
        this.inPlay = bool;
    }

    private class DrawingPanel extends JPanel {
        private JTextField tf;
        DrawingPanel () {
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Drawing images into program
            Image menueImg = Toolkit.getDefaultToolkit().getImage("menueImg.png"); 
            g.drawImage(menueImg,0,0,MAX_X,MAX_Y,this);
            Image playImg = Toolkit.getDefaultToolkit().getImage("playImg.png"); 
            hovering(PLAY_X,PLAY_Y,PLAY_L,PLAY_W,g,playImg);
            Image instructionImg = Toolkit.getDefaultToolkit().getImage("instructionImg.png"); 
            hovering(INS_X,INS_Y,INS_L,INS_W,g,instructionImg);   
            Image quitImg = Toolkit.getDefaultToolkit().getImage("Titles/quitImg.png"); 
            hovering(MQUIT_X,MQUIT_Y,MQUIT_L,MQUIT_W,g,quitImg);
        }
        public void hovering(int x, int y, int width, int height, Graphics g, Image img){
            try{ 
                TimeUnit.SECONDS.sleep(1/2); 
                double mouseX= MouseInfo.getPointerInfo().getLocation().getX(); 
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();

                if ((mouseX > x) && (mouseX < (x + width)) && (mouseY > y) && (mouseY < (y + height))){ 
                    g.drawImage(img,x-(MAX_X/50),y+(MAX_Y/30),width+(MAX_X/50),height+(MAX_Y/30),this);
                }else{ 
                    g.drawImage(img,x,y,width,height,this);
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

        }

        @Override
        public void keyReleased(KeyEvent keyEvent) {

        }
    }

    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getX() > PLAY_X && mouseEvent.getX() < PLAY_X+PLAY_L && mouseEvent.getY() > PLAY_Y && mouseEvent.getY() < PLAY_Y+PLAY_W){
                thisFrame.dispose();
                setStart(false);
                
            }else if (mouseEvent.getX() > INS_X && mouseEvent.getX() < INS_X+INS_L && mouseEvent.getY() > INS_Y && mouseEvent.getY() < INS_Y+INS_W){
                
            }else if (mouseEvent.getX() > MQUIT_X && mouseEvent.getX() < MQUIT_X+MQUIT_L && mouseEvent.getY() > MQUIT_Y && mouseEvent.getY() < MQUIT_Y+MQUIT_W){
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
