/*
 * Level.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Level object, contains code for player movement in level and all level content
 */

import java.awt.*;
import java.util.ArrayList;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*; 

class Level extends Runnable implements InputReq {
  
  private ArrayList <Block> blockList;
  private ArrayList <Monster> monsterList;
  
  public Player player;
  
  
  private double deltaTime;
  private int gravity;
  private int touchNum;
  private int resetX;
  private int resetY;
  
  private BufferedImage bg;
  
  
  Level (Player player) {
    try {
      bg = ImageIO.read (new File ("images/levelBlocks/caveBG.png"));
    }catch(Exception e){System.out.println("Error: Cave bg failed to load");};
    
    this.player = player;
    blockList = new ArrayList <Block> ();
    monsterList = new ArrayList <Monster> ();
    
    gravity = 6;
    deltaTime = 0;
  }
  
  public void draw (Graphics g) {    
    g.drawImage (bg, 0, 0, null);
    for (int i = 0; i < blockList.size(); i++) {
      blockList.get (i).draw (g);
    }
    
    for (int i = 0; i < monsterList.size(); i++) {
      monsterList.get (i).draw (g);
    }
    
    player.draw (g);
  }
  
  public void update(double elapsedTime, boolean key []) {
    deltaTime += elapsedTime;
      
      for (int i = 0; i < blockList.size(); i++) {
      blockList.get (i).update (elapsedTime);
    }
    
    for (int i = 0; i < monsterList.size(); i++) {
      monsterList.get (i).update (elapsedTime);
      
      if (player.getRect().intersects (monsterList.get (i).getRect())) {
        player.reset (resetX, resetY);
        player.loseLives();
      }
      
      for (int j = player.getMag().size() - 1; j > -1; j--){
        if (monsterList.get (i).getRect().intersects (player.getMag().get (j).getRect())) {
          monsterList.get (i).takeDamage ();
          player.getMag().remove (j);
        }
      }
      
      if (monsterList.get (i).getHp() <= 0) {
        monsterList.remove (i);
        player.addCoins();
      }
      
    }
    
    movement (elapsedTime, key);
    
    if (key [4]) {
      player.shoot(elapsedTime);
      player.nextSprite (2);
    }
    
    for (int i = 0; i < player.getMag().size(); i++){
      player.getMag().get(i).update(elapsedTime);
    }
    
    player.updateBullets ();
    
    if (player.getX() < 0) {
      player.reset(resetX, resetY);
    }
  }
  
  public void end () {
    if (player.getX() > 1280 || player.getLives() == 0) {
      super.end();
      player.addCoins();
    }
  }
  
  public void run () {
    super.run();
    if (player.getX() > 1280) {
      player.reset (resetX, resetY);
    }
  }
  
  public void addBlock (Block block) {
    blockList.add (block);
  }
  
  public void addMonster (int num) {
    Monster monster = new Monster (blockList.get (num));
    monsterList.add (monster);
  }
  
  public void addReset (int x, int y) {
    resetX = x;
    resetY = y;
  } 
  
  private void movement (double elapsedTime, boolean key []) {
    int xD = 0;
    int yD = 0;
    Rectangle intersect;
    
    if (key [0]) {
      if (touchNum > 0) {
        player.setVy (-40);
      }
    }
    
    // move left and right
    if (key [1]) {
      player.setVx (-player.getSpeed());
      player.setDir (0);
      if (deltaTime >= 0.1) {
        player.nextSprite();
        deltaTime = 0;
      }
    } else if (key [2]) {
      player.setVx (player.getSpeed());
      player.setDir (1);
      if (deltaTime >= 0.1) {
        player.nextSprite();
        deltaTime = 0;
      }
    } else {
      player.setVx (0);
    }
    
    // add gravity to velocity
    player.setVy (player.getVy() + (gravity * elapsedTime * 40));
    
    // calculate horizontal displacement
    xD = (int) (player.getVx() * elapsedTime * 100);
    
    // calculate vertical displacement
    yD = (int) (player.getVy() * elapsedTime * 40);
    
    // move
    player.moveHorizontal (xD);
    player.moveVertical (yD);
    
    // stopping
    touchNum = 0;
    for (int i = 0; i < blockList.size(); i++) {
      if (player.getRect().intersects (blockList.get (i).getRect())) {
        intersect = player.getRect().intersection (blockList.get (i).getRect());
        
        if (intersect.width < intersect.height) {
          player.hit (blockList.get (i));
          player.setVx (0);
        } else if ((intersect.width > intersect.height) && (blockList.get (i).getY() > player.getY())) {
          player.stand (blockList.get (i));
          player.setVy (0);
          touchNum++;
          // standing on a block
          if (blockList.get (i) instanceof SideBlock) {
            player.moveHorizontal ((int) (blockList.get (i).getSpeed() * elapsedTime * 100));
          } // side to side moving blocks brings players with it
          
        } else if ((intersect.width > intersect.height) && (blockList.get (i).getY() < player.getY())) {
          player.headButt (blockList.get(i));
        } // hitting a block sideways
        
        if (blockList.get (i).getLethal ()) {
          player.loseLives();
          player.reset (resetX, resetY);
        } // hit a poison block
      }
    }
    
    // jumping and standing still sprites
    if (touchNum == 0) {
      player.nextSprite (2);
    } else if (player.getVx() == 0){
      player.nextSprite(1);
    }
  }
}