/*
 * Inventory.java
 * Version 1.0
 * @Cindy Wang
 * @01/21/2019
 * Inventory that takes in item boosts for player
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;

class Inventory {
  private boolean visible;
  private int heartPotion;
  private int speed;
  
  private BufferedImage[] itemsImages;
  private BufferedImage[] nums;
  
  public Inventory(){ //constructor
    itemsImages = new BufferedImage[2];
    nums = new BufferedImage [2];
    loadImages();
    visible = true;
    heartPotion = 1;
    speed = 1;
  }
  
  private void loadImages(){
    try{
      itemsImages[0] = ImageIO.read (new File ("images/inventory/potion.png"));
      itemsImages[1] = ImageIO.read(new File ("images/inventory/speed.png"));
      
      for (int i = 0; i < 2; i++) {
        nums [i] = ImageIO.read (new File ("images/inventory/" + (i + 1) + ".png"));
      }
    }catch(Exception e){
      e.printStackTrace();
      System.out.println("Error: inventory images failed to load");}
  }
  
  public void draw(Graphics g){
    g.setColor (Color.WHITE);
    g.setFont(new Font("Monospaced", Font.BOLD, 20)); 
    g.drawString("x "+heartPotion,1100,30);
    g.drawString("x "+speed,1100,70);
    
    for(int i = 0; i<2; i++){
      g.drawImage(itemsImages[i], 1060, 10 + i*40, null);
      g.drawImage(nums [i], 1020, 10 + i*40, null);
    }
  }
  
  //get + set visible status of inventory
  public boolean getVisible(){
    return visible;
  }
  
  public void setVisible(boolean status){
    this.visible = status;
  }
  
  //use, add, get num heartPotion
  public void useHeartPotion(){
    heartPotion -= 1;
  }
  
  public void addHeartPotion(){
    heartPotion += 1;
  }
  
  public int getHeartPotion(){
    return heartPotion;
  }
  
  //use, add, get num speed boosts
  public void useSpeed(){
    speed -= 1;
  }
  
  public void addSpeed(){
    speed += 1;
  }
  
  public int getSpeed(){
    return speed;
  }
}