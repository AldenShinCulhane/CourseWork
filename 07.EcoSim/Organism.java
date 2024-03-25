abstract class Organism{
  private int health;
  private int initialHealth; 
  private int x;
  private int y;
  protected int age;
  
  // Constructors 
  Organism (int x, int y, int health) {
    this.health = health; 
    this.initialHealth = health; 
    this.x = x;
    this.y = y;
    this.age = 0;
  }  
  
  // Used for plants 
  Organism(int health) {
    this.health = health;
    this.age = 0;
    this.x = 0;
    this.y = 0;
  }
  
  // Setters & getters
  public int getHealth() {
    return health;
  }
  public int getX() {
    return x;
  }
  public int getY() {
    return y;
  }
  public int getAge() {
    return age;
  }
  public int getInitialHealth() {
    return initialHealth;
  }
  public void changeHealth(int health) {
    this.health += health;
  }
  public void setHealth(int health) {
    this.health = health;
  }
  public void setX(int x) {
    this.x = x;
  }
  public void setY(int y) {
    this.y = y;
  }
  public void changeAge(int age) {
    this.age += age;
  }
  public void setAge(int age){
    this.age = age;
  }
  
  // Compare method 
  public int compareTo(Organism organismTwo) {
    if (health > organismTwo.getHealth()) {
      return 1;
    } else if (health < organismTwo.getHealth()) {
      return -1;
    } else {
      return 0;
    }
  }
}