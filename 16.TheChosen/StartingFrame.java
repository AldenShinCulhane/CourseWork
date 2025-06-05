/*
 * StartingFrame.java
 * Version 1.0
 * @Cindy Wang
 * @01/21/2019
 * Main menu of program
 */


//Imports
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

  
class StartingFrame extends JFrame { 

  JFrame thisFrame;
  
  //Constructor - this runs first
  StartingFrame() { 
    super("Start Screen");
    this.thisFrame = this; //lol  
    
    //configure the window
    this.setSize(1280,768);    
    this.setLocationRelativeTo(null); //start the frame in the center of the screen
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    this.setResizable (false);
    
    //Create a Panel for stuff
    JPanel decPanel = new DecoratedPanel();
    
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.setBackground(new Color(0, 0, 0, 0));
    mainPanel.setPreferredSize(new Dimension(1280,768));
    
    
    //Create a JButton for the centerPanel
    //start button
    ImageIcon sb =new ImageIcon("images/menu/startButton.png");
    
    JButton startButton = new JButton(sb);
    startButton.setBackground(new Color(0, 0, 0, 0));
    startButton.setRolloverIcon(new ImageIcon("images/menu/startButtonPressed.png"));
    startButton.setBorder(BorderFactory.createEmptyBorder());
    startButton.setFocusPainted(false);
    startButton.addActionListener(new StartButtonListener());
    
    //create button
    ImageIcon create =new ImageIcon("images/menu/createButton.png");
    
    JButton createButton = new JButton(create);
    createButton.setBackground(new Color(0, 0, 0, 0));
    createButton.setRolloverIcon(new ImageIcon("images/menu/createButtonPressed.png"));
    createButton.setBorder(BorderFactory.createEmptyBorder());
    createButton.setFocusPainted(false);
    createButton.addActionListener(new CreateButtonListener());
    
    //exit button
    ImageIcon exit =new ImageIcon("images/menu/exitButton.png");
    
    JButton exitButton = new JButton(exit);
    exitButton.setBackground(new Color(0, 0, 0, 0));
    exitButton.setRolloverIcon(new ImageIcon("images/menu/exitButtonPressed.png"));
    exitButton.setBorder(BorderFactory.createEmptyBorder());
    exitButton.setFocusPainted(false);
    exitButton.addActionListener(new ExitButtonListener());
    
    //create bottom panel
    JPanel bottomPanel = new JPanel();
    bottomPanel.setPreferredSize(new Dimension(1280,300)); 
    bottomPanel.setBackground(new Color(0, 0, 0,0)); //!!
    
    //add buttons
    bottomPanel.add(startButton);
    bottomPanel.add(createButton);
    bottomPanel.add(exitButton);

    //Add panels to the mainPanel 
    mainPanel.add(bottomPanel,BorderLayout.SOUTH);
    
    decPanel.add(mainPanel);
    
    //add the main panel to the frame
    this.add(decPanel);
    
    //Start the app
    this.setVisible(true);
  }
  
  //INNER CLASS - Overide Paint Component for JPANEL
  private class DecoratedPanel extends JPanel {
    
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);     
        Image pic = new ImageIcon( "images/menu/backgroundVillage.png" ).getImage();
        g.drawImage(pic,0,0,null); 
   }
  
   
  }
  //This is an inner class that is used to detect a button press
 class StartButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      new GameWindow();
    }
  }
 
 class CreateButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose(); 
      new CreatorWindow ();
    }
  }
 
 class ExitButtonListener implements ActionListener {  //this is the required class definition
    public void actionPerformed(ActionEvent event)  {  
      thisFrame.dispose();
      System.exit(0);
    }
  }
  
}