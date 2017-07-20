package task03v2;

/**
 * Stores all text occured in game.
 */
public class Say {
    public static void chooseField() {
        System.out.print("Enter the coordinates : ");
    }

    public static void wrongCoordinates() {
        System.out.println("Incorrect coordinates format. Try again.");
    }

    public static void tryAnotherOne() {
        System.out.println("You've already shot this field, try another one.");
    }

    public static void thisIsMarkedField() {
        System.out.print("This is marked field, try another one.");
    }

    public static void forQuit() {
        System.out.print("Press any key to continue. For quit press 'Q'.");
    }

    public static void whatToDo() {
        System.out.println("\n\tShoot all the enemies boats on the table below. \n" +
                "\tType the coordinates in format [letter]+[number], like B7, H3 or A2.\n " +
                "\tGOOD LUCK!\n");
    }

    public static void header() {
        System.out.println("********************************************");
        System.out.println("************* THE MARINE FIGHT *************");
        System.out.println("********************************************");
    }
}
