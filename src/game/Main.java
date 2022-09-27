package game;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        int size = 50;
        Board b = new Board(10, 10);

        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        f.setResizable(false);
        f.setTitle("Minesweeper");
        f.add(b);

        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        b.toString();

//        int[][] test = {{1, 1, 1, 0, 0},
//                           {2, 9, 2, 0, 0},
//                           {2, 9, 2, 0, 0},
//                           {1, 1, 1, 0, 0}};
//
//        System.out.println(test[1][2]);

    }
}