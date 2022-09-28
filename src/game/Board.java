package game;

import minesweeperImages.ImageManager;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Board extends JPanel implements MouseListener {

    private final int preferredScreenSize = 600;
    private final int screenWidth;
    private final int screenHeight;
    private final int numberOfCol;
    private final int numberOfRows;
    private final int numberOfBombs;
    private final Cell[][] hiddenBoard;
    private final Cell[][] displayedBoard;
    private final int minCellSize = 15;
    private final int cellSize;
    private int numberOfDugSpots;
    private final ImageManager iM;
    private boolean gameOver;

    public Board(int numberOfCol, int numberOfRows, int numberOfBombs) {
        this.numberOfCol = numberOfCol;
        this.numberOfRows = numberOfRows;
        this.numberOfBombs = numberOfBombs;

        this.cellSize = Math.max(preferredScreenSize / Math.max(numberOfCol, numberOfRows), minCellSize);

        screenWidth = cellSize * numberOfCol;
        screenHeight = cellSize * numberOfRows;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GRAY);
        this.addMouseListener(this);

        this.hiddenBoard = new Cell[numberOfRows][numberOfCol];
        this.displayedBoard = new Cell[numberOfRows][numberOfCol];

        iM = new ImageManager(this);

        initBoards();
        plantBombs();
        fillBoard();
    }

    private void initBoards() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int column = 0; column < numberOfCol; column++) {
                hiddenBoard[row][column] = new Cell();
                displayedBoard[row][column] = new Cell();
            }
        }
    }

    private void plantBombs() {
        Random rand = new Random();
        int bombsToPlace = numberOfBombs;
        do {
            int r = rand.nextInt(numberOfRows);
            int c = rand.nextInt(numberOfCol);
            if (hiddenBoard[r][c].getValue() != 9) {
                hiddenBoard[r][c].setValue(9);
                bombsToPlace--;
            }
        } while (bombsToPlace > 0);
    }

    private void fillBoard() {
        for (int row = 0; row < numberOfRows; row++)
            for (int column = 0; column < numberOfCol; column++)
                if (hiddenBoard[row][column].getValue() != 9)
                    hiddenBoard[row][column].setValue(getNumberOfSurroundingBombs(row, column));
    }

    private int getNumberOfSurroundingBombs(int row, int column) {
        int numberOfSurroundingBombs = 0;
        for (int r = Math.max(0, row - 1); r < Math.min(numberOfRows, row + 2); r++)
            for (int c = Math.max(0, column - 1); c < Math.min(numberOfCol, column + 2); c++)
                if (hiddenBoard[r][c].getValue() == 9)
                    numberOfSurroundingBombs++;
        return numberOfSurroundingBombs;
    }

    private void dig(int row, int column) {

        if (displayedBoard[row][column].isDug() || displayedBoard[row][column].isFlagged())
            return;
        else {
            displayedBoard[row][column] = hiddenBoard[row][column];
            displayedBoard[row][column].setDug();
            numberOfDugSpots++;
        }

        if (hiddenBoard[row][column].getValue() == 0)
            digSurroundings(row, column);

        if (displayedBoard[row][column].getValue() == 9)
            gameOver();
    }

    private void digSurroundings(int row, int column) {
        for (int r = Math.max(0, row - 1); r < Math.min(numberOfRows, row + 2); r++)
            for (int c = Math.max(0, column - 1); c < Math.min(numberOfCol, column + 2); c++) {
                if (r == row && c == column)// || displayedBoard[r][c].isDug()
                    continue;
                dig(r, c);
            }
    }

    private void gameOver() {
        gameOver = true;
    }

    private boolean isWinner() {
        return numberOfDugSpots == numberOfCol * numberOfRows - numberOfBombs;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCol; col++) {

                Cell currentCell = displayedBoard[row][col];

                if (currentCell.isFlagged())
                    iM.drawFlag(g2, col, row);
                else if (currentCell.isDug()) {
                    g2.setColor(Color.darkGray);
                    g2.fillRect(cellSize * col, cellSize * row, cellSize, cellSize);
                }

                if (currentCell.getValue() == 9)
                    iM.drawBomb(g2, col, row);
                else if (currentCell.getValue() != 0)
                    iM.drawNumber(g2, col, row, getNumberOfSurroundingBombs(row, col));

                g2.setColor(Color.black);
                g2.drawRect(cellSize * col, cellSize * row, cellSize, cellSize);
            }
        }
        if (gameOver)
            gameOver(g2);
        else if (isWinner())
            win(g2);

        g2.dispose();
        g.dispose();
    }

    private void gameOver(Graphics2D g2) {
        Font font = new Font("Times New Roman", Font.BOLD, 36);
        FontMetrics metrics = g2.getFontMetrics(font);
        String text = "GAME OVER";

        int x = (screenWidth - metrics.stringWidth(text)) / 2;
        int y = screenHeight / 4;

        g2.setColor(Color.red);
        g2.setFont(font);
        g2.drawString(text, x, y);
    }

    private void win(Graphics2D g2) {
        Font font = new Font("Times New Roman", Font.BOLD, 36);
        FontMetrics metrics = g2.getFontMetrics(font);
        String text = "YOU WON ! :)";

        int x = (screenWidth - metrics.stringWidth(text)) / 2;
        int y = screenHeight / 4;

        g2.setColor(Color.green);
        g2.setFont(font);
        g2.drawString(text, x, y);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        int x = getCellX(e);
        int y = getCellY(e);

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (gameOver || isWinner())
                Minesweeper.gameOver();
            dig(y, x);
        }
        if (e.getButton() == MouseEvent.BUTTON2)
            System.out.println("middle");
        if (e.getButton() == MouseEvent.BUTTON3 && !displayedBoard[y][x].isDug()) {
            displayedBoard[y][x].setFlag();
        }

        repaint();
    }

    private int getCellX(MouseEvent e) {
        int x = e.getX() / cellSize;

        if (x >= numberOfCol)
            x = numberOfCol - 1;
        if (x < 0)
            x = 0;

        return x;
    }

    private int getCellY(MouseEvent e) {
        int y = e.getY() / cellSize;

        if (y >= numberOfRows)
            y = numberOfRows - 1;
        if (y < 0)
            y = 0;

        return y;
    }

    public int getCellSize() {
        return cellSize;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public String toString() {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfCol; col++) {
                System.out.print(hiddenBoard[row][col].getValue() + " ");
            }
            System.out.println();
        }
        return null;
    }
}
