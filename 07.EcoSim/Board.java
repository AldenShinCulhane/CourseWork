class Board {
  // Variables
  private Organism[][] board; // 2d array of organism objects
  private int length; // Length of board
  private int width; // Width of board
  private int plantNutrition; // How much health plants give 
  private boolean oldAge; // If animals are old 
  
  // Constructor
  Board(int length, int width, int plantNutrition, boolean intelligence) {
    board = new Organism[length][width];
    this.length = length;
    this.width = width;
    this.plantNutrition = plantNutrition;
  }
  
  // Setters & getters
  public void setOldAge(boolean oldAge) { 
    this.oldAge = oldAge;
  }
  
  public Organism[][] getBoard() {
    return board;
  }
  
  public Organism getOrganism(int y, int x) {
    return board[y][x];
  }
  
  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
  public void age(Organism organism) { // Age method
    organism.changeAge(1); 
    
    // Changes health of animal 
    if (organism instanceof Animal) {
      int healthLost = (int)Math.ceil(organism.getAge()*0.01); 
      organism.changeHealth(-healthLost);
      
      // Changes nutrition of plants (decay over time)
    } else if (organism instanceof Grass) {
      int newHealth = (int) Math.floor(plantNutrition*(-1*organism.getAge()*organism.getAge() + 20*organism.getAge() + organism.getInitialHealth()));
      organism.setHealth(newHealth); 
    }
    
    // Sanity check 
    if (organism.getX() < 0 || organism.getX() >= length || organism.getY() < 0 || organism.getY() >= length){
      return; 
    }
    
    // Organism has died, point to null
    if (organism.getHealth() < 1) {
      board[organism.getY()][organism.getX()] = null; 
    } 
    
    // Organism is too old, point to null 
    if (oldAge && organism.getAge() > 100) {
      board[organism.getY()][organism.getX()] = null;
    }
  }
  
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void moveDecision(Animal a) { // Method decides whether animals move
      moveDirection(a); 
    }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void moveDirection(Animal animal) { // Method for deciding direction of animal movement
    int direction; 
    boolean triedMoving = false; 
    
    // For wolves
    if (animal instanceof Wolf) {
      if (animal.getX() > 0 && animal.getY() >= 0 && animal.getY() < length) { // Prevents going out of the array 
        if (board[animal.getY()][animal.getX()-1] != null) {
          if (board[animal.getY()][animal.getX()-1] instanceof Sheep) { // Checks to see if an adjacent spot is a sheep
            moveAnimal(animal, 0); 
            triedMoving = true; 
          }
        }
      }
      if (animal.getX() < width-1 && animal.getY() >= 0 && animal.getY() < length  && !triedMoving) { // Prevents going out of the array 
        if (board[animal.getY()][animal.getX()+1] != null) {
          if (board[animal.getY()][animal.getX()+1] instanceof Sheep) {
            moveAnimal(animal, 1);
            triedMoving = true;
          }
        }
      }
      if (animal.getY() > 0 && animal.getX() >= 0 && animal.getX() < length && !triedMoving) { // Prevents going out of the array 
        if (board[animal.getY()-1][animal.getX()] != null) {
          if (board[animal.getY()-1][animal.getX()] instanceof Sheep) {
            moveAnimal(animal, 2);
            triedMoving = true;
          }
        }
      }
      if (animal.getY() < length-1 && animal.getX() >= 0 && animal.getX() < length && !triedMoving) { // Prevents going out of the array 
        if (board[animal.getY()+1][animal.getX()] != null) {
          if (board[animal.getY()+1][animal.getX()] instanceof Sheep) {
            moveAnimal(animal, 3);
            triedMoving = true;
          }
        }
      }
    }
    // Sheep want to eat if they are near food and hungry
    if (animal instanceof Sheep && !triedMoving) {
      int highestNutrition = 0; 
      direction = 5; 
      if (animal.getX() > 0 && animal.getY() < length && animal.getY() >= 0) { // Prevents going out of the array 
        if (board[animal.getY()][animal.getX()-1] != null) {
          if (board[animal.getY()][animal.getX()-1] instanceof Grass && board[animal.getY()][animal.getX()-1].getHealth() > highestNutrition) { // Prevents going out of the array 
            highestNutrition = board[animal.getY()][animal.getX()-1].getHealth();
            direction = 0; 
          }
        }
      }
      if (animal.getX() < width-1 && animal.getY() < length && animal.getY() >= 0) { // Prevents going out of the array 
        if (board[animal.getY()][animal.getX()+1] != null) {
          if (board[animal.getY()][animal.getX()+1] instanceof Grass && board[animal.getY()][animal.getX()+1].getHealth() > highestNutrition) {
            highestNutrition = board[animal.getY()][animal.getX()+1].getHealth();
            direction = 1; 
          }
        }
      }
      if (animal.getY() > 0 && animal.getX() < length && animal.getX() >= 0) { // Prevents going out of the array 
        if (board[animal.getY()-1][animal.getX()] != null) {
          if (board[animal.getY()-1][animal.getX()] instanceof Grass && board[animal.getY()-1][animal.getX()].getHealth() > highestNutrition) {
            highestNutrition = board[animal.getY()-1][animal.getX()].getHealth();
            direction = 2;
          }
        }
      }
      if (animal.getY() < length-1 && animal.getX() < length && animal.getX() >= 0) { // Prevents going out of the array 
        if (board[animal.getY()+1][animal.getX()] != null) {
          if (board[animal.getY()+1][animal.getX()] instanceof Grass && board[animal.getY()+1][animal.getX()].getHealth() > highestNutrition) {
            highestNutrition = board[animal.getY()+1][animal.getX()].getHealth();
            direction = 3;
          }
        }
      }
      if (direction == 5) { // 5 = no actual direction
        triedMoving = false;
      } else {
        moveAnimal(animal, direction);
        triedMoving = true;
      }
    }
    
    // Makes sheep want to mate if they're high in health
    if (animal instanceof Sheep && animal.getHealth() > 40 && !triedMoving) {
      if (animal.getX() > 0 && animal.getY() >= 0 && animal.getY() < length) {
        if (board[animal.getY()][animal.getX()-1] != null) {
          if (board[animal.getY()][animal.getX()-1] instanceof Sheep && ((Animal)board[animal.getY()][animal.getX()-1]).getHealth() > 40 && ((Animal)board[animal.getY()][animal.getX()-1]).getGender() != animal.getGender()) {
            moveAnimal(animal, 0);
            triedMoving = true;
          }
        }
      }
      if (animal.getX() < width-1 && animal.getY() >= 0 && animal.getY() < length && !triedMoving) {
        if (board[animal.getY()][animal.getX()+1] != null) {
          if (board[animal.getY()][animal.getX()+1] instanceof Sheep && ((Animal)board[animal.getY()][animal.getX()+1]).getHealth() > 40 && ((Animal)board[animal.getY()][animal.getX()+1]).getGender() != animal.getGender()) {
            moveAnimal(animal, 1);
            triedMoving = true;
          }
        }
      }
      if (animal.getY() > 0 && animal.getX() >= 0 && animal.getX() < length  && !triedMoving) {
        if (board[animal.getY()-1][animal.getX()] != null) {
          if (board[animal.getY()-1][animal.getX()] instanceof Sheep && ((Animal)board[animal.getY()-1][animal.getX()]).getHealth() > 40 && ((Animal)board[animal.getY()-1][animal.getX()]).getGender() != animal.getGender()) {
            moveAnimal(animal, 2);
            triedMoving = true;
          }
        }
      }
      if (animal.getY() < length-1 && animal.getX() >= 0 && animal.getX() < length && !triedMoving) {
        if (board[animal.getY()+1][animal.getX()] != null) {
          if (board[animal.getY()+1][animal.getX()] instanceof Sheep && ((Animal)board[animal.getY()+1][animal.getX()]).getHealth() > 40 && ((Animal)board[animal.getY()+1][animal.getX()]).getGender() != animal.getGender()) {
            moveAnimal(animal, 3);
            triedMoving = true;
          }
        }
      }
    }
    
    if (!triedMoving) {
      // Randomly moves in a direction if they don't have better logic hit 
      direction = (int) Math.floor(Math.random() * 4);
      moveAnimal(animal, direction);
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void resetMoved(int y, int x) { // Method to reset "moved"
    // Organism has not moved this turn, resets the value of moved to false
    if (board[y][x] == null) {
    } else if (board[y][x] instanceof Animal) {
      ((Animal)board[y][x]).setMoved(false);
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void resetHasEaten(int y, int x) { // Method to reset "eaten"
    if (board[y][x] == null) {
    } else if (board[y][x] instanceof Animal) {
      ((Animal)board[y][x]).setHasEaten(false);
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void addToArray(Organism organism) { // Adds/assigns objects to 2d array 
    if (board[organism.getY()][organism.getX()] == null) {
      board[organism.getY()][organism.getX()] = organism;
      
    } else if (board[organism.getY()][organism.getX()] instanceof Grass && !(organism instanceof Grass)) {
      if (organism instanceof Sheep) {
        // If a baby spawns on food it consumes it
        organism.changeHealth(board[organism.getY()][organism.getX()].getHealth());
        board[organism.getY()][organism.getX()] = organism;
        ((Animal)organism).setHasEaten(true);
      } else {
        board[organism.getY()][organism.getX()] = organism;
      }
      
    } else if (!(organism instanceof Grass)) {
      double boardCounter = 0; 
      double area = length*width; 
      for (int i=0; i<length; i++) {
        for (int j=0; j<width; j++) {
          // Counts empty spaces 
          if (board[i][j] == null) {
            boardCounter++; 
          }
        }
      }
      if ((boardCounter/area) > 0.5) {
        randomCoords(organism); 
      } else if (boardCounter != area) {
        for (int i=0; i<length; i++) {
          for (int j=0; j<width; j++) {
            if (board[i][j] == null) {
              organism.setX(j);
              organism.setY(i);
              addToArray(organism);
            }
          }
        }
      }
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void spawnPlant() { // Plant spawning method
    Grass plant;
    plant = new Grass(); // Calls constructor that doesn't have x & y coords (only reason those constructors even exist)
    randomCoords(plant); // Then sets x & y coords 
  } 
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void randomCoords(Organism organism) { // Randomizes coordinates of an organism trying to spawn
    organism.setY((int)(Math.floor(Math.random() * length)));
    organism.setX((int)(Math.floor(Math.random() * width)));
    addToArray(organism); 
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void randomGender(Animal animal) { // Randomizing gender of animals
    // Randomizes a gender
    int random = (int)(Math.floor(Math.random() * 2));
    if (random == 0) {
      animal.setGender(true);
    } else {
      animal.setGender(false);
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void directionImplement(Animal organism, int direction) { // Changes coordinates of animals 
    if (direction == 0) {
      organism.moveLeft();
    } else if (direction == 1) {
      organism.moveRight();
    } else if (direction == 2) {
      organism.moveUp();
    } else if (direction == 3) {
      organism.moveDown();
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public boolean wolvesNear (Animal organism) { // Checks in neighbouring spots have wolves
    boolean wolvesAround = true;
    try {
      if (organism.getX() > 0 && organism.getY() >= 0 && organism.getY() < length) {
        if (!(board[organism.getY()][organism.getX()-1] instanceof Wolf)) {
          return false;
        }
      }
      if (organism.getX() < width-1 && organism.getY() >= 0 && organism.getY() < length) {
        if (!(board[organism.getY()][organism.getX()+1] instanceof Wolf)) {
          return false;
        }
      }
      if (organism.getY() > 0 && organism.getX() >= 0 && organism.getX() < length) {
        if (!(board[organism.getY()-1][organism.getX()] instanceof Wolf)) {
          return false;
        }
      }
      if (organism.getY () < length-1 && organism.getX() >= 0 && organism.getX() < length)  {
        if (!(board[organism.getY()+1][organism.getX()] instanceof Wolf)) {
          return false;
        }
      }
      return wolvesAround;
    } catch (NullPointerException e) {
      return false;
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public boolean spawnChildren (Animal parent, int potentialX, int potentialY) { // Spawns children next to their parents
    int parentX = parent.getX(); 
    int parentY = parent.getY();
    Animal child; // Declare the animal class
    int childX=0, childY=0; 
    try {
      if (parentX > 0) {
        childX = parentX-1;
        childY = parentY;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          }  else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (parentX < width-1) {
        childX = parentX+1;
        childY = parentY;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (parentY > 0) {
        childX = parentX;
        childY = parentY-1;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (parentY < length-1) {
        childX = parentX;
        childY = parentY+1;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      } 
      if (potentialX > 0) {
        childX = potentialX-1;
        childY = potentialY;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (potentialX < width-1) {
        childX = potentialX+1;
        childY = potentialY;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (potentialY > 0) {
        childX = potentialX;
        childY = potentialY-1;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      if (potentialY < length-1) {
        childX = potentialX;
        childY = potentialY+1;
        if (!(board[childY][childX] instanceof Animal)) {
          if (parent instanceof Sheep) {
            child = new Sheep(20);
          } else {
            child = new Wolf(20);
          }
          child.setX(childX);
          child.setY(childY);
          randomGender(child);
          board[childY][childX] = child;
          return true;
        }
      }
      return false;
    } catch (NullPointerException e) {
      if (parent instanceof Sheep) {
        child = new Sheep(20);
      } else {
        child = new Wolf(20);
      }
      child.setX(childX);
      child.setY(childY);
      randomGender(child);
      board[childY][childX] = child;
      return true;
    }
  }
  
  // --------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  public void moveAnimal(Animal organism, int direction) {
    boolean wolvesAround = wolvesNear(organism); 
    boolean canMove = false; 
    int potentialX = 0;
    int potentialY = 0; 
    
    // Error catching
    if (organism == null){
      return;
    }
    
    // Sanity check
    if (organism.getX() < 0 || organism.getX() > length-1 || organism.getY() < 0 || organism.getY() > length-1) {
      return;
    }
    
    /* 0 = left
     * 1 = right
     * 2 = up
     * 3 = down
     */
    
    // Sets new X and Y coord for an open space 
    if (direction == 0 && organism.getX() > 0) {
      potentialX = organism.getX()-1;
      potentialY = organism.getY();
      canMove = true;
    } else if (direction == 1 && organism.getX() < width-1) {
      potentialX = organism.getX()+1;
      potentialY = organism.getY();
      canMove = true;
    } else if (direction == 2 && organism.getY() > 0) {
      potentialX = organism.getX();
      potentialY = organism.getY()-1;
      canMove = true;
    } else if (direction == 3 && organism.getY() < length-1) {
      potentialX = organism.getX();
      potentialY = organism.getY()+1;
      canMove = true;
    }
    
    // Only happens if they can move 
    if (canMove == true) {
      
      // If the space is empty 
      if (board[potentialY][potentialX] == null) {
        
        // Moves onto the empty space
        board[organism.getY()][organism.getX()] = null;
        directionImplement(organism, direction);
        board[potentialY][potentialX] = organism;
        
        // If there's a plant 
      } else if (board[potentialY][potentialX] instanceof Grass) {
        board[organism.getY()][organism.getX()] = null;
        directionImplement(organism, direction);
        // If it's not a wolf they eat the plant 
        if (organism instanceof Sheep) {
          int health = organism.getHealth();
          organism.changeHealth(health*4/5);
          board[potentialY][potentialX] = organism;
          organism.setHasEaten(true);
        } else {
          board[potentialY][potentialX] = organism;
        }
      }
    } 
    // Sheep avoid wolves 
    if (board[potentialY][potentialX] instanceof Wolf && (organism instanceof Sheep) && !wolvesAround) {
      boolean tried = false;
      if (organism.getX() > 0 && direction != 0) {
        try {
          if (!(board[organism.getY()][organism.getX()-1] instanceof Wolf)) {
            moveAnimal(organism, 0); // Tries to move to the left
            tried = true;
          }
        } catch(NullPointerException e) {
          moveAnimal(organism, 0);
          tried = true;
        }
      } 
      
      if (organism.getX() < width-1 && direction!= 1 && !tried) {
        try {
          if (!(board[organism.getY()][organism.getX()+1] instanceof Wolf)) {
            moveAnimal(organism, 1); // Moves right
            tried = true;
          }
        } catch(NullPointerException e) {
          moveAnimal(organism, 1);
          tried = true;
        }
      }
      if (organism.getY() > 0 && direction!= 2 && !tried) {
        try {
          if (!(board[organism.getY()-1][organism.getX()] instanceof Wolf)) {
            moveAnimal(organism, 2); // Moves up
            tried = true;
          }
        } catch(NullPointerException e) {
          moveAnimal(organism, 2);
          tried = true;
        }
      }
      if (organism.getY() < length-1 && direction!= 3 && !tried) {
        try {
          if (!(board[organism.getY()+1][organism.getX()] instanceof Wolf)) {
            moveAnimal(organism, 3); 
            tried = true;
          }
        } catch(NullPointerException e) {
          moveAnimal(organism, 3);
          tried = true;
        }
      }
      
      // If there's no wolf nearby and the orgaism is a wolf 
    } else if (!(board[potentialY][potentialX] instanceof Wolf) && organism instanceof Wolf) {
      board[organism.getY()][organism.getX()] = null;
      directionImplement(organism, direction);
      organism.changeHealth((organism.getHealth()*3)/5);
      organism.setHasEaten(true);
      board[potentialY][potentialX] = organism;
      
      // If there's potential to mate (sheep or wolves)
    } else if (board[potentialY][potentialX] instanceof Animal && organism instanceof Animal && (organism.getGender() != ((Animal)board[potentialY][potentialX]).getGender())) {
    
      // Determines the interaction 
      if (organism instanceof Sheep && board[potentialY][potentialX] instanceof Sheep) {
        boolean spawned; 
 
        // Spawns children
        do {
          spawned = spawnChildren((Animal)organism, potentialX, potentialY);
          board[potentialY][potentialX].changeHealth(-10);
          organism.changeHealth(-10);
        } while (board[potentialY][potentialX].getHealth() > 20 && organism.getHealth() > 20 && spawned);
        
        // Wolf babies
      } else if (organism instanceof Wolf && board[potentialY][potentialX] instanceof Wolf) {
        boolean spawned; 
        int counter = 0; 
        
        do {
          spawned = spawnChildren((Animal)organism, potentialX, potentialY);
          counter++;
          board[potentialY][potentialX].changeHealth(-10);
          organism.changeHealth(-10);
        } while (board[potentialY][potentialX].getHealth() > 20 && organism.getHealth() > 20 && spawned && counter<5);
        
        
        // Wolf fights
      } else if (board[potentialY][potentialX] instanceof Wolf && organism instanceof Wolf) {
        // Determines the outcome of a fight between wolves of the same gender
        int winner = organism.compareTo((Animal)board[potentialY][potentialX]);
        if (winner == 1) {
          board[potentialY][potentialX].changeHealth(-10);
          if (board[potentialY][potentialX].getHealth() < 1) {
            // If a wolf dies the other one moves onto the space
            board[organism.getY()][organism.getX()] = null;
            directionImplement(organism, direction);
            board[organism.getY()][organism.getX()] = organism;
          }
        } else if (winner == -1) {
          organism.changeHealth(-10);
          if (organism.getHealth() < 1) {
            board[organism.getY()][organism.getX()] = null;
          }
        } else {
          // In the event of a tie
          int random = (int)(Math.floor(Math.random() * 2));
          if (random == 0) {
            organism.changeHealth(-10);
            if (organism.getHealth() < 1) {
              board[organism.getY()][organism.getX()] = null;
            }
          } else {
            board[potentialY][potentialX].changeHealth(-10);
            if (board[potentialY][potentialX].getHealth() < 1) {
              board[organism.getY()][organism.getX()] = null;
              directionImplement(organism, direction);
              board[organism.getY()][organism.getX()] = organism;
            } 
          } 
        } 
      } 
    } 
  } // End of method
} // End of file

