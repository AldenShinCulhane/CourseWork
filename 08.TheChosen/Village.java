/*
 * Village.java
 * Version 1.0
 * @Charles Wong + Cindy Wang + Alden Shin-Culhane
 * @01/21/2019
 * Village of game
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class Village implements InputReq {
  
  private World world;
  
  private SetBlock ground;
  private int gravity;
  private Player player;
  private double deltaTime;
  
  private BufferedImage villageBackground[];
  
  Village (Player player) { //constructor
    villageBackground = new BufferedImage[3];
    
    loadImages();
    
    world = new World (player);
    
    this.player = player;
    ground = new SetBlock (0, 738, 5, false);
    gravity = 6;
  }
  
  //load images
  public void loadImages(){
    try{
      for(int i = 0; i<3; i++){
      villageBackground[i] = ImageIO.read (new File ("images/village/bg" + (i+1) + ".png"));
      }
    }catch(Exception e){System.out.println("Error: village failed to load");}
  }
  
  public void draw (Graphics g) {
    if (world.getStatus()) {
      world.draw (g);
    } else {
     if(player.getX() > 110 && player.getX() < 280){
        g.drawImage(villageBackground[1], 0,0,null);
      }else if(player.getX() > 468 && player.getX() < 638){
          g.drawImage(villageBackground[2], 0,0,null);
        }else{
            g.drawImage(villageBackground[0], 0,0,null);
          }
      ground.draw (g);
      player.draw(g);
    }
  }
  
  //update elements
  public void update (double elapsedTime, boolean key []) {
    if (world.getStatus()) {
      world.update (elapsedTime, key);
      world.end();
    } else {
      deltaTime += elapsedTime;
      movement (elapsedTime, key);
      if (player.getX() > 1280) {
        world.run();
      }else if (player.getX() < 0){
        player.reset(0, player.getY());
      }
      
      //purchase speed boost
      if(player.getCoins()>10 && player.getX() > 110 && player.getX() < 280 && key[5]){
          player.getInventory().addSpeed();
          player.loseCoins(10);
          //purchase heart potion
        }else if(player.getCoins()>20 && player.getX() > 468 && player.getX() < 638 && key[5]){
          player.getInventory().addHeartPotion();
          player.loseCoins(20);
        }
        key[5] = false;
    }
    
     //use heart potion
        if(player.getInventory().getHeartPotion() > 0 && key[6]){
          player.getInventory().useHeartPotion();
          player.addLives();
        }
        key[6] = false;
        //use speed boost
        if(player.getInventory().getSpeed() > 0 && key[7]){
          player.getInventory().useSpeed();
          player.addSpeed();
        }
        key[7] = false;
        //toggle inventory on/off
        if(player.getInventory().getVisible() && key[8]){
          player.getInventory().setVisible(false);
        }else if(!player.getInventory().getVisible() && key[8]){
          player.getInventory().setVisible(true);
        }
        key[8] = false;
  }
  
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
    } else { //player standing
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