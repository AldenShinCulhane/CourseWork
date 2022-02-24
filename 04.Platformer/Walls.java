//Graphics &GUI imports
import javax.swing.*;
import java.awt.*;    

class Walls{
    private int x;
    private int y;
    private int width;
    private int height;
    private int initialX;
    private int initialY;
    Walls(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public int getImageX(){
        return this.x;
    }
    public int getImageY(){
        return this.y;
    }
    public int getImageW(){
        return this.width;
    }
    public int getImageH(){
        return this.height;
    }
    public void setImageX(int x){
        this.x = this.x + x;
    }
    public void setImageY(int Y){
        this.y = this.y + y;
    }
    public int getImageInitialX(){
        return this.initialX;
    }
    public int getImageInitialY(){
        return this.initialY;
    }
    public void setImageInitialX(int x){
        this.initialX = x;
    }
    public void setImageInitialY(int y){
        this.initialY = y;
    }
    
    
    //gets overidden
    public Image getImage(){
        return null;
    }
}