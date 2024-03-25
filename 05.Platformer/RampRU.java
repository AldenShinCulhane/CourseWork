/**
 * 
 * @Gerred G ICS4U
 * @version Jan 2020
 */

import javax.swing.*;
import java.awt.*;    

class RampRU extends Walls{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image imageRUp = Toolkit.getDefaultToolkit().getImage("Map Blocks/Ramp R Up.png");
    RampRU(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    
    @Override 
    public Image getImage(){
        return this.imageRUp;
    }
}