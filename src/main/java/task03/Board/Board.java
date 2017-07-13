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

    public void printTheBoard(){
        int newLine=0;
        System.out.print("\t   A B C D E F G H I J\n");
        for (int i = 0; i < Rules.FIELD_SIDE; i++) {
            if(i<Rules.FIELD_SIDE-1) {
                System.out.print("\t" + (i + 1) + " ");
            } else if (i == Rules.FIELD_SIDE-1){
                System.out.print("   " + (i+1) + " ");
            }
            for (int j = 0; j < Rules.FIELD_SIDE; j++) {
                System.out.print(" " + Display.defineFieldImageByStatus(board[i][j]));  // ?? достаточно ли пробелов
                newLine++;
                if (newLine % 10 == 0) {
                    System.out.print("\n");
                }
            }
        }
    }

    /*      я хватаю ошибку при переводе Y из значения в индекс массива.     */
    public Field getBoardField(char characterX, int numberY) {
        return board[Arrays.asList(Coordinates.X).indexOf(characterX)][Coordinates.Y[numberY]];
    }

    public String showFieldStatus(char characterX, int numberY) {
        return board[Arrays.asList(Coordinates.X).indexOf(characterX)][ numberY].getStatus();
    }

}


