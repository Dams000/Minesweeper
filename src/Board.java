import java.util.Random;

public class Board {

    private final int width;
    private final int height;
    private final int numberOfBombs = 20;
    private final Cell[][] hiddenBoard;
    private Cell[][] displayedBoard;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;

        this.hiddenBoard = new Cell[height][width];
        this.displayedBoard = new Cell[height][width];

        initBoards();
        plantBombs();
        fillBoard();
    }

    private void initBoards() {
        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                hiddenBoard[row][column] = new Cell();
                displayedBoard[row][column] = new Cell();
            }
        }
    }

    private void plantBombs() {
        Random rand = new Random();
        int bombsToPlace = numberOfBombs;
        do {
            int r = rand.nextInt(height);
            int c = rand.nextInt(width);
            if (hiddenBoard[r][c].getValue() != 9) {
                hiddenBoard[r][c].setValue(9);
                bombsToPlace--;
            }
        } while (bombsToPlace > 0);
    }

    private void fillBoard() {
        for (int row = 0; row < height; row++)
            for (int column = 0; column < width; column++)
                if (hiddenBoard[row][column].getValue() != 9)
                    hiddenBoard[row][column].setValue(getNumberOfSurroundingBombs(row, column));
    }

    private int getNumberOfSurroundingBombs(int row, int column) {
        int n = 0;
        for (int r = Math.max(0, row - 1); r < Math.min(height, row + 2); r++)
            for (int c = Math.max(0, column - 1); c < Math.min(width, column + 2); c++)
                if (hiddenBoard[r][c].getValue() == 9)
                    n++;
        return n;
    }

    public void dig(int row, int column) {
        if (hiddenBoard[row][column].getValue() == 0)
            digSurroundings(row, column);
        else if (displayedBoard[row][column] != null) {
            displayedBoard[row][column] = hiddenBoard[row][column];
        }
    }

    private void digSurroundings(int row, int column) {
        for (int r = Math.max(0, row - 1); r < Math.min(height, row + 2); r++)
            for (int c = Math.max(0, column - 1); c < Math.min(width, column + 2); c++){
                if (r != row && c != column)
                    dig(r, c);
            }
    }

    @Override
    public String toString() {
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++)
                System.out.print(displayedBoard[r][c].getValue() + " ");
            System.out.println();
        }
        return null;
    }
}
