/*
 * FileIO.java
 * Version 1.0
 * @Charles Wong
 * @01/21/2019
 * Reads text files and generates levels
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

class FileIO {
  
  private static Level readLvlFile (String fileName, Player player) {
    Level level = new Level (player);
    
    try {
      
      File layoutFile = new File (fileName + "/layout.txt");
      Scanner reader = new Scanner (layoutFile);
      
      int x = reader.nextInt();
      int y = reader.nextInt();
      
      level.addReset (x, y);
      
      while (reader.hasNext()) {
        int blockType = reader.nextInt();
        int blockSize = reader.nextInt();
        int blockX = reader.nextInt();
        int blockY = reader.nextInt();
        int end;
        
        switch (blockType) {
          case 0:
            SetBlock sBlock;
            int lethal = reader.nextInt();
            if (lethal == 0) {
              sBlock = new SetBlock (blockX, blockY, blockSize, false);
            } else {
              sBlock = new SetBlock (blockX, blockY, blockSize, true);
            }
            level.addBlock (sBlock);
            break;
          case 1:
            end = reader.nextInt();
            SideBlock block = new SideBlock (blockX, blockY, end, blockSize, false);
            level.addBlock (block);
            break;
          case 2:
            end = reader.nextInt();
            Elevator eBlock = new Elevator (blockX, blockY, end, blockSize, false);
            level.addBlock (eBlock);
            break;
          case 3:
            end = reader.nextInt();
            SideBlock blockL = new SideBlock (blockX, blockY, end, blockSize, true);
            level.addBlock (blockL);
            break;
          case 4:
            end = reader.nextInt();
            Elevator eBlockL = new Elevator (blockX, blockY, end, blockSize, true);
            level.addBlock (eBlockL);
            break;
        }
      }
      reader.close();
      
    } catch (Exception E) {
      System.out.println ("Error: Level " + fileName + " failed to load");
    }
    
    try {
        File monsterFile = new File (fileName + "/monsters.txt");
        Scanner monsReader = new Scanner (monsterFile);
        
        while (monsReader.hasNext()) {
          int place = monsReader.nextInt();
          level.addMonster (place);
        }
        monsReader.close();
      } catch (Exception e) {
        System.out.println ("Error: Monsters not loaded");
      }
    
    return level;
  } // end of readLvlFile
  
  private static StoryLine readStory (String fileName, Player player) {
    
    StoryLine story = new StoryLine ();
    
    try {
      String listFile = fileName;
      listFile += "/lvlList.txt";
      File myFile = new File (listFile);
      Scanner reader = new Scanner (myFile);
      
      while (reader.hasNext()){
        String lvlName = fileName;
        lvlName += "/";
        lvlName += reader.nextLine();
        
        Level lvl = readLvlFile (lvlName, player);
        
        story.addLvl (lvl);
      }
      
      reader.close();
    } catch (Exception E) {
      System.out.println ("Error: Storyline failed to load");
    }
    
    return story;
  } // end of readStory
  
  public static ArrayList <StoryLine> readFile (Player player) {
    
    ArrayList <StoryLine> storyList = new ArrayList <StoryLine> ();
    
    for (int i = 1; i < 5; i++) {
      StoryLine story = new StoryLine ();
      
      String folder = "blueprints/story";
      folder += i;
      
      story = readStory (folder, player);
      
      storyList.add (story);
    }
    
    return storyList;
  }
}