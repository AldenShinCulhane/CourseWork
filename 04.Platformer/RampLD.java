/**
 * 
 * @Gerred G ICS4U
 * @version Jan 2020
 */

import javax.swing.*;
import java.awt.*;    

class RampLD extends Walls{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image imageLDown = Toolkit.getDefaultToolkit().getImage("Map Blocks/Ramp L Down.png");
    RampLD(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    
    @Override 
    public Image getImage(){
        return this.imageLDown;
    }
}