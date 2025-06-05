/*
 * CreationBlock.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Blocks used in level creation
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class CreationBlock {
  
  // attributes
  private Rectangle rectangle;
  private int x;
  private int y;
  private int size;
  private int type;
  private boolean lethal;
  private int endX;
  private int endY;
  private boolean hasMonster;
  
  // button that edits attribtues
  private ToggleButton lethalButton;
  private ToggleButton monsterButton;
  
  // images
  private BufferedImage lethalPic;
  private BufferedImage safePic;
  private BufferedImage marker;
  private BufferedImage monsterPic;
  
  CreationBlock (int x, int y, int size) {
    try {
      String fileName = "images/creation/";
      switch (size) {
        case 0:
          rectangle = new Rectangle (x, y, 75, 19); // flat block
          fileName += "fb";
          break;
        case 1:
          rectangle = new Rectangle (x, y, 150, 75); // large flat block
          fileName += "lfb";
          break;
        case 2:
          rectangle = new Rectangle (x, y, 19, 75); // tall block
          fileName += "tb";
          break;
        case 3:
          rectangle = new Rectangle (x, y, 75, 150); // large tall block
          fileName += "ltb";
          break;
        case 4:
          rectangle = new Rectangle (x, y, 113, 113); // square block
          fileName += "sb";               
          break;
        case 5:
          rectangle = new Rectangle (x, y, 960, 23); // ground
          fileName += "ground";
      }
      
      String lethalName = fileName + "L.png";
      lethalPic = ImageIO.read (new File (lethalName));
      
      fileName += ".png";
      safePic = ImageIO.read (new File (fileName)); // ^ load correct sized image
      
      marker = ImageIO.read (new File ("images/creation/marker.png")); // load endpoint marker
      monsterPic = ImageIO.read (new File ("images/creation/monster.png"));
      
    }catch(Exception e){System.out.println("Error: CreationBlock image failed to load");};
    
    this.x = x;
    this.y = y;
    this.lethal = false;
    this.size = size;
    this.type = 0;
    this.endX = -1;
    this.endY = -1;
    hasMonster = false;
    
    lethalButton = new ToggleButton (1000, 320, 100, 50, "NON-LETHAL", "LETHAL"); // create button
    monsterButton = new ToggleButton (1000, 400, 100, 50, "NO_MONSTER", "ADD_MONSTER");
  } // end of constructor
  
  public void draw (Graphics g) {
    if (lethal) {
      g.drawImage (lethalPic, x, y, null);
    } else {
      g.drawImage (safePic, x, y, null);
    }
    
    if (hasMonster) {
      g.drawImage (monsterPic, x, y - 42, null);
    }
  }
  
  public void drawButton (Graphics g) {
    lethalButton.draw(g);
    monsterButton.draw (g);
    
    if (size != 5) {
      g.setColor (Color.WHITE);
      switch (type) {
        case 0:
          g.fillRect (995, 75, 110, 60);
          break;
        case 1:
          g.fillRect (1135, 75, 110, 60);
          break;
        case 2:
          g.fillRect (995, 155, 110, 60);
          break;
      }
    }
    
    if (endX > -1) {
      g.drawImage (marker, endX - 7, y - 19, null);
    }
    
    if (endY > -1) {
      g.drawImage (marker, x - 7, endY - 19, null);
    }
  } // draw button and marker
  
  public void setX (int coordinate) {
    x = coordinate;
    rectangle.setLocation (x, y);
  }
  
  public void setY (int coordinate) {
    y = coordinate;
    rectangle.setLocation (x, y);
  }
  
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  public void setType (int setting) {
    type = setting;
  }
  
  public int getType () {
    return type;
  }
  
  public int getSize () {
    return size;
  }
  
  public void setEndX (int set) {
    endX = set;
  }
  
  public int getEndX () {
    return endX;
  }
  
  public void setEndY (int set) {
    endY = set;
  }
  
  public int getEndY () {
    return endY;
  }
  
  public void checkButton (int x, int y) {
    if (lethalButton.contains (x, y)) {
      lethalButton.toggle();
      if (!lethal) {
        lethal = true;
      } else {
        lethal = false;
      }
    } // lethal button
    
    if (monsterButton.contains (x, y)) {
      monsterButton.toggle();
      if (!hasMonster) {
        hasMonster = true;
      } else {
        hasMonster = false;
      }
    }
  }
  
  public boolean getMonster() {
    return hasMonster;
  }
  
  public boolean getLethal () {
    return lethal;
  }
  
  public Rectangle getRect () {
    return rectangle;
  }
}