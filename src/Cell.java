public class Cell {

    private int value;
    private boolean isFlagged = false;

    public Cell(){
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
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
}
