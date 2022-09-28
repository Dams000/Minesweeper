package game;

public class Minesweeper {

    private static GameForm gf;
    private static StartUpForm sf;
    private static CustomForm cf;

    public static void startEasy() {
        gf = new GameForm(10, 10, 10);
        gf.setVisible(true);
    }

    public static void startMedium() {
        gf = new GameForm(20, 20, 45);
        gf.setVisible(true);
    }
    public static void startHard() {
        gf = new GameForm(35, 35, 80);
        gf.setVisible(true);
    }

    public static void startCustom(int rows, int columns, int numberOfBombs) {
        gf = new GameForm(rows, columns, numberOfBombs);
        gf.setVisible(true);
    }

    public static void gameOver() {
        gf.setVisible(false);
        sf.setVisible(true);
    }

    public static void showStartUp() {
        sf.setVisible(true);
    }

    public static void showCustomForm() {
        cf.setVisible(true);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {

                sf = new StartUpForm();
                cf = new CustomForm();

                sf.setVisible(true);

            }
        });

    }

}
