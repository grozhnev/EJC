package task03.Board;

import task03.Display.Display;
import task03.GameLogic.Rules;

import java.util.Arrays;

/**
 * Board consists of Fields.
 */
public class Board  {
    public static Field[][] board = new Field[10][10];  // <<<< собирается сюда
    Display draw = new Display();

    //constructor
    public Board() {
        generateBoard10x10();
    }

    public  void generateBoard10x10() {
        for (int m = 0; m < Rules.FIELD_SIDE ; m++) {
            for (int n = 0; n < Rules.FIELD_SIDE; n++) {
                board[n][m] = new Field(Coordinates.X[n], Coordinates.Y[m]);
            }
        }
    }

    public String showFieldStatus(char characterX, int numberY) {
        return board[Arrays.asList(Coordinates.X).indexOf(characterX)][ numberY].getStatus();
    }
}


