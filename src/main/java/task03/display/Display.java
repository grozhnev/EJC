package task03.display;


import task03.board.Board;
import task03.board.Field;
import task03.gameLogic.Rules;

public class Display {
    Rules rules = new Rules();

    public static void drawTheBoard(){
        int newLine=0;
        System.out.print("\t   A B C D E F G H I J\n");
        for (int i = 0; i < Rules.FIELD_SIDE; i++) {
            if(i<Rules.FIELD_SIDE-1) {
                System.out.print("\t" + (i + 1 ) + " ");
            } else if (i == Rules.FIELD_SIDE-1){
                System.out.print("   " + (i + 1) + " ");
            }
            for (int j = 0; j < Rules.FIELD_SIDE; j++) {
                System.out.print(" " + defineFieldImageByStatus(Board.board[j][i]));
                newLine++;
                if (newLine % 10 == 0) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println("\n\n");
    }

    public static String defineFieldImageByStatus(Field field){
        String fieldImage = "";
        switch (field.getStatus()){
            case "CLEAN":
                fieldImage = ".";
                break;
            case "PADDED":
                fieldImage = "*";
                break;
            case "DAMAGED":
                fieldImage = "?";
                break;
            case "MARKED":
                fieldImage = "-";
                break;
            case "KILLED":
                fieldImage = "x";
                break;
        }
        return fieldImage;
    }
}
