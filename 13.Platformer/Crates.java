//Graphics &GUI imports
import javax.swing.*;
import java.awt.*;    
    
class Crates extends Walls{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image = Toolkit.getDefaultToolkit().getImage("crateImg.png");
    Crates(int x, int y, int width, int height){
        super(x,y,width,height);
    }
    
    @Override 
    public Image getImage(){
        return this.image;
    }
}