package minesweeperImages;

import game.Board;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ImageManager {

    Board b;
    int cellSize;
    int imageSize;
    int offSetX;
    int offSetY;
    Image[] images;

    public ImageManager(Board b) {
        this.b = b;
        cellSize = b.getCellSize();
        imageSize = (int) (0.75 * b.getCellSize());
        offSetX = (int) (0.125 * b.getCellSize());
        offSetY = (int) (0.125 * b.getCellSize());
        images = new Image[10];
        getImage();
    }

    public void getImage() {
        try {
            images[0] = new Image();
            images[0].image = ImageIO.read(getClass().getResourceAsStream("flag.png"));

            images[1] = new Image();
            images[1].image = ImageIO.read(getClass().getResourceAsStream("1.png"));

            images[2] = new Image();
            images[2].image = ImageIO.read(getClass().getResourceAsStream("2.png"));

            images[3] = new Image();
            images[3].image = ImageIO.read(getClass().getResourceAsStream("3.png"));

            images[4] = new Image();
            images[4].image = ImageIO.read(getClass().getResourceAsStream("4.png"));

            images[5] = new Image();
            images[5].image = ImageIO.read(getClass().getResourceAsStream("5.png"));

            images[6] = new Image();
            images[6].image = ImageIO.read(getClass().getResourceAsStream("6.png"));

            images[7] = new Image();
            images[7].image = ImageIO.read(getClass().getResourceAsStream("7.png"));

            images[8] = new Image();
            images[8].image = ImageIO.read(getClass().getResourceAsStream("8.png"));

            images[9] = new Image();
            images[9].image = ImageIO.read(getClass().getResourceAsStream("mine.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawFlag(Graphics2D g2, int x, int y) {
        g2.drawImage(images[0].image, x * cellSize + offSetX, y * cellSize + offSetY, imageSize, imageSize, null);
    }

    public void drawBomb(Graphics2D g2, int x, int y) {
        g2.drawImage(images[9].image, x * cellSize + offSetX, y * cellSize + offSetY, imageSize, imageSize, null);
    }

    public void drawNumber(Graphics2D g2, int x, int y, int number) {
        g2.drawImage(images[number].image, x * cellSize + offSetX, y * cellSize + offSetY, imageSize, imageSize, null);
    }
}
