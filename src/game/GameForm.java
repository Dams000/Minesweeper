package game;

import javax.swing.*;

public class GameForm extends JFrame {

    public GameForm(int numberOfCol, int numberOfRows, int numberOfBombs) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);

        Board board = new Board(numberOfCol, numberOfRows, numberOfBombs);

        JLabel bombsLeft = new JLabel();
        bombsLeft.setBounds(0, 0, board.getWidth(),150);
        bombsLeft.setText("test");

        getContentPane().add(bombsLeft);
        getContentPane().add(board);
        this.pack();
        this.setLocationRelativeTo(null);
    }

}
