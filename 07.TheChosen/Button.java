/*
 * Button.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * buttons used in level creation interface
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class Button {
  
  private BufferedImage pic;
  private Rectangle box;
  private int x;
  private int y;
  
  Button (int x, int y, int width, int height, String name) {
    
    try {
      pic = ImageIO.read (new File ("images/creation/" + name + ".png"));
    } catch (Exception e) {
      System.out.println ("Error: Creation pic failed to load");
    }
    
    box = new Rectangle (x, y, width, height);
    
    this.x = x;
    this.y = y;
  }
  
  public boolean contains (int x, int y) {
    if (box.contains (x, y)) {
      return true;
    } else {
      return false;
    }
  } // check for mouse on button
  
  public void draw (Graphics g) {
    g.drawImage (pic, x, y, null);
  }
  
  protected int getX () {
    return x;
  }
  
  protected int getY () {
    return y;
  }
}

class ToggleButton extends Button {
  
  BufferedImage pressedPic;
  private boolean pressed;
  
  ToggleButton (int x, int y, int width, int height, String notPressed, String pressed) {
    
    super (x, y, width, height, notPressed);
    
    try {
      pressedPic = ImageIO.read (new File ("images/creation/" + pressed + ".png"));
    } catch (Exception e) {
      System.out.println ("Error: Creation pic failed to load");
    }
    
    this.pressed = false;
  }
  
  public void draw (Graphics g) {
    if (pressed) {
      g.drawImage (pressedPic, this.getX(), this.getY(), null);
    } else {
      super.draw (g);
    }
  }
  
  public void toggle () {
    if (pressed) {
      pressed = false;
    } else if (!pressed) {
      pressed = true;
    }
  }
}