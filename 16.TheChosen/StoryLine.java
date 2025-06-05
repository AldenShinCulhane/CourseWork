/*
 * StoryLine.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Grouping object of levels
 */

import java.util.ArrayList;
import java.awt.*;

public class StoryLine extends Runnable implements InputReq {
  
  ArrayList <Level> lvlList;
  private int currentLvl;
  
  StoryLine () {
    lvlList = new ArrayList <Level> ();
    currentLvl = 1;
  }
  
  public void draw (Graphics g) {
    lvlList.get ((currentLvl - 1)).draw (g);
  }
  
  public void update (double elapsedTime, boolean key []) {
    if (currentLvl == 1) {
      lvlList.get (0).run();
    } // run the first level at the beginning
    
    if (currentLvl > 0) {
      lvlList.get ((currentLvl - 1)).update(elapsedTime, key);
    }
    lvlList.get ((currentLvl - 1)).end(); // check if the level has ended
    nextLvl(); // check if need to go to the next level
  }
  
  private void nextLvl () {
    if (!lvlList.get ((currentLvl - 1)).getStatus()) {
      if (currentLvl < lvlList.size()) {
        currentLvl++;
        lvlList.get ((currentLvl - 1)).run();
      } else if (currentLvl == lvlList.size()) {
        currentLvl = 0;
      }
    }
  }
  
  public void end () {
    if (currentLvl == 0) {
      super.end ();
    }
  }
  
  public void run () {
    super.run ();
    currentLvl = 1;
  }
  
  public void addLvl (Level lvl) {
    lvlList.add (lvl);
  }
}