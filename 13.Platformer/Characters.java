//Graphics &GUI imports 
import javax.swing.*; 
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;  
 
public class Characters implements Directions{ 
    private int x = 0; 
    private int y; 
    private int width; 
    private int height; 
    private int powerUp; 
    private int position; 
    private int speed; 
    private int points; 
    private Rectangle hitbox; 
    private double deltaTime; 
    private int charInitialX; 
    private int direction;
    private int orientation;
    private boolean dirChanged = false;
    private BufferedImage grappleImage;
    private int grappleW, grappleH, grappleY = 0;
     
    
    public Characters(int x, int y, int width, int height){ 
        this.x = x; 
        this.y = y; 
        this.width = width; 
        this.height = height;
        this.grappleW = getCharW()/10;


        try{
            this.grappleImage = ImageIO.read (new File ("grappleImg.png"));
        }catch(Exception e){}
    } 
     
    public int getCharX(){ 
        return this.x; 
    } 
    public int getCharY(){ 
        return this.y; 
    } 
    public int getCharW(){ 
        return this.width; 
    } 
    public int getCharH(){ 
        return this.height; 
        
    }public int getCharInitialX(){ 
        return this.charInitialX; 
    } 
    public void setCharInitialX(int intialX){ 
        this.charInitialX = intialX; 
    } 
    public void updateInitialX(int x){ 
        this.charInitialX = this.charInitialX + x; 
    } 
     
    public void setCharX(int x){ 
        this.x = this.x + x; 
    } 
    public void setCharY(int y){ 
        this.y = this.y + y; 
    } 
    
    public void updateCharX(int x){
        this.x = x;
    }
 
    public void setDir(int dir){ 
        this.direction = dir; 
    } 
    
    public int getDir(){
        return this.direction;
    }
     
    public void setSpeed(int speed){ 
        this.speed = speed; 
    } 
     
    public void addPoint(){ 
        this.points += 1; 
    } 
     
    public int getPoints() { 
        return points; 
    } 
     
    public Rectangle getHitbox(){ 
        return hitbox; 
    }  
    
    public void setOrientation(int x){
        this.orientation = x;
    }
    
    public int getOrientation(){
        return this.orientation;
    }
    
    public void setDirChanged(boolean change){
        this.dirChanged = change;
    }
    
    public boolean getDirChanged(){
        return this.dirChanged;
    }
    
    public void moveVertical (int distance) { 
        y += distance; 
    } 
     
    public void moveHorizontal (int distance) { 
        x += distance; 
    } 
    
    public int getGrappleW(){ 
        return this.grappleW;
    } 
    public int getGrappleH(){ 
        return this.grappleH;
    } 
    public void setGrappleH(int h){ 
        this.grappleH = h;
    } 
    public int getGrappleY(){ 
        return this.grappleY;
    } 
    public void setGrappleY(int y){ 
        this.grappleY = y;
    }
    public void drawGrapple(Graphics g, Characters character){
        g.drawImage(this.grappleImage, character.getCharInitialX() + character.getCharW(), grappleY, character.getGrappleW(), grappleH, null);
    }
}