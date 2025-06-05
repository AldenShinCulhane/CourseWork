/*
 * Player.java
 * Version 1.0
 * @Cindy Wang + Charles Wong 
 * @01/21/2019
 * Player object in program
 */

import java.awt.*;
import java.io.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.util.ArrayList;

class Player implements Movable {
  private int x;
  private int y;
  private double vY;
  private double vX;
  private int width;
  private int height;
  
  private int direction;
  private int position;
  
  private int speed;
  
  private int lives; 
  private int coins;
  private Inventory inventory;
  
  private ArrayList <Projectile> magazine;
  
  private Rectangle hitbox;
  
  private BufferedImage[] spriteR;
  private BufferedImage[] spriteL;
  
  private BufferedImage livesImage;
  private BufferedImage coinsImage;
  
  private BufferedImage rightB;
  private BufferedImage leftB;
  
  private double deltaTime;
  
  public Player(){ //constructor
    spriteR = new BufferedImage [4];
    spriteL = new BufferedImage [4];
    
    loadSprites();
    loadImages();
    x = 0;
    y = 0;
    width = 49;
    height = 77;
    
    hitbox = new Rectangle (x, y, width, height + 1);
    
    magazine = new ArrayList <Projectile> ();
    
    inventory = new Inventory();
    
    direction = 1;
    position = 1;
    speed = 5;
    lives = 4;
    coins = 15;
    
    deltaTime = 0;
  }
  
  //load images
  private void loadSprites(){
    try{
      for (int i = 0; i < 4; i++) {
        spriteR [i] = ImageIO.read (new File ("images/sprites/right" + (i + 1) + ".png"));
      }
      
      for (int i = 0; i < 4; i++) {
        spriteL [i] = ImageIO.read (new File ("images/sprites/left" + (i + 1) + ".png"));
      }
    }catch(Exception e){System.out.println("Error: player sprite failed to load");}
  }
  
  private void loadImages(){
    try{
      livesImage = ImageIO.read (new File ("images/playerAtributes/lives.png"));
      coinsImage = ImageIO.read (new File ("images/playerAtributes/coins.png"));
      rightB = ImageIO.read (new File ("images/sprites/bullets/fireballR.png"));
      leftB = ImageIO.read (new File ("images/sprites/bullets/fireballL.png"));
    }catch(Exception e) {
      System.out.println("Error: lives image failed to load");
    }
  }
  
  public void draw(Graphics g){
    if(inventory.getVisible()){
      inventory.draw(g);
    }
    g.setColor (Color.WHITE);
    g.setFont(new Font("Monospaced", Font.BOLD, 20)); 
    g.drawString("x "+lives,1200,30);
    g.drawImage(livesImage, 1160,10,null);
    
    g.drawString("x "+coins,1200,70);
    g.drawImage(coinsImage, 1160,50,null);
    
    if(direction == 0){
      g.drawImage (spriteL [position - 1], x - 2, y, null);
    } else if(direction == 1){
      g.drawImage (spriteR [position - 1], x - 2, y, null);
    }
    
    for (int i = 0; i < magazine.size(); i++) {
      if (magazine.get (i).getDir () == 0) {
        g.drawImage (leftB, magazine.get (i).getX(), magazine.get (i).getY(), null);
      } else if (magazine.get (i).getDir () == 1) {
        g.drawImage (rightB, magazine.get (i).getX(), magazine.get (i).getY(), null);
      }
    }
  }
  
  //get inventory
  public Inventory getInventory(){
    return inventory;
  }
  
  public void shoot (double elapsedTime) {
    deltaTime += elapsedTime;
    
    if (deltaTime >= 0.1) {
      magazine.add (new Projectile (x, y, direction));
      deltaTime = 0;
    }
  }
  
  public void updateBullets () {
    for (int i = magazine.size() - 1; i > -1; i--){
      if (magazine.get (i).getX() < 0 || magazine.get (i).getX() > 1280){
        magazine.remove(i);
      }
    }
  }
  
  public ArrayList <Projectile> getMag () {
    return magazine;
  }
  
  //moving up and down
  public void moveVertical (int distance) {
    hitbox.translate (0, distance);
    y += distance;
  }
  
  public void moveHorizontal (int distance) {
    hitbox.translate (distance, 0);
    x += distance;
  }
  
  //set direction
  public void setDir (int dir) {
    direction = dir;
  }
  
  //get x, y + height and hitbox
  public int getX () {
    return x;
  }
  
  public int getY () {
    return y;
  }
  
  public int getHeight() {
    return height;
  }
  
  public Rectangle getRect() {
    return hitbox;
  }
  
  //set next sprite
  public void nextSprite (){
    if (position < 4) {
      position ++;
    } else if (position == 4) {
      position = 1;
    }
  }
  
  public void nextSprite (int pos) {
    position = pos;
  }
  
  //get and set vertical and horizontal velocity
  public double getVy () {
    return vY;
  }
  
  public void setVy (double amt) {
    vY = amt;
  }
  
  public double getVx () {
    return vX;
  }
  
  public void setVx (double amt) {
    vX = amt;
  }
  
  //setting horizontal speed
  public int getSpeed() {
    return speed;
  }
  
  //resetting player's location to spawn point
  public void reset (int x, int y) {
    this.x = x;
    this.y = y;
    hitbox.setLocation (x,y);
    vY = 0;
  }
  
  //player is standing
  public void stand (Block block) {
    y = block.getY() - height;
    hitbox.setLocation (x, y);
  }
  
  //if player hits the block
  public void hit (Block block) {
    if (block.getX() > x) {
      x = block.getX() - width;
    } else if (block.getX() < x) {
      x = block.getX() + block.getRect().width;
    }
    hitbox.setLocation (x, y);
  }
  
  public void headButt (Block block) {
    y = block.getY() + block.getRect().height;
    hitbox.setLocation (x, y);
  }
  
  //add speed (boost)
  public void addSpeed(){
    this.speed += 1;
  }
  
  //add, lose, get lives + coins
  public void addLives(){
    this.lives += 1;
  }
  
  public void loseLives(){
    this.lives -= 1;
  }
  
  public int getLives () {
    return lives;
  }
  
  public void addCoins(){
    this.coins += 2;
  }
  
  public void loseCoins(int loss){
    this.coins -= loss;
  }
  
  public int getCoins(){
    return this.coins;
  }
}