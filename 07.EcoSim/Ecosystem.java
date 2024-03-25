/*
 * AnimalGame.java
 * Alden Shin-Culhane
 * 11/20/2018
 * Animal EcoSim Game
 * 
 */
import javax.swing.JOptionPane;

class Ecosystem {
  public static void main(String[] args) {
    // Variables
    int initialSheep, initialWolves, initialHealth; 
    int plantNutrition, plantSpawnRate, plantSpawnAttempts; 
    int length, width; 
    int wolfCount, sheepCount, turnCount; // Counters
    String answer; 
    String terrain;
    int option; // Answers for option panes
    Object[] options = {"Field", "Snow", "Desert", "City"}; // Different terrains 
    Board map; 
    DisplayGrid grid; 
    
    /*----------------------------------------------------
     *                           Main Program
     * ----------------------------------------------------
     */
    
    // Dimensions of the board
    // User input
    answer = JOptionPane.showInputDialog("How large do you want the field to be?");
    
    // Finds length in their answer 
    length = Integer.parseInt(answer); 
    
    // Multiplies the length by itself because the board is a square 
    width = length; 
    
    // Gives user option of what terrain type they want 
    option = JOptionPane.showOptionDialog(null, "What ecosystem would you like?", "Select an option", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    
    // If they choose "Field"
    if (option == 0) {
      JOptionPane.showMessageDialog(null, "The fields have high plant nutrition and spawn rate."); // Gives user description of terrain 
      // High nutrition 
      plantNutrition = 2; 
      
      // High spawn rate 
      plantSpawnRate = 2; 
      
      // Initial sheep related to board size 
      initialSheep = (length*width)/10;
      
      // Initial wolves related to board size 
      initialWolves = initialSheep/10;
      
      // High initial health 
      initialHealth = 40; 
      
      // Set terrain 
      terrain = "field";
      
      // Call map object 
      map = new Board(length, width, plantNutrition, true);
      
      // If they choose "Snow"
    } else if (option == 1) {
      JOptionPane.showMessageDialog(null, "Very cold and very few plants grow."); // Gives user description of terrain 
      // Low nutrition 
      plantNutrition = 1; 
      
      // Low spawn rate 
      plantSpawnRate = 1;
      
      // Low initial sheep 
      initialSheep = (length*width)/30; 
      
      // Low inital wolves
      initialWolves = initialSheep/5; 
      
      // Lower initial health 
      initialHealth = 20;
      
      // Set Terrain 
      terrain = "snow";
      
      // Call map object 
      map = new Board(length, width, plantNutrition, true);
      
      // If they choose "Desert" 
    } else if (option == 2) {
      JOptionPane.showMessageDialog(null, "Few, but nutritious plants."); // Gives user description of terrain 
      // High nutriton 
      plantNutrition = 2; 
      
      // Low spawn rate 
      plantSpawnRate = 1;
      
      // Moderate initial sheep amount 
      initialSheep = (length*width)/20; 
      
      // Low initial wolf amount 
      initialWolves = initialSheep/5; 
      
      // Low initial health 
      initialHealth = 20;
      
      // Set terrain 
      terrain = "desert";
      
      // Call map object 
      map = new Board(length, width, plantNutrition, true);
      
      // If they choose "City"
    } else {
      JOptionPane.showMessageDialog(null, "Animals are treated well, plants are hard to reproduce"); // Gives user description of terrain 
      // High nutrition 
      plantNutrition = 2; 
      
      // Low spawn rate 
      plantSpawnRate = 1;
      
      // Moderate initial sheep amount
      initialSheep = length*width/20; 
      
      // Low initial wolf amount 
      initialWolves = initialSheep/5; 
      
      // High initial health (animals are treated better in the city)
      initialHealth = 50;
      
      // Set terrain 
      terrain = "city";
      
      // Call map object 
      map = new Board(length, width, plantNutrition, true);
    }
    
    // Determining the number of attempted plant spawns per turn
    int multiplier = (int)Math.pow(10, (4-plantSpawnRate));
    plantSpawnAttempts = plantSpawnRate * (int)Math.ceil((double)(length*width)/(multiplier));
    
    // Spawn plants and animals 
    // Spawn sheep 
    for (int i=0; i<initialSheep; i++) {
      Sheep sheep = new Sheep(initialHealth);
      sheep.setMoved(false);
      map.randomGender(sheep);
      map.randomCoords(sheep);
    }
    // Spawn wolves
    for (int i=0; i<initialWolves; i++) {
      Wolf wolf = new Wolf(initialHealth);
      wolf.setMoved(false);
      map.randomGender(wolf);
      map.randomCoords(wolf);
    }
    // Spawn plants 
    for (int i=0; i<=plantSpawnAttempts; i++) {
      map.spawnPlant();
    }
    
    // Initialize display
    grid = new DisplayGrid(map.getBoard());
    
    // Set terrain 
    grid.setTerrain(terrain);
    
    // Counters
    turnCount = 0;
    sheepCount = 0;
    wolfCount = 0;
    
    // Counting animals 
    for (int i=0; i<length; i++) {
      for (int j=0; j<width; j++) {
        map.resetMoved(i, j);
        Organism organism = map.getOrganism(i, j);
        if (organism == null) {
        } else if (organism instanceof Sheep) {
          sheepCount++;
        } else if (organism instanceof Wolf) {
          wolfCount++;
        } 
      }
    }
    System.out.println("Starting with " + sheepCount + " sheep");
    System.out.println("Starting with " + wolfCount + " wolves");
    System.out.println();
    
    boolean triggeredEnd = false, endLoop=false;
    
    /*----------------------------------------------------
     *                           Simulation
     * ----------------------------------------------------
     */
    while(!endLoop) {
      try{ Thread.sleep(500); }catch(Exception c) {}; 
      // Loop count 
      turnCount++; 
      
      // Counters
      sheepCount = 0;
      wolfCount = 0;
      
      // Change if animals eat 
      for (int i=0; i<length; i++) {
        for (int j=0; j<width; j++) {
          map.resetHasEaten(i, j);
        }
      }
      
      // Spawn plants
      for (int i=0; i<plantSpawnAttempts; i++) {
        map.spawnPlant();
      }
      
      // Allows organisms to move and age
      for (int i=0; i<length; i++) {
        for (int j=0; j<width; j++) {
          Organism organism = map.getOrganism(i, j);
          if (organism != null) {
            if (organism instanceof Grass) {
              map.age(organism);
            } else if (organism instanceof Animal && !((Animal)organism).getMoved()) {
              map.moveDecision((Animal)organism);
              ((Animal)organism).setMoved(true);
              map.age(organism);
            } 
          }
        }
      }
      
      // Counters
      for (int i=0; i<length; i++) {
        for (int j=0; j<width; j++) {
          map.resetMoved(i, j);
          Organism organism = map.getOrganism(i, j);
          if (organism == null) {
          } else if (organism instanceof Sheep) {
            sheepCount++;
          } else if (organism instanceof Wolf) {
            wolfCount++;
          }
        }
      }
      
      // Passes updated board to grid
      grid.changeGrid(map.getBoard());
      grid.refresh();
      
      // Outputs counters to console
      System.out.println(turnCount + " turns so far");
      System.out.println("There are " + sheepCount + " sheep left");
      System.out.println("There are " + wolfCount + " wolves left");
      System.out.println("");
      
      // Message if a species is extinct 
      if (!triggeredEnd && (wolfCount == 0 || sheepCount == 0)) {
        option = JOptionPane.showConfirmDialog(null, "Sheep or wolves have gone extinct. End program?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (option == 0) {
          endLoop = true;
        } else {
          triggeredEnd = true;
        }
      }
    }
    
    // End messages 
    System.out.println("This simulation took " + turnCount + " turns.");
    System.out.println("There are " + sheepCount + " sheep left.");
    System.out.println("There are " + wolfCount + " wolves left.");
  }
}

