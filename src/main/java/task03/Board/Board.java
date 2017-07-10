package task03.Board;

import task03.GameLogic.Rules;

/**
 * Board consists of Fields.
 */
public class Board {
    public Board() {
        generateBoard10x10();
    }
    public static Field[][] gameBoard = new Field[10][10];

    public static void generateBoard10x10(){
        for (int i = 0; i < Rules.FIELD_SIDE ; i++)
            for (int j = 0; j < Rules.FIELD_SIDE; j++)
                gameBoard[i][j] = new Field(Coordinates.X[j], Coordinates.Y[i]);
    }
    /*
      я хватаю ошибку при переводе Y из значения в индекс массива.
     */
    public Field getBoardField(char cch, int nuum) {
        return  gameBoard[Coordinates.X[cch]][Coordinates.Y[nuum]];
    }
    public String showFieldStatus(char chh, int numm) {
        return gameBoard[chh][numm].getStatus();
    }
    public void changeFieldStatus(char chr, int nmr, int type){
        gameBoard[Coordinates.X[chr]][Coordinates.Y[nmr]].setStatus(Coordinates.TYPE[type]);
    }


}


