/*
 * GameWindow.java
 * Version 1.0
 * @Cindy Wang + Charles Wong + Justin Wang + Alden Shin-Culhane
 * @01/21/2019
 * Program takes key input and changes game values
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GameWindow extends JFrame {
  
  static JFrame thisFrame;
  
  //constructor
  GameWindow () {
    this.thisFrame = this; //lol  
    
    setTitle ("The Chosen");
    setResizable (false);
    this.setSize (1280, 768);
    setLocationRelativeTo (null);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed 
    
    getContentPane().add (new GamePanel()); 
    pack (); //makes the frame fit the contents 
    setVisible (true);
  }
  
  static class GamePanel extends JPanel implements KeyListener{
    
    Village village;
    Clock clock;
    FrameRate frameRate;
    Player player;
    boolean[] key;
    double deltaTime;
    
    //constructor
    GamePanel () {
      setPreferredSize(new Dimension(1280,768)); 
      clock = new Clock ();
      frameRate = new FrameRate ();
      player = new Player ();
      village = new Village (player);
      
      addKeyListener(this);
      setFocusable(true);
      requestFocusInWindow();
      key = new boolean [9];
      deltaTime = 0;
    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      clock.update(); 
      frameRate.update();
      village.update(clock.getElapsedTime(), key);
      
      //draw the screen 
      village.draw(g); 
      frameRate.draw(g,10,10); 
      
      //request a repaint 
      repaint();
    }
    
    public void keyPressed (KeyEvent e) {
      if (e.getKeyChar() == 'w') { //up
        key [0] = true;
      } else if (e.getKeyChar() == 'a') { //left
        key [1] = true;
      } else if (e.getKeyChar() == 'd') { //right
        key [2] = true;
      } else if (e.getKeyChar() == 'h') { //enter portal
        key [3] = true;
      } else if (e.getKeyCode() == KeyEvent.VK_SPACE) { //shooting bullets
        key [4] = true;
      } else if (e.getKeyChar() == 'q') { //purchase item
        key[5] = true;
      }else if (e.getKeyCode() == KeyEvent.VK_1){ //using heart potion
        key[6] = true;
      }else if (e.getKeyCode() == KeyEvent.VK_2){ //using speed potion 
        key[7] = true;
      }else if (e.getKeyChar() == 'e') { //toggle inventory on + off
        key[8] = true;
      }else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { //closing game
        thisFrame.dispose();
        new StartingFrame();
      }
    }
    
    public void keyTyped (KeyEvent e) {
    }
    
    public void keyReleased (KeyEvent e) {
      if (e.getKeyChar() == 'w') {
        key [0] = false;
      } else if (e.getKeyChar() == 'a') {
        key [1] = false;
      } else if (e.getKeyChar() == 'd') {
        key [2] = false;
      } else if (e.getKeyChar () == 'h') {
        key [3] = false;
      } else if (e.getKeyCode () == KeyEvent.VK_SPACE) {
        key [4] = false;
      }
    }
  }
}