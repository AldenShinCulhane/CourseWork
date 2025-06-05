class Sheep extends Animal implements Moveable{
  Sheep(int health) {
    super(health);
  }
  
  public void moveUp() {
    this.setY(this.getY()+1);
  };
  
  public void moveDown() {
    this.setY(this.getY()-1);
  };
  
  public void moveLeft() {
    this.setX(this.getX()+1);
  };
  
  public void moveRight(){
    this.setX(this.getX()-1);
  };
}