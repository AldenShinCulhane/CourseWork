// Graphics & GUI imports
import javax.swing.*;
import java.awt.*; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;

public class CPU extends Characters{
    private BufferedImage image;
    public CPU(int x, int y, int width, int height){
        super(x,y,width,height);
        try{
            this.image = ImageIO.read(getClass().getResource("botImg.png"));
        }catch(Exception e){}
    }
    
    public void draw(Graphics g,CPU bot){
        g.drawImage(this.image, bot.getCharX(), bot.getCharY(),bot.getCharW(),bot.getCharH(), null); 
    }
  

    
    
}