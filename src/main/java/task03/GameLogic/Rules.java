package task03.GameLogic;

/**
 * Game rules
 */
public class Rules {
    public static int MAX_ATTEMPTS = 30;
    public static int FIELD_SIDE = 10;

    // victory marker
    private static boolean victory;
    public static void setVictory(boolean victory) {
        Rules.victory = victory;
    }
    public static boolean isVictory() {
        return victory;
    }
    public static void whatToDo() {
        System.out.println("\tShoot the enemies boats on the table-board 10x10 with: \n" +
                "\trows (1, 2, 3, 4, 5, 6, 7, 8, 9, 10) and columns(A B C D E F G H I J ) \n\n" +
                "\tEnter your target coordinates in format [1 character] + [1 number], like B7, H3 or A2.\n\tYou've got 30 shots to win the game.\n" +
                "\tGOOD LUCK!\n\n");
    }

    public static void header(){
        System.out.println("****************************************");
        System.out.println("*********** THE MARINE FIGHT ***********");
        System.out.println("****************************************");
        System.out.println();
    }


}
