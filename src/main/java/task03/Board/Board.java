package task03.Board;

import task03.GameLogic.Rules;

import java.util.ArrayList;
//import task03.Ships.*;

/**
 * Board consists of Fields.
 */
public class Board {
    //constructor
    public Board(){
        generateBoard10x10();
    }
    // {!} STATIC
    public static Field[][] gameBoard = new Field[10/*Rules.FIELD_SIDE*/][10/*Rules.FIELD_SIDE*/];

    public static void generateBoard10x10(){
        for (int i = 0; i < Rules.FIELD_SIDE ; i++)
            for (int j = 0; j < Rules.FIELD_SIDE; j++)
                gameBoard[i][j] = new Field(Coordinates.X[j], Coordinates.Y[i]);
    }

    /**
     * я хватаю ошибку при переводе Y из значения в индекс массива.
     * <<<<
     */
    public Field getBoardField(char cch, int nuum) {
        return  gameBoard[Coordinates.X[cch]][Coordinates.Y[nuum]];
    }
    public String showFieldStatus(char chh, int numm){
        return gameBoard[chh][numm].getStatus();
    }


    /* "CLEAN", "PADDED", "DAMAGED", "MARKED", "KILLED" */
    public void changeFieldStatus(char chr, int nmr, int type){
        gameBoard[Coordinates.X[chr]][Coordinates.Y[nmr]].setStatus(Coordinates.TYPE[type]);
    }


}


