/*
 * Runnable.java
 * Version 1.0
 * @Charles Wong 
 * @01/21/2019
 * super class for all things that can be run (level, world, storyline, etc.)
 */

class Runnable {
  
  private boolean status;
  
  Runnable () {
    status = false;
  }
  
  Runnable (boolean set) {
    status = set;
  }
  
  public boolean getStatus () {
    return status;
  }
  
  public void run () {
    status = true;
  }
  
  public void end () {
    status = false;
  }
}