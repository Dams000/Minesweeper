import java.util.Random;

public class Board {

    private final int width;
    private final int height;
    private final int numberOfBombs = 20;
    private final Cell[][] hiddenBoard;

    public Board(int width, int height){
        this.width = width;
        this.height = height;

        this.hiddenBoard = new Cell[height][width];

        initBoard();
        plantBombs();
    }

    private void plantBombs() {
        Random rand = new Random();
        int bombsToPlace = numberOfBombs;
        do {
            int r = rand.nextInt(height);
            int c = rand.nextInt(width);
            if (hiddenBoard[r][c].getValue() != 9){
                hiddenBoard[r][c].setValue(9);
                bombsToPlace--;
            }
        } while (bombsToPlace > 0);
    }

    private void initBoard() {
        for (int row = 0; row < height; row++){
            for (int column = 0; column < width; column++){
                hiddenBoard[row][column] = new Cell(0);
            }
        }
    }

    @Override
    public String toString() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++)
                System.out.print(hiddenBoard[r][c].getValue() + " ");
            System.out.println();
        }
        return null;
    }
}
