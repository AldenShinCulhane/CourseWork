/*
 * Block.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Blocks used ingame
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

abstract class Block implements Movable, DrawReq {
  
  private Rectangle rectangle;
  private int x;
  private int y;
  private boolean lethal;
  private BufferedImage blockPic;
  
  Block (int x, int y, int type, boolean lethal) {
    try {
      String fileName = "images/levelBlocks/";
      switch (type) {
        case 0:
          rectangle = new Rectangle (x, y, 100, 25); // flat block
          fileName += "fb";
          break;
        case 1:
          rectangle = new Rectangle (x, y, 200, 100); // large flat block
          fileName += "lfb";
          break;
        case 2:
          rectangle = new Rectangle (x, y, 25, 100); // tall block
          fileName += "tb";
          break;
        case 3:
          rectangle = new Rectangle (x, y, 100, 200); // large tall block
          fileName += "ltb";
          break;
        case 4:
          rectangle = new Rectangle (x, y, 150, 150); // square block
          fileName += "sb";               
          break;
        case 5:
          rectangle = new Rectangle (x, y, 1280, 30); // ground
          fileName += "ground";
      }
      
      if (lethal) {
        fileName += "L";
      }
      fileName += ".png";
      
      blockPic = ImageIO.read (new File (fileName));
    }catch(Exception e){System.out.println("Error: Block image failed to load");};
    
    this.x = x;
    this.y = y;
    this.lethal = lethal;
  } // end of constructor
  
  public void draw (Graphics g) {  
    g.drawImage (blockPic, x, y, null);
  }
  
  abstract public void update (double elapsedTime);
  
  public Rectangle getRect () {
    return rectangle;
  }
  
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  public void moveVertical (int distance) {
    rectangle.translate (0, distance);
    y += distance;
  }
  
  public void moveHorizontal (int distance) {
    rectangle.translate (distance, 0);
    x += distance;
  }
  
  public boolean getLethal () {
    return lethal;
  }
  
  public int getSpeed () {return 0;}
} // end of Block

class SetBlock extends Block { // non-moving block
  
  SetBlock (int x, int y, int type, boolean lethal) {
    super (x, y, type, lethal);
  }
  
  public void update(double elapsedTime) {}
  
  public void moveVertica(int distance) {}
  public void moveHorizontal(int distance) {}
} // end of SetBlock

class SideBlock extends Block { // left right moving block
  
  private int startX;
  private int endX;
  private int speed;
  
  SideBlock (int startX, int startY, int endX, int type, boolean lethal) {
    super (startX, startY, type, lethal);
    this.startX = startX;
    this.endX = endX;
    speed = 6;
  } // end of constructor
  
  public void update (double elapsedTime) {
    int distance = 0;
    if (startX < endX) {
      if (this.getX() < startX) {
        speed = 4;
      } else if (this.getX() > endX) {
        speed = -4;
      }
    } else {
      if (this.getX() > startX) {
        speed = -4;
      } else if (this.getX() < endX) {
        speed = 4;
      }
    }
    distance = (int) (elapsedTime * speed * 100);
    this.moveHorizontal (distance);
  }
  
  public int getSpeed () {
    return speed;
  }
}// end of SideBlock

class Elevator extends Block { // up and down moving block
  private int startY;
  private int endY;
  private int speed;
  
  Elevator (int startX, int startY, int endY, int type, boolean lethal) {
    super (startX, startY, type, lethal);
    this.startY = startY;
    this.endY = endY;
    speed = 6;
  } // end of constructor
  
  public void update (double elapsedTime) {
    int distance = 0;
    if (startY < endY) {
      if (this.getY() < startY) {
        speed = 4;
      } else if (this.getY() > endY) {
        speed = -4;
      }
    } else {
      if (this.getY() > startY) {
        speed = -4;
      } else if (this.getY() < endY) {
        speed = 4;
      }
    }
    distance = (int) (elapsedTime * speed * 100);
    this.moveVertical (distance);
  }
  
  public int getSpeed () {
    return speed;
  }
}// end of Elevator