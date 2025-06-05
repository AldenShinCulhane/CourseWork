/*
 * Monster.java
 * Version 1.0
 * @Cindy Wang + Charles Wong
 * @01/21/2019
 * Monster enemy 
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class Monster implements DrawReq {
  private int x;
  private int y;
  private int width;
  private int height;
  private Rectangle hitbox;
  
  private double vX;
  
  private int health;

  private int direction;
  private int position;
  private double deltaTime;
  
  private Block home;
  
  private BufferedImage[] spriteR;
  private BufferedImage[] spriteL;
  
  public Monster(Block block){ //constructor
    spriteR = new BufferedImage [4];
    spriteL = new BufferedImage [4];
    
    loadSprites();
    
    width = 48;
    height = 42;
    
    x = block.getX();
    y = block.getY() - height;
    
    hitbox = new Rectangle (x, y, width, height);
    
    home = block;
    
    direction = 1;
    position = 1;
    vX = 3;
    health = 10;
    deltaTime = 0;
  }
  
  //load images
  private void loadSprites(){
    try{
      for (int i = 0; i < 4; i++) {
        spriteR [i] = ImageIO.read (new File ("images/monsters/purpleright" + (i + 1) + ".png"));
      }
      
      for (int i = 0; i < 4; i++) {
        spriteL [i] = ImageIO.read (new File ("images/monsters/purpleleft" + (i + 1) + ".png"));
      }
    }catch(Exception e){System.out.println("error loading sprite");};
  }
  
  public void draw(Graphics g){
    if(direction == -1){
      g.drawImage (spriteL [position - 1], x, y, null);
    } else if(direction == 1){
      g.drawImage (spriteR [position - 1], x, y, null);
    }
  }
  
  //update elements
  public void update (double elapsedTime) {
    deltaTime += elapsedTime;
    if (x <= home.getX()) {
      setDir (1);
    } else if ((x + width) >= (home.getX() + home.getRect().width)) {
      setDir (-1);
    }
    
    int xD;
    xD = (int) (elapsedTime * vX * direction * 100);
    moveHorizontal (xD);

    if (deltaTime >= 0.1) {
      nextSprite();
      deltaTime = 0;
    }
  }
  
  //moving
  public void moveVertical (int distance) {
  }
  
  public void moveHorizontal (int distance) {
    hitbox.translate (distance, 0);
    x += distance;
  }
  
  //setting direction + changing sprites
  private void setDir (int i) {
    direction = i;
  }
  
  private void nextSprite (){
    if (position < 4) {
      position ++;
    } else if (position == 4) {
      position = 1;
    }
  }
  
  //get x + y
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  //return hitbox
  public Rectangle getRect() {
    return hitbox;
  }
  
  //get + take damage
  public int getHp() {
    return health;
  }
  
  public void takeDamage () {
    health--;
  }
  
}