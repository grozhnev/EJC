package task03.player;

/**
 * player enter coordinate A1, B7, J3
 */
public class Player {
    public static int HIT = 0;
    private char plX;
    private int plY;

    public char getPlX() {
        return plX;
    }

    public void setPlX(char plX) {
        this.plX = plX;
    }

    public int getPlY() {
        return plY;
    }

    public void setPlY(int plY) {
        this.plY = plY - 1;
    }
}