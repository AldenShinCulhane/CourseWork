/*
 * DrawInputReq.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Interface for requirements to draw + input
 */

import java.awt.*;

interface DrawReq {
  
  void draw (Graphics g);
  void update(double elapsedTime);
}

interface InputReq {
  
  void draw (Graphics g);
  void update (double elapsedTime, boolean[] key);
}