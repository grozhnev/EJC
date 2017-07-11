package task03.Board;

import task03.GameLogic.Rules;

import java.util.Arrays;


/**
 * Board consists of Fields.
 */
public class Board {
    // STATIC
    public static Field[][] gameBoard = new Field[10][10];

    //constructor
    public Board() {
        generateBoard10x10();
    }

    public static void generateBoard10x10() {
        for (int i = 0; i < Rules.FIELD_SIDE; i++)
            for (int j = 0; j < Rules.FIELD_SIDE; j++)
                gameBoard[i][j] = new Field(Coordinates.X[j], Coordinates.Y[i]);
    }

    /*      я хватаю ошибку при переводе Y из значения в индекс массива.     */
    public Field getBoardField(char characterX, int numberY) {
        return gameBoard[Arrays.asList(Coordinates.X).indexOf(characterX)][Coordinates.Y[numberY]];
    }

    public String showFieldStatus(char characterX, int numberY) {
        return gameBoard[Arrays.asList(Coordinates.X).indexOf(characterX)][ numberY].getStatus();
    }

}


