class Wolf extends Animal implements Moveable {
  Wolf(int health) {
    super(health);
  }
  Wolf(int x, int y, int health, boolean gender) {
    super(x, y, health, gender);
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
