package task03.player;

/**
 * player enter coordinate A1, B7, J3
 */
public class Player {
    private char playerChoiceX;
    private int playerChoiceY;
    public static int HIT=0;

    public char getPlayerChoiceX() {
        return playerChoiceX;
    }
    public void setPlayerChoiceX(char coordX) {
        this.playerChoiceX = coordX;
    }

    public int getPlayerChoiceY() {
        return playerChoiceY;
    }
    public void setPlayerChoiceY(int coordY) {
        this.playerChoiceY = coordY-1;
    }
}