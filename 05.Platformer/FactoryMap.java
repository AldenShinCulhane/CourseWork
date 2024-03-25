//Graphics & GUI imports
import javax.swing.*; 
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;

//Scanners and FileI/O
import java.io.File;
import java.util.Scanner;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class FactoryMap extends JFrame{ 
    private final int MAX_X = (int)getToolkit().getScreenSize().getWidth();
    private final int MAX_Y = (int)getToolkit().getScreenSize().getHeight();
    private DrawingPanel panel;

    FactoryMap(){
        super("Grapple Zone");
                
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
    
    public void redraw () {
        panel.repaint();
    }
    
    public Walls[][] getMap()throws Exception{
        File myFile = new File("Maps/factory.txt");
        Scanner file = new Scanner(myFile);
        String str = "";
        int rows = 0;
        
        //find the number of accumulating words and add the words onto a string
        while (file.hasNextLine()){
            rows = rows + 1;
            str = str + file.nextLine();
        }
        
        int columns = str.length()/rows;
        
        //finding the size of the walls according to the map
        final int WALL_W = MAX_X/columns;
        final int WALL_H = MAX_Y/rows;
        
        //make a 2d array of Walls objects
        Walls [][] map = new Walls [rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns;j ++){
                if (str.substring(0,1).equals("1")){
                    map[i][j] = new NormalWalls(j * WALL_W, i*WALL_H,WALL_W,WALL_H); 
                }else if (str.substring(0,1).equals("0")){
                    map[i][j] = null;
                }else if (str.substring(0,1).equals("2")){
                    map[i][j] = new GrappleWalls(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("3")){
                    map[i][j] = new Spikes(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("4")){
                    map[i][j] = new Crates(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("5")){
                    map[i][j] = new RampLD(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("6")){
                    map[i][j] = new RampRD(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("7")){
                    map[i][j] = new RampLU(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }else if (str.substring(0,1).equals("8")){
                    map[i][j] = new RampRU(j * WALL_W, i*WALL_H,WALL_W,WALL_H);
                }
                str = str.substring(1);
            }
        }
        file.close();
        return map;
    }
    
    private class DrawingPanel extends JPanel {
        private JTextField tf;
        Walls[][] map;
        DrawingPanel(){
            try{
                map = getMap(); 
            }catch(Exception e){
            }
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image scapeImg = Toolkit.getDefaultToolkit().getImage("Backgrounds/factoryBackImg.jpg"); 
            g.drawImage(scapeImg,0,0,MAX_X,MAX_Y,this);
            
            System.out.println(map.length + "           " + map[0].length);
            
            //drawing the walls and obstacles
            for (int i = 0; i < map.length; i++){
                for (int j = 0; j < map[i].length;j ++){
                    if(map[i][j] != null){
                        if (map[i][j] instanceof NormalWalls){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof GrappleWalls){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof Spikes){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof Crates){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof RampLD){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof RampRD){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof RampLU){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }else if (map[i][j] instanceof RampRU){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }
                    }
                }
            }
            
//            while(true){
//                try{ Thread.sleep(500);} catch (Exception exc){}  //delay
//            }
            
        }
    }
    
    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                new PausedMenu();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            
        }
    }
    
    private class MyMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
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
        }
    }
}

