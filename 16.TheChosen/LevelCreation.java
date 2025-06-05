/*
 * LevelCreation.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Level creation interface and writing to file
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.util.Scanner;

class LevelCreation {
  
  ArrayList <CreationBlock> blockList;
  
  private BufferedImage canvas;
  private BufferedImage typeTitle;
  private BufferedImage attrTitle;
  private BufferedImage choiceTitle;
  private BufferedImage levelTitle;
  private BufferedImage spawn;
  
  private Button blockChoices [];
  private Button clearAll;
  private Button delete;
  private Button nonMoving;
  private Button sideMoving;
  private Button vertMoving;
  private Button location;
  private Button endpoint;
  private Button save;
  private Button setSpawn;
  
  private boolean pickUp;
  private int selected;
  private boolean endX;
  private boolean endY;
  private int spawnX;
  private int spawnY;
  private boolean spawnEdit;
  private boolean move;
  
  LevelCreation () {
    blockList = new ArrayList <CreationBlock> ();
    blockChoices = new Button [6];
    
    loadImages();
    
    blockChoices [0] = new Button (50, 684, 75, 19, "fb");
    blockChoices [1] = new Button (244, 656, 150, 75, "lfb");
    blockChoices [2] = new Button (175, 656, 19, 75, "tb");
    blockChoices [3] = new Button (444, 581, 75, 150, "ltb");
    blockChoices [4] = new Button (569, 600, 113, 133, "sb");
    blockChoices [5] = new Button (0, 737, 960, 23, "ground");
    
    clearAll = new Button (1140, 560, 100, 50, "CLEAR");
    save = new Button (1000, 640, 100, 50, "SAVE");
    setSpawn = new Button (1000, 560, 100, 50, "SET_SPAWN");
    nonMoving = new Button (1000, 80, 100, 50, "NON-MOVING");
    sideMoving = new Button (1140, 80, 100, 50, "MOVE_SIDEWAYS");
    vertMoving = new Button (1000, 160, 100, 50, "MOVE_UP_DOWN");
    delete = new Button (1140, 320, 100, 50, "DELETE");
    location = new Button (1140, 400, 100, 50, "LOCATION");
    endpoint = new Button (1140, 400, 100, 50, "ENDPOINT");
    
    pickUp = false;
    selected = -1;
    endX = false;
    endY = false;
    spawnX = -1;
    spawnY = -1;
    spawnEdit = false;
    move = false;
  }
  
  private void loadImages () {
    String name = "images/creation/";
    try {
      canvas = ImageIO.read (new File (name + "canvas.png"));
      typeTitle = ImageIO.read (new File (name + "typeTitle.png"));
      attrTitle = ImageIO.read (new File (name + "attributesTitle.png"));
      choiceTitle = ImageIO.read (new File (name + "choiceTitle.png"));
      levelTitle = ImageIO.read (new File (name + "levelTitle.png"));
      spawn = ImageIO.read (new File (name + "spawn.png"));
    } catch (Exception e) {
      System.out.println ("Error: creation background failed to load");
    }
  }
  
  public void draw (Graphics g) {
    g.drawImage (canvas, 0, 0, null);
    
    if (selected > -1) {
      g.setColor (Color.WHITE);
      g.fillRect ((blockList.get (selected).getX () - 5), (blockList.get (selected).getY () - 5), (blockList.get (selected).getRect ().width + 10), 
                  (blockList.get (selected).getRect ().height + 10));
    } // draw white rectangle to show selected
    
    for (int i = 0; i < blockList.size(); i++) {
      blockList.get (i).draw (g);
    } // draw all placed blocks
    
    g.setColor (Color.BLACK);
    g.fillRect (0, 576, 960, 192);
    g.fillRect (960, 0, 320, 768); // draw interface background
    
    g.drawImage (choiceTitle, 7, 600, null);
    g.drawImage (levelTitle, 960, 480, null); // draw headings
    
    for (int i = 0; i < 6; i++) {
      blockChoices [i].draw (g);
    } // draw block buttons
    
    if (selected > -1) {
      delete.draw (g);
      blockList.get (selected).drawButton(g);
      g.drawImage (attrTitle, 960, 240, null);
      if (blockList.get (selected).getSize () != 5) {
        g.drawImage (typeTitle, 960, 0, null);
        nonMoving.draw (g);
        sideMoving.draw (g);
        vertMoving.draw (g);
        location.draw (g);
        
        if (blockList.get (selected).getType () == 1 || blockList.get (selected).getType () == 2) {
          endpoint.draw (g);
        }
      }
    } // draw assorted settings
    
    if (spawnX > -1 && spawnY > -1) {
      g.drawImage (spawn, spawnX, spawnY, null);
    } // draw spawn marker
    
    clearAll.draw (g);
    save.draw (g);
    setSpawn.draw (g);
    // draw level settings
  }
  
  public void update (int mouseXY [], boolean click) {
    
    if (click) {
      
      if (pickUp) { // place block
        
        if (mouseXY[0] >= 0 && mouseXY [0] <= 960 && mouseXY [1] >= 0 && mouseXY [1] <= 576) {
          blockList.get (blockList.size() - 1).setX (mouseXY[0]);
          blockList.get (blockList.size() - 1).setY (mouseXY[1]);
          pickUp = false;
          selected = blockList.size() - 1;
        }
        
      } else if (endX) { // set endpoint for sideways moving block
        
        if (mouseXY[0] >= 0 && mouseXY [0] <= 960) {
          blockList.get (selected).setEndX (mouseXY[0]);
          endX = false;
        }
        
      } else if (endY) { // set endpoint for up down moving block
        
        if (mouseXY[1] >= 0 && mouseXY [1] <= 576) {
          blockList.get (selected).setEndY (mouseXY[1]);
          endY = false;
        }
        
      } else if (spawnEdit) { // set spawn point
        
        if (mouseXY[0] >= 0 && mouseXY [0] <= 960 && mouseXY [1] >= 0 && mouseXY [1] <= 576) {
          spawnX = mouseXY[0];
          spawnY = mouseXY[1];
          spawnEdit = false;
        }
        
      } else if (move) { // edit block location
        
        if (mouseXY[0] >= 0 && mouseXY [0] <= 960 && mouseXY [1] >= 0 && mouseXY [1] <= 576) {
          blockList.get (selected).setX (mouseXY[0]);
          blockList.get (selected).setY (mouseXY[1]);
          move = false;
        }
        
      } else { // all clicks outside of canvas
        
        for (int i = 0; i < 5; i++) {
          if (blockChoices [i].contains (mouseXY[0], mouseXY[1])) {
            CreationBlock block = new CreationBlock (0, 768, i);
            blockList.add (block);
            pickUp = true;
          }
        } // choose block
        
        if (blockChoices [5].contains (mouseXY[0], mouseXY[1])) {
          int reps = 0;
          for (int i = 0; i < blockList.size(); i++) {
            if (blockList.get (i).getType() == 5) {
              reps++;
            }
          }
          if (reps == 0) {
            CreationBlock block = new CreationBlock (0, 553, 5);
            blockList.add (block);
            selected = blockList.size() - 1;
          }
        } // ground button
        
        for (int i = 0; i < blockList.size(); i++) {
          if (mouseXY[0] >= 0 && mouseXY [0] <= 960 && mouseXY [1] >= 0 && mouseXY [1] <= 576) {
            if (blockList.get (i).getRect().contains (mouseXY[0], mouseXY[1])) {
              selected = i;
            }
          }
        } // selection
        
        if (clearAll.contains (mouseXY[0], mouseXY[1])) {
          blockList.clear();
          selected = -1;
          spawnX = -1;
          spawnY = -1;
        } // clearAll
        
        if (save.contains (mouseXY[0], mouseXY[1])) {
          save();
        } // save
        
        if (setSpawn.contains (mouseXY[0], mouseXY[1])) {
          spawnEdit = true;
        } // setSpawn
        
        if (selected > -1) {
          blockList.get (selected).checkButton (mouseXY[0], mouseXY[1]); // lethal
          
          if (blockList.get (selected).getSize () != 5) {
            
            if (nonMoving.contains (mouseXY[0], mouseXY[1])) {
              blockList.get (selected).setType (0);
              blockList.get (selected).setEndX (-1);
              blockList.get (selected).setEndY (-1);
            } else if (sideMoving.contains (mouseXY[0], mouseXY[1])) {
              blockList.get (selected).setType (1);
              blockList.get (selected).setEndY (-1);
              endX = true;
            } else if (vertMoving.contains (mouseXY[0], mouseXY[1])) {
              blockList.get (selected).setType (2);
              blockList.get (selected).setEndX (-1);
              endY = true;
            }// set type
            
            if (location.contains (mouseXY[0], mouseXY[1])) {
              move = true;
            } // edit location
            
            if (blockList.get (selected).getType() == 1) {
              if (endpoint.contains (mouseXY[0], mouseXY[1])) {
                endX = true;
              } // edit endX
            } else if (blockList.get (selected).getType() == 1) {
              if (endpoint.contains (mouseXY[0], mouseXY[1])) {
                endY = true;
              }
            } // edit endY           
          }
          
          if (delete.contains (mouseXY[0], mouseXY[1])) {
            blockList.remove (selected);
            selected = -1;
          } // delete
        }
      }
    }
  } // end of update
  
  private void save () {
    int lvls = 0;
    String lvlName = "";
    
    try {
      File readFile = new File ("blueprints/story4/lvlList.txt");
      Scanner reader = new Scanner (readFile);
      
      while (reader.hasNext()) {
        reader.nextLine();
        lvls++;
      }
      
      reader.close();
    } catch (Exception e) {
      System.out.println ("Error: player levels not found");
    }
    
    try {
      
      File writeLvlList = new File ("blueprints/story4/lvlList.txt");
      PrintWriter listWriter = new PrintWriter(writeLvlList);
      
      for (int i = 0; i < lvls; i++) {
        listWriter.println ("lvl" + (i + 1));
      }
      
      lvls++;
      lvlName = "lvl";
      lvlName += lvls;
      listWriter.println (lvlName);
      
      listWriter.close();
      
    } catch (Exception e) {
      System.out.println ("Error: lvlList not written");
    }
    
    File f = new File ("blueprints/story4/" + lvlName);
    f.mkdir();
    
    try {
      
      File writeFile = new File ("blueprints/story4/" + lvlName + "/layout.txt");
      PrintWriter writer = new PrintWriter (writeFile);
      
      writer.println (((int) (spawnX / 0.75)));
      writer.println (((int) (spawnY / 0.75)));
      
      for (int i = 0; i < blockList.size(); i++) {
        
        if (blockList.get (i).getType() > 0 && blockList.get (i).getLethal()) {
          writer.println ((blockList.get (i).getType() + 2));
        } else {
          writer.println (blockList.get (i).getType());
        }
        
        writer.println (blockList.get (i).getSize());
        writer.println (((int) (blockList.get (i).getX() / 0.75)));
        writer.println (((int) (blockList.get (i).getY() / 0.75)));
        
        if (blockList.get (i).getType() == 0) {
          if (blockList.get (i).getLethal()) {
            writer.println (1);
          } else {
            writer.println (0);
          }
        } else if (blockList.get (i).getType() == 1) {
          writer.println (((int) (blockList.get (i).getEndX() / 0.75)));
        } else if (blockList.get (i).getType() == 2) {
          writer.println (((int) (blockList.get (i).getEndY() / 0.75)));
        }
      }
      
      writer.close();
      
    } catch (Exception e) {
      System.out.println ("Error: lvl not written");
    }
    
    try {
      
      File monsterFile = new File ("blueprints/story4/" + lvlName + "/monsters.txt");
      PrintWriter monsterWriter = new PrintWriter (monsterFile);
      
      for (int i = 0; i < blockList.size(); i++) {
        if (blockList.get (i).getMonster ());
            monsterWriter.println (i);
      }
      
      monsterWriter.close();
    } catch (Exception e) {
      System.out.println ("Error: monsters not written");
    }
  }
} // end of class