package task03.board;

import task03.gameLogic.Rules;

import java.util.Arrays;


/**
 * board consists of Fields.
 */
public class Board {

    //boats collect in here
    public static Field[][] board = new Field[10][10];

    //constructor
    public Board() {
        generateBoard10x10();
    }

    private void generateBoard10x10() {
        for (int m = 0; m < Rules.FIELD_SIDE; m++) {
            for (int n = 0; n < Rules.FIELD_SIDE; n++) {
                board[n][m] = new Field(Coordinates.X[n], Coordinates.Y[m]);
            }
        }
    }

    protected String showFieldStatus(char characterX, int numberY) {
        return board[Arrays.asList(Coordinates.X).indexOf(characterX)][numberY].getStatus();
    }
}


