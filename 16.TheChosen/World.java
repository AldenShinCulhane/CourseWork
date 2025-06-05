/*
 * World.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Overall setting outside of village (lobby)
 */

import java.util.ArrayList;
import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class World extends Runnable implements InputReq {
  
  private ArrayList <StoryLine> storyList;
  
  private BufferedImage bg;
  private BufferedImage portals;
  private BufferedImage[] highlights;
  BufferedImage chains;
  
  private SetBlock ground;
  
  private int currentStory;
  private boolean[] unlocked;
  
  
  private int gravity;
  private Player player;
  private double deltaTime;
  
  World (Player player) { //constructor
    storyList = new ArrayList <StoryLine> ();
    highlights = new BufferedImage [4];
    
    try {
      bg = ImageIO.read (new File ("images/world/worldBG.png"));
      portals = ImageIO.read (new File ("images/world/portals.png"));
      chains = ImageIO.read (new File ("images/world/chains.png"));
      
      for (int i = 0; i < 4; i++)
        highlights [i] = ImageIO.read (new File ("images/world/highlight" + (i + 1) + ".png"));
                                       
    } catch (Exception E) {
      System.out.println ("Error: world image failed to load");
    }
    
    storyList = FileIO.readFile (player);
    
    unlocked = new boolean [4];
    unlocked [0] = true;
    
    for (int i = 0; i < 4; i++)  //for full access of all levels
      unlocked [i] = true;
    
    this.player = player;
    ground = new SetBlock (0, 738, 5, false);
    currentStory = 0;
    gravity = 6;
  }
  
  public void draw (Graphics g) {
    if (currentStory == 0) { //if in the level selection
      g.drawImage (bg, 0, 0, null);
      g.drawImage (portals, 0, 0, null);
      if (player.getX() > 0 && player.getX() < 296) {
        g.drawImage (highlights [0], 0, 0, null);
      } else if (player.getX() > 297 && player.getX() < 616) {
        g.drawImage (highlights [1], 0, 0, null);
      } else if (player.getX() > 617 && player.getX() < 936) {
        g.drawImage (highlights [2], 0, 0, null);
      } else if (player.getX() > 937 && player.getX() < 1280) {
        g.drawImage (highlights [3], 0, 0, null);
      }
      
      for (int i = 0; i < 4; i++) {
        if (!unlocked [i]) {
          g.drawImage (chains, i * 320, 365, null);
        }
      } // draw chains
      
      ground.draw (g);
      player.draw(g);
    } else { //if in the level
      storyList.get ((currentStory - 1)).draw (g);
    }
  }
  
  //update elements
  public void update (double elapsedTime, boolean key []) {
    if (currentStory > 0) { //if in the level
      if (storyList.get (currentStory - 1).getStatus()) {
        storyList.get (currentStory - 1).update(elapsedTime, key);
      }
      storyList.get (currentStory - 1).end(); // check if the story has ended
      
      if (!storyList.get (currentStory - 1).getStatus()) {
        if (currentStory < 4 && player.getLives() > 0)
          unlocked [currentStory] = true;
        currentStory = 0;
        player.reset (0, 0);
      } // return to the world from levels
      
    } else { //if in the level selection
      deltaTime += elapsedTime;
      movement (elapsedTime, key);
      if (player.getX() > 0 && player.getX() < 296 && key [3]) {
        if (unlocked [0]) {
          currentStory = 1;
          player.reset (1281, 0);
          storyList.get (currentStory - 1).run();
        }
      } else if (player.getX() > 297 && player.getX() < 616 && key [3]) {
        if (unlocked [1]) {
          currentStory = 2;
          player.reset (1281, 0);
          storyList.get (currentStory - 1).run();
        }
      } else if (player.getX() > 617 && player.getX() < 936 && key [3]) {
        if (unlocked [2]) {
          currentStory = 3;
          player.reset (1281, 0);
          storyList.get (currentStory - 1).run();
        }
      } else if (player.getX() > 937 && player.getX() < 1280 && key [3]) {
        if (unlocked [3]) {
          currentStory = 4;
          player.reset (1281, 0);
          storyList.get (currentStory - 1).run();
        }
      }
      
      if (key [4]) {
        player.shoot(elapsedTime);
        player.nextSprite (2);
      }
      
      for (int i = 0; i < player.getMag().size(); i++){
        player.getMag().get(i).update(elapsedTime);
      }
      player.updateBullets ();
      
    }
  }
  
  public void end () { // end the world (exit to village)
    if (currentStory == 0) {
      if (player.getX() < 0) {
        super.end();
        player.reset (1280, 661);
      }
    }
  }
  
  public void run () { // run the world
    super.run();
    player.reset (0, 661);
  }
  
  //player movement
  private void movement (double elapsedTime, boolean key []) {  
    int xD;
    int yD;
    
    if (key [0]) { //player falling
      if (player.getY() + player.getHeight() == ground.getY()) {
        player.setVy (-40);
      }
    }
    
    if (key [1]) { //player moving left
      player.setVx (-player.getSpeed());
      player.setDir (0);
      if (deltaTime >= 0.1) {
        player.nextSprite();
        deltaTime = 0;
      }
    } else  if (key [2]) { //player moving right
      player.setVx (player.getSpeed());
      player.setDir (1);
      if (deltaTime >= 0.1) {
        player.nextSprite();
        deltaTime = 0;
      }
    } else { //playing standing
      player.setVx (0);
    }
    
    //setting x and y displacement
    xD = (int) (elapsedTime * player.getVx() * 100);
    player.moveHorizontal (xD);
    
    player.setVy (player.getVy() + (gravity * elapsedTime * 40));
    yD = (int) (player.getVy() * elapsedTime * 40);
    player.moveVertical (yD);
    
    //player standing
    if (player.getY() + player.getHeight() >= ground.getY()) {
      player.stand (ground);
      player.setVy (0);
    }
    
    //player in air (jumping/falling)
    if (player.getY() + player.getHeight() < ground.getY()) {
      player.nextSprite (2);
    } else if (player.getVx() == 0){
      player.nextSprite(1);
    }
  }
}