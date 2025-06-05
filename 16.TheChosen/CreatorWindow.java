/*
 * CreatorWindow.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * jframe & jpanel for level creation
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CreatorWindow extends JFrame {
  
  static JFrame thisFrame;
  
  CreatorWindow () {
    setTitle ("The Chosen Level Creation");
    this.thisFrame = this; //lol  
    
    setResizable (false);
    this.setSize (1408, 768);
    setLocationRelativeTo (null);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);  // set the window up to end the program when closed 
    
    getContentPane().add (new GamePanel()); 
    pack (); //makes the frame fit the contents 
    setVisible (true);
  }
  
  static class GamePanel extends JPanel implements KeyListener, MouseListener {
    
    Clock clock;
    FrameRate frameRate;
    
    LevelCreation lvlCreate;
    
    boolean key [];
    int mouseXY [];
    boolean click;
    
    GamePanel () {
      setPreferredSize(new Dimension(1280,768));
      clock = new Clock ();
      frameRate = new FrameRate ();
      
      addKeyListener(this);
      addMouseListener(this);
      setFocusable(true);
      requestFocusInWindow();
      
      lvlCreate = new LevelCreation ();
      
      mouseXY = new int [2];
      click = false;
    }
    
    public void paintComponent(Graphics g) {
      super.paintComponent(g); //required to ensure the panel is correctly redrawn 
      
      //update the content
      clock.update(); 
      frameRate.update();
      lvlCreate.update (mouseXY, click);
      click = false;
      
      //draw the screen 
      lvlCreate.draw (g);
      frameRate.draw(g,10,10); 
      
      //request a repaint 
      repaint();
    }
    
    public void keyPressed (KeyEvent e) {
      
      if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  
        thisFrame.dispose(); 
      new StartingFrame ();
      }
    }
    
    public void keyTyped (KeyEvent e) {
    }
    
    public void keyReleased (KeyEvent e) {
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mousePressed(MouseEvent e) {
      click = true;
      mouseXY [0] = e.getX();
      mouseXY [1] = e.getY();
    }
    
    public void mouseReleased(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
  }
}