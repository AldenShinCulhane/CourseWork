abstract class Animal extends Organism implements Moveable{
  private boolean gender;
  private boolean moved;
  private boolean hasEaten;
  
  // Constructors 
  Animal(int health) {
    super(health);
    moved = true;
    hasEaten = false;
  }
  
  Animal(int x, int y, int health, boolean gender) {
    super(x, y, health);
    this.gender = gender;
    moved = true;
    hasEaten = false;
  }
  
  abstract public void moveUp() ;
  
  abstract public void moveDown();
  
  abstract public void moveLeft();
  
  abstract public void moveRight();
  
  // Setters & getters
  public void setGender(boolean gender) {
    this.gender = gender;
  }
  public void setMoved(boolean moved) {
    this.moved = moved;
  }
  public void setHasEaten(boolean hasEaten) {
    this.hasEaten = hasEaten;
  }
  public boolean getGender() {
    return gender;
  }
  public boolean getMoved() {
    return moved;
  }
  public boolean getHasEaten() {
    return hasEaten;
  }
}
