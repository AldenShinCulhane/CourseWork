/*
 * Projectile.java
 * Version 1.0
 * @Charles Wong + Justin Wang
 * @01/21/2019
 * Monster enemy 
 */

import java.awt.*;

class Projectile {
  private int x;
  private int y;
  private Rectangle hitbox;
  private double vX;
  
  private int direction;
  
  public Projectile(int pX, int pY, int dir){ //constructor
    
    x = pX + 20;
    y = pY + 40;
    
    hitbox = new Rectangle (x, y, 25, 15);
    
    direction = dir;
    vX = 8;
  }
  
  //update elements
  public void update (double elapsedTime) {
    int xD;
    if (direction == 0) {
      xD = (int) (elapsedTime * -vX * 100);
      moveHorizontal (xD);
    } else if (direction == 1) {
      xD = (int) (elapsedTime * vX * 100);
      moveHorizontal (xD);
    }
  }
  
  //move + get x, y coordinate, direction, hitbox
  private void moveHorizontal (int distance) {
    hitbox.translate (distance, 0);
    x += distance;
  }
  
   public int getX () {
    return x;
  }
   
   public int getY () {
     return y;
   }
   
   public int getDir () {
     return direction;
   }
  
  public Rectangle getRect() {
    return hitbox;
  }
}