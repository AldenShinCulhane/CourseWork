//Graphics &GUI imports
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;


//Scanners and FileI/O
import java.io.File;
import java.util.Scanner;

import javax.swing.JOptionPane;

//importing araylists
import java.util.*;

//Mouse imports
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

//Keyboard imports
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class GameFrame extends JFrame implements GameConstants{ 
    private final int MAX_X = (int)getToolkit().getScreenSize().getWidth();
    private final int MAX_Y = (int)getToolkit().getScreenSize().getHeight();
    private DrawingPanel panel;
    int maxW;
    int maxH;
    Walls[][] map;
    CPU bot;
    int [][] pos; 
    Players player;
    boolean playerRight, playerLeft = false;
    boolean crouching = false;
    boolean jump, jumpRight, jumpLeft = false;
    boolean grapple, grappleMove = false;
    boolean platformTouched = false;
    boolean pressedJ = false;
    int speedLength = 200;
    int jumpHeight = 0;
    double panelX = 0;
    double panelY = -1080;
    boolean gravity = true;
    
    GameFrame(){
        super("Frame");
        
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
        
        //this.setUndecorated(true);
        
        //Sets visibility to true
        this.setVisible(true);
        
        try{
            getMap();
        }catch (Exception e){
        }
        
        player = new Players(maxW,maxH*(map.length-3),maxW,maxH*2);
        player.setCharInitialX(maxW);
        player.setCharX(maxW);
    }
    
    public void refresh () {
        panel.repaint();
    }
    
    public void getMap()throws Exception{
        File myFile = new File("map.txt");
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
        maxW = MAX_X/columns;
        maxH = MAX_Y/rows;
        
        //make a 2d array of Walls objects
        map = new Walls [rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns;j ++){
                if (str.substring(0,1).equals("0")){
                    map[i][j] = null;
                }else{
                    if (str.substring(0,1).equals("1")){
                        map[i][j] = new NormalWalls(j * maxW, i*maxH,maxW,maxH); 
                    }else if (str.substring(0,1).equals("2")){
                        map[i][j] = new GrappleWalls(j * maxW, i*maxH,maxW,maxH);
                    }else if (str.substring(0,1).equals("3")){
                        map[i][j] = new Spikes(j * maxW, i*maxH,maxW,maxH);
                    }else if (str.substring(0,1).equals("4")){
                        map[i][j] = new Crates(j * maxW, i*maxH,maxW,maxH);
                    }else if (str.substring(0,1).equals("5")){
                        map[i][j] = new SpeedWall(j * maxW, i*maxH,maxW,maxH);
                    }
                    map[i][j].setImageInitialX(j * maxW);
                    map[i][j].setImageInitialY(i * maxH);
                }
                str = str.substring(1);
            }
        }
    }
    
    private class DrawingPanel extends JPanel {
        private JTextField tf;
        DrawingPanel(){
        }
        
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            this.repaint();
            Image scapeImg = Toolkit.getDefaultToolkit().getImage("Backgrounds/scapeImg.png"); 
            g.drawImage(scapeImg,0,0,MAX_X,MAX_Y,this);
            
            AffineTransform tx1 = new AffineTransform();
            tx1.translate(panelX, panelY);
            tx1.scale(2, 2);
            g2d.setTransform(tx1);
            
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
                        }else if (map[i][j] instanceof SpeedWall){
                            g.drawImage(map[i][j].getImage(),map[i][j].getImageX(),map[i][j].getImageY(),map[i][j].getImageW(),map[i][j].getImageH(),this);
                        }
                    }
                }
            }
            // drawing the character
            pos =  new int[map.length][map[0].length]; 
            pos[player.getCharY()/maxH][player.getCharX()/maxW] = 1;
            pos[(player.getCharY()/maxH) + 1][player.getCharX()/maxW] = 1;
//            System.out.println("head Y: " + player.getCharY()/maxH);
//            System.out.println("head X: " + player.getCharX()/maxW);
//            System.out.println("feet Y: " + ((player.getCharY()/maxH) + 1));
//            System.out.println("feet X: " + player.getCharX()/maxW);
            
            player.draw(g, player, (player.getCharInitialX()+player.getCharW()), maxH*(map.length-3));
            
            //check for touching abilities
            for (int i = 0; i < map.length; i++){ 
                for (int j = 0; j < map[i].length;j ++){
                    if (pos[i][j] == 1){
                        if (map[i][j] != null){
                            if (map[i][j] instanceof SpeedWall){
                                speedLength = 0;
                            }
                        }
                    }
                    if (grapple){
                        if(map[i][j] != null){
                            if ((map[i][j] instanceof GrappleWalls) && ((player.getCharX() + player.getCharW() + player.getGrappleW())/maxW == j) && ((player.getGrappleY())/ maxH == i)){
                                grappleMove = false;
                                gravity = false;
                            }
                            if (map[i][j] instanceof NormalWalls){
                               grapple = false;
                               grappleMove = false;
                               player.setGrappleH(0);
                            }
                        }
                    }
                } 
            }
            
//            for (int i = 0; i < map.length; i++){
//                for (int j = 0; j < map[i].length; j++){
//                    if (pos[i][j] == 1 && pos[i+1][j] == 1){
//                        System.out.println("head"+(i*maxH)+","+((j*maxW) + player.getCharW()));
//                        System.out.println("feet"+ ((i+1)*maxH) + ","+((j*maxW) + player.getCharW()));
//                    }
//                }
//            }
            
            // BORDER CHECK // 
            for (int i = 0; i < map.length - 1; i++){
                for (int j = 0; j < map[i].length; j++){
                    if (pos[i][j] == 1){
                        if(i > 0){ 
                            if (pos[i-1][j] == 1){                                
                                // Gravity //
                                if ((map[i][j] == null) && (map[i+1][j] != null) && (map[i+1][j] instanceof NormalWalls) && (player.getCharY() + player.getCharH() == map[i+1][j].getImageInitialY())){
                                    gravity = false;
                                    platformTouched = true;
                                }
                                if (j < map[i].length-1){
                                    if ( (map[i][j+1] instanceof NormalWalls == false) && (map[i+1][j+1] != null) && (map[i+1][j+1] instanceof NormalWalls) && (player.getCharY() + player.getCharH() == map[i+1][j+1].getImageInitialY())){
                                        if (player.getCharX() + player.getCharW() >= map[i+1][j+1].getImageInitialX()){
                                            gravity = false;
                                            platformTouched = true;
                                        }
                                    }
                                } 
                                
                                if (j < map[i].length-1){
                                    // Right Border //
                                    if ((map[i][j] == null) && (map[i][j+1] != null) && (map[i][j+1] instanceof NormalWalls)){
                                        if (player.getCharX() + player.getCharW() >= map[i][j+1].getImageInitialX()){
                                            playerRight = false;
//                                            if ((map[i][j+1].getImageInitialX()-player.getCharW()) <= player.getCharX()){
//                                                player.updateCharX(map[i][j+1].getImageInitialX()-player.getCharW());
//                                            }
                                        }
                                    }
                                    
                                    
                                }
                                if (j > 0){ 
                                    // Left Border //
                                    if ((map[i][j] == null) && (map[i][j-1] != null) && (map[i][j-1] instanceof NormalWalls)){ 
                                        if (player.getCharX() <= (map[i][j-1].getImageInitialX() + map[i][j-1].getImageW())){ 
                                            playerLeft = false; 
//                                            if (){
//                                            }
                                        }  
                                    }
                                }
                            }
                        }
                        
                        
                        if (pos[i+1][j] == 1){
                            // Ceiling Border //
                            if (i > 0){
                                if ((map[i-1][j] instanceof NormalWalls) && (player.getCharY() == map[i-1][j].getImageInitialY() + map[i-1][j].getImageH())){ 
                                    jump = false;
                                    gravity = true;
                                }
                                
                                if (j < map[i].length-1){
                                    if ((map[i-1][j+1] instanceof NormalWalls) && (player.getCharY() == map[i-1][j+1].getImageInitialY() + map[i-1][j+1].getImageH())){
                                        if (player.getCharX() <= (map[i-1][j+1].getImageInitialX() + map[i-1][j+1].getImageW())){
                                            jump = false;
                                            gravity = true;
                                        }
                                    }
                                }
                            }
                        } 
                    } 
                }
            }
            
            
            
            for (int i = 0; i < map.length; i++){ 
                for (int j = 0; j < map[i].length; j++){ 
                    if (map[i][j] != null){ 
                        if (playerRight){ 
                            map[i][j].setImageX(LEFT); 
                        }      
                        if (playerLeft){ 
                            map[i][j].setImageX(RIGHT); 
                        } 
                    } 
                } 
            }
            
            if(pressedJ){
                if (speedLength < 200){
                    if (playerRight || playerLeft){
                        player.setCharX(player.getOrientation());
                        player.updateInitialX(player.getOrientation()); 
                        speedLength += 1;
                    }
                }
            }
            
            if (grapple){
                player.drawGrapple(g, player);
                if (grappleMove){
                    player.setGrappleH(player.getGrappleH() + 1);
                    player.setGrappleY(player.getCharY() + (player.getCharH()/3) - 1);
                }else if (!grappleMove){
                    player.setCharX(player.getOrientation());
                    player.updateInitialX(player.getOrientation());
                }
            }else if (!grapple){
                player.setGrappleH(0);
                player.setGrappleY(player.getCharY() + (player.getCharH()/3));
            }
            
            if (gravity){
                player.setCharY(1);
            }
            gravity = true;
            
            if (playerRight){
                player.setCharX(1);
                player.setDir(2);
                player.setOrientation(RIGHT);
            }
            
            if (playerLeft){
                player.setCharX(-1);
                player.setDir(3);
                player.setOrientation(LEFT);
            }
            
            if (jump){
                if (jumpHeight < 150){
                    player.setCharY(-2);
                    
                }else{
                    jump = false;
                }
                jumpHeight += 2;
            }
            if (platformTouched){
                jumpHeight = 0;
            }
            platformTouched = false;
            
            if (crouching && (player.getOrientation() == RIGHT)){
                player.setDir(4);
            }else if (crouching && (player.getOrientation() == LEFT)){
                player.setDir(5);
            }
            
            if (grapple && (player.getOrientation() == RIGHT)){
                player.setDir(8);
            }else if (grapple && (player.getOrientation() == LEFT)){
                player.setDir(9);
            }
            
            if (jump && (player.getOrientation() == RIGHT)){
                player.setDir(6);
            }else if (jump && (player.getOrientation() == LEFT)){
                player.setDir(7);
            } 
            
            if (!jump && !playerRight && !playerLeft && !crouching){
                if (player.getOrientation() == RIGHT){
                    player.setDir(0);
                }else if (player.getOrientation() == LEFT){
                    player.setDir(1);
                }
            }
        }
    }
    
    private class MyKeyListener implements KeyListener {
        @Override
        public void keyTyped(KeyEvent keyEvent) {
            
        }
        
        @Override
        public void keyPressed(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_A){
                playerLeft = true;
                if(panelX != 0){
                    panelX = panelX + MOVE_SPEED;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_D){
                playerRight = true;
                if(panelX != (-1920)){
                    panelX = panelX - MOVE_SPEED;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_W){
                jump = true;
                if(panelY != 0){
                    panelY = panelY + MOVE_SPEED;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_S){
                crouching = true;
                if(panelY != (-1080)){
                    panelY = panelY - MOVE_SPEED;
                }
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_J){
                pressedJ = true;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_K){
                grapple = true;
                grappleMove = true;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
                new PausedMenu();
            }
        }
        
        @Override
        public void keyReleased(KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == KeyEvent.VK_A) {
                playerLeft = false;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_D){
                playerRight = false;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_S){
                crouching = false;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_J){
                pressedJ = false;
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_K){
                grapple = false;
                gravity = true;
            }
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

//            // BORDER CHECK //
//            for (int i = 0; i < map.length; i++){ 
//                for (int j = 0; j < map[i].length; j++){  
//                    if (pos[i][j] == 1){
//                        if (i < map.length-1){
//                            if (pos[i+1][j] == 1){
//                                /*RIGHT BORDER*/ 
//                                if (j < map[i].length-1){  
//                                    if ((map[i][j + 1] != null && map[i][j + 1] instanceof NormalWalls)){
//                                        if(((player.getCharX() + player.getCharW()) >= map[i][j+1].getImageInitialX()) && 
//                                            (player.getCharX() <= map[i][j+1].getImageInitialX() + map[i][j+1].getImageW())){
//                                            playerRight = false;
//                                        }
//                                    }
////                                    if(map[i+1][j + 1] != null && map[i+1][j + 1] instanceof NormalWalls){
////                                        if(((player.getCharX() + player.getCharW()) >= map[i+1][j+1].getImageInitialX() && 
////                                            (player.getCharX() + player.getCharW()) <= map[i+1][j+1].getImageInitialX() + map[i+1][j+1].getImageW())){  
////                                            playerRight = false; 
//////                                            int difference = player.getCharX() - (map[i+1][j+1].getImageInitialX()-player.getCharW());
//////                                            player.updateCharX(map[i+1][j+1].getImageInitialX()-player.getCharW());
//////                                            player.setCharInitialX(player.getCharInitialX()-difference);
////                                        }
////                                    }
//                                    
//                                    /*LEFT BORDER*/ 
//                                    if (j > 0){ 
//                                        if (map[i][j - 1] != null && map[i][j-1] instanceof NormalWalls){ 
//                                            if((player.getCharX() >= map[i][j-1].getImageInitialX()) &&
//                                               (player.getCharX() <= map[i][j-1].getImageInitialX() + map[i][j-1].getImageW())){ 
//                                                playerLeft = false; 
//                                            }  
//                                        }
//                                    }
//                                }
//                            } 
//                        }
//                        
//                        /*CEILING BORDER*/
//                        if (i > 0 && j < map[i].length-1){
//                            if (map[i-1][j] != null && map[i-1][j] instanceof NormalWalls){ 
//                                if(((player.getCharX() + player.getCharW()) >= map[i-1][j].getImageX()) && 
//                                   (player.getCharX() <= map[i-1][j].getImageInitialX() + map[i-1][j].getImageW()) &&
//                                   (player.getCharY() == map[i-1][j].getImageInitialY() + map[i-1][j].getImageH())){
//                                    jump = false;
//                                    gravity = true;
//                                }
//                            }
//                            
//                            if ((map[i-1][j+1] != null) && (map[i-1][j+1] instanceof NormalWalls)){
//                                if (((player.getCharX() + player.getCharW()) >= map[i-1][j+1].getImageX()) && 
//                                    (player.getCharX() <= map[i-1][j+1].getImageInitialX() + map[i-1][j+1].getImageW()) &&
//                                    (player.getCharY() == map[i-1][j+1].getImageInitialY() + map[i-1][j+1].getImageH())){
//                                    jump = false;
//                                    gravity = true;
//                                }
//                            }
//                        }
//                        
//                        /*GRAVITY*/ 
//                        if (i < map.length-1){
//                            if (map[i+1][j] != null && map[i+1][j] instanceof NormalWalls){ 
//                                if (((player.getCharX() + player.getCharW()) >= map[i+1][j].getImageX()) &&
//                                    (player.getCharX() <= map[i+1][j].getImageInitialX() + map[i+1][j].getImageW()) &&
//                                    (player.getCharY() + player.getCharH() == map[i+1][j].getImageInitialY())){
//                                    platformTouched = true;
//                                    gravity = false;
//                                }
//                            }
//                            
//                            if ((j < map[i].length-1) && (map[i+1][j+1] != null) && (map[i+1][j+1] instanceof NormalWalls)){
//                                if (((player.getCharX() + player.getCharW()) >= map[i+1][j+1].getImageX()) &&
//                                    (player.getCharX() <= map[i+1][j+1].getImageInitialX() + map[i+1][j+1].getImageW()) &&
//                                    (player.getCharY() + player.getCharH() == map[i+1][j+1].getImageInitialY())){
//                                    platformTouched = true;
//                                    gravity = false;
//                                }
//                            }
//                        }
//                    }
//                }
//            } 