public class Block {
  private int x;
  private int y;
  private char symbol;

  public Block(int x, int y, char symbol) {
    this.x = x;
    this.y = y;
    this.symbol = symbol;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public char getSymbol() {
    return symbol;
  }

  public void moveDown() {
    y++;
  }

  public void moveLeft() {
    x--;
  }

  public void moveRight() {
    x++;
  }
}
