package game;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(30, 30);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setResizable(false);
        f.setTitle("Minesweeper");
        f.add(b);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

    }
}