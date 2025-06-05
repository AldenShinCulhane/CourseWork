/* [DisplayGrid.java]
 * A Small program for Display a 2D String Array graphically
 * @author Mangat
 */

// Graphics Imports
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.BorderLayout;


class DisplayGrid { 
  
  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private Organism[][] world;
  private String terrain;
  
  DisplayGrid(Organism[][] w) { 
    this.world = w;
    this.terrain = "Field";
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height;
    GridToScreenRatio = maxY / (world.length+1);  //ratio to fit in screen as square map
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    this.frame = new JFrame("Map of World");
    GridAreaPanel worldPanel = new GridAreaPanel();
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  public void changeGrid(Organism[][] w) {
    this.world = w;
  }
  
  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }
  
  public void refresh() { 
    frame.repaint();
  }
  
  class GridAreaPanel extends JPanel {
    public void paintComponent(Graphics g) {        
      super.repaint();
      setDoubleBuffered(true); 
      
      // Grass texture
      Image grass = Toolkit.getDefaultToolkit().getImage("images/grass.png");
      
      // Animal textures
      Image sheep = Toolkit.getDefaultToolkit().getImage("images/sheep.png");
      Image babySheep = Toolkit.getDefaultToolkit().getImage("images/babySheep.png");
      Image wolf = Toolkit.getDefaultToolkit().getImage("images/wolf.png");
      Image babyWolf = Toolkit.getDefaultToolkit().getImage("images/babyWolf.png");
      
      if (terrain.equals("desert") || terrain.equals("snow") || terrain.equals("city")) {
        grass = Toolkit.getDefaultToolkit().getImage("images/grass.png");
      }
      
      // loop for 2d array 
      for(int i = 0; i<world[0].length; i++) { 
        for(int j = 0; j<world.length; j++) { 
          
          // If there's a plant
          if (world[i][j] instanceof Grass) {
            g.drawImage(grass,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
            
            // If there's a wolf 
          } else if (world[i][j] instanceof Wolf) {
            // If there's a baby wolf 
            if (world[i][j].getAge() < 10) {
              g.drawImage(babyWolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              
              // Adult wolf 
            } else {
              g.drawImage(wolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
            }
            
            // If there's a sheep 
          } else if (world[i][j] instanceof Sheep) {
            // If there's a baby sheep 
            if (world[i][j].getAge() < 10) {
              g.drawImage(babySheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              //System.out.println("b"+i+j+world[i][j].getAge());
              //System.out.printf("%p\n" , world[i][j]);
              
              // Adult sheep 
            } else {
              g.drawImage(sheep,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              //System.out.println("a"+i+j+world[i][j].getAge());
            }
          } 
        }
      }
    }
  }// End of GridAreaPanel
}// End of DisplayGrid
