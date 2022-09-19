import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Board b = new Board(10, 8);

        while (true) {

            b.toString();

            Scanner s = new Scanner(System.in);

            System.out.println("row: ");
            int r = s.nextInt();
            System.out.println("col: ");
            int c = s.nextInt();

            b.dig(r, c);
        }
    }
}