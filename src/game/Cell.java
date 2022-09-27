package game;

public class Cell {

    private int value;
    private boolean isFlagged = false;
    boolean isDug = false;

    public Cell() {}

    public void setFlag() {
        isFlagged = !isFlagged;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setDug() {
        isDug = true;
    }

    public boolean isDug(){ return isDug;}
}
