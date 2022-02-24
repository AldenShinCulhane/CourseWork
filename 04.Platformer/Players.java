// Graphics & GUI imports
import javax.swing.*;
import java.awt.*; 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Players extends Characters{
    private BufferedImage[] spriteRunning;
    private BufferedImage[] spriteRunningFlip;
    private BufferedImage[] spriteJumping;
    private BufferedImage[] spriteJumpingFlip;
    private BufferedImage[] spriteGrappling;
    private BufferedImage[] spriteGrapplingFlip;
    private BufferedImage spriteStanding;
    private BufferedImage spriteStandingFlip;
    private BufferedImage spriteCrouching;
    private BufferedImage spriteCrouchingFlip;
    private int index;
    
//    private BufferedImage pointsImage;
//    private BufferedImage speed100Image;
//    private BufferedImage speed75Image;
//    private BufferedImage speed50Image;
//    private BufferedImage speed25Image;
//    private BufferedImage speed0Image;
    
    public Players (int x, int y, int width, int height){
        super (x, y, width, height);
        spriteRunning = new BufferedImage [6];
        spriteRunningFlip = new BufferedImage [6];
        spriteJumping = new BufferedImage [4];
        spriteJumpingFlip = new BufferedImage [4];
        spriteGrappling = new BufferedImage [5];
        spriteGrapplingFlip = new BufferedImage [5];
        
        loadSprites();
    }
    
    public void setIndex(int index){
        this.index = index;
    }
    
    public int getIndex(){
        return this.index;
    }
    
    // load sprites
    private void loadSprites(){
        try{
            for (int i = 0; i < 6; i++){ // running sprite
                spriteRunning[i] = ImageIO.read (new File ("Sprites/running" + i + ".png"));
                spriteRunningFlip[i] = ImageIO.read (new File ("Sprites/runningFlip" + i + ".png"));
            }
            for (int i = 0; i < 4; i++){ // jumping sprite
                spriteJumping[i] = ImageIO.read (new File ("Sprites/jumping" + i + ".png"));
                spriteJumpingFlip[i] = ImageIO.read (new File ("Sprites/jumpingFlip" + i + ".png"));
            }
            for (int i = 0; i < 5; i++){ // grappling sprite
                spriteGrappling[i] = ImageIO.read (new File ("Sprites/grappling" + i + ".png"));
                spriteGrapplingFlip[i] = ImageIO.read (new File ("Sprites/grapplingFlip" + i + ".png"));
            }
            
            // misc sprite positions
            spriteStanding = ImageIO.read (new File ("Sprites/standing.png"));
            spriteStandingFlip = ImageIO.read (new File ("Sprites/standingFlip.png"));
            spriteCrouching = ImageIO.read (new File ("Sprites/crouching.png"));
            spriteCrouchingFlip = ImageIO.read (new File ("Sprites/crouchingFlip.png"));
            
        }catch (Exception e){
            System.out.println("Error: Player sprite failed to load.");
        }
    }
    
    public void draw(Graphics g, Players player, int x, int y){
        int direction = player.getDir();
        
        if (player.getDirChanged()){
            setIndex(0);
            player.setDirChanged(false);
        }
        
        if (direction == STANDING_RIGHT){
            g.drawImage(spriteStanding, x, player.getCharY(), player.getCharW(), player.getCharH(), null); 
            
        }else if (direction == STANDING_LEFT){
            g.drawImage(spriteStandingFlip, x, player.getCharY(), player.getCharW(), player.getCharH(), null); 
            
        }else if (direction == RUNNING_RIGHT){
            if (index > spriteRunning.length-1){
                setIndex(0);
            }
            g.drawImage(spriteRunning[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == RUNNING_LEFT){
            if (index > spriteRunningFlip.length-1){
                setIndex(0);
            }
            g.drawImage(spriteRunningFlip[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == CROUCHING_RIGHT){
            g.drawImage(spriteCrouching, x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            
        }else if (direction == CROUCHING_LEFT){
            g.drawImage(spriteCrouchingFlip, x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            
        }else if (direction == JUMPING_RIGHT){
            if (index > spriteJumping.length-1){
                setIndex(0);
            }
            g.drawImage(spriteJumping[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == JUMPING_LEFT){
            if (index > spriteJumpingFlip.length-1){
                setIndex(0);
            }
            g.drawImage(spriteJumpingFlip[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == GRAPPLING_RIGHT){
            if (index > spriteGrappling.length-1){
                setIndex(0);
            }
            g.drawImage(spriteGrappling[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == GRAPPLING_LEFT){
            if (index > spriteGrapplingFlip.length-1){
                setIndex(0);
            }
            g.drawImage(spriteGrapplingFlip[index], x, player.getCharY(), player.getCharW(), player.getCharH(), null);
            setIndex(getIndex()+1);
        }else if (direction == ABILITY_RIGHT){
        }else if (direction == ABILITY_LEFT){
        }
                  
//                      final int STANDING_RIGHT = 0;
//    final int STANDING_LEFT = 1;
//    final int RUNNING_RIGHT = 2;
//    final int RUNNING_LEFT = 3;
//    final int CROUCHING_RIGHT = 4;
//    final int CROUCHING_LEFT = 5;
//    final int JUMPING_RIGHT = 6;
//    final int JUMPING_LEFT = 7;
//    final int GRAPPLING_RIGHT = 8;
//    final int GRAPPLING_LEFT = 9;
//    final int ABILITY_RIGHT = 10;
//    final int ABILITY_LEFT = 11;
    }
    
    
//    if(direction == 0){
//        g.drawImage (spriteL [position - 1], x - 2, y, null);
//    } else if(direction == 1){
//        g.drawImage (spriteR [position - 1], x - 2, y, null);
//    }
}