import java.util.Random;

public class TetrisGame {
  private static final int WIDTH = 10;
  private static final int HEIGHT = 20;

  private Block[][] grid;
  private Block currentPiece;

  public TetrisGame() {
    grid = new Block[WIDTH][HEIGHT];
    currentPiece = generateNewPiece();
  }

  public void play() {
    while (true) {
      printGrid();
      movePieceDown();
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private void printGrid() {
    System.out.println("+-" + "-".repeat(WIDTH * 2) + "+");
    for (int y = 0; y < HEIGHT; y++) {
      System.out.print("| ");
      for (int x = 0; x < WIDTH; x++) {
        if (grid[x][y] == null) {
          System.out.print("  ");
        } else {
          System.out.print(grid[x][y].getSymbol() + " ");
        }
      }
      System.out.println("|");
    }
    System.out.println("+-" + "-".repeat(WIDTH * 2) + "+");
  }

  private void movePieceDown() {
    if (canMovePieceDown()) {
      currentPiece.moveDown();
    } else {
      placeCurrentPieceOnGrid();
      currentPiece = generateNewPiece();
    }
  }

  private boolean canMovePieceDown() {
    for (Block block : getCurrentPieceBlocks()) {
      int x = block.getX();
      int y = block.getY() + 1;
      if (y >= HEIGHT || grid[x][y] != null) {
        return false;
      }
    }
    return true;
  }

  private void placeCurrentPieceOnGrid() {
    for (Block block : getCurrentPieceBlocks()) {
      int x = block.getX();
      int y = block.getY();
      grid[x][y] = block;
    }
  }

  private Block generateNewPiece() {
    Random random = new Random();
    int shape = random.nextInt(7);
    int x = WIDTH / 2;
    int y = 0;

    switch (shape) {
      case 0:
        // I shape
        return new Block(x, y, 'I');
      case 1:
        // J shape
        return new Block(x, y, 'J');
      case 2:
        // L shape
        return new Block(x, y, 'L');
      case 3:
        // O shape
        return new Block(x, y, 'O');
      case 4:
        // S shape
        return new Block(x, y, 'S');
      case 5:
        // T shape
        return new Block(x, y, 'T');
      default:
        // Z shape
        return new Block(x, y, 'Z');
    }
  }

  private Block[] getCurrentPieceBlocks() {
    Block[] blocks = new Block[4];
    int i = 0;
    for (int x = 0; x < WIDTH; x++) {
      for (int y = 0; y < HEIGHT; y++) {
        if (currentPiece.getX() == x && currentPiece.getY() == y) {
          blocks[i++] = currentPiece;
          if (currentPiece.getSymbol() == 'I') {
            blocks[i++] = new Block(x, y + 1, 'I');
            blocks[i++] = new Block(x, y + 2, 'I');
            blocks[i++] = new Block(x, y + 3, 'I');
          } else if (currentPiece.getSymbol() == 'J') {
            blocks[i++] = new Block(x - 1, y, 'J');
            blocks[i++] = new Block(x, y + 1, 'J');
            blocks[i++] = new Block(x, y + 2, 'J');
          } else if (currentPiece.getSymbol() == 'L') {
            blocks[i++] = new Block(x + 1, y, 'L');
            blocks[i++] = new Block(x, y + 1, 'L');
            blocks[i++] = new Block(x, y + 2, 'L');
          } else if (currentPiece.getSymbol() == 'O') {
            blocks[i++] = new Block(x, y + 1, 'O');
            blocks[i++] = new Block(x + 1, y, 'O');
            blocks[i++] = new Block(x + 1, y + 1, 'O');
          } else if (currentPiece.getSymbol() == 'S') {
            blocks[i++] = new Block(x, y + 1, 'S');
            blocks[i++] = new Block(x + 1, y, 'S');
            blocks[i++] = new Block(x - 1, y + 1, 'S');
          } else if (currentPiece.getSymbol() == 'T') {
            blocks[i++] = new Block(x - 1, y, 'T');
            blocks[i++] = new Block(x, y + 1, 'T');
            blocks[i++] = new Block(x + 1, y, 'T');
          } else if (currentPiece.getSymbol() == 'Z') {
            blocks[i++] = new Block(x, y + 1, 'Z');
            blocks[i++] = new Block(x - 1, y, 'Z');
            blocks[i++] = new Block(x + 1, y + 1, 'Z');
          }
        }
      }
    }
    return blocks;
  }
}
