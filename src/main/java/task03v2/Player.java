package task03v2;

import java.util.HashSet;

/**
 * Represents the actions of user in task03v2 game.
 */
public class Player {
    static String inputString;
    public static HashSet<Field> userShots = new HashSet<>();

    static String choice = "";
    /*static char choice = ' ';*/
    static boolean victory = false;

    void buildPlayground(){
        Board board = new Board();
        board.createBoard();
    }
}
