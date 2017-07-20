package task02.casino;

/**
 * This class contains all the print text.
 */
class Announcement {
    public static void greet() {
        System.out.println("Make a bet on a duck from 1 to 5");
    }

    public static void results() {
        System.out.println("\n===================================================");
        System.out.println("Here are the results of the race: \n");
    }

    public static void proceed() {
        System.out.println("Wanna play one more time? (for YES press 9, for NO press 0) ");
    }

    public static void noMoney() {
        System.out.println("You've got NO MONEY!");
    }

    public static void winTheRace() {
        System.out.println("\nCongratulations, YOU WIN THE RACE!");
    }

    public static void loseTheRace() {
        System.out.println("\nYOU LOSE.");
    }

    public static void isTheWinner() {
        System.out.print(" Is the WINNER of the race!");
    }

    public static void cannotFly() {
        System.out.print(" She can't fly and didn't participate the race.");
    }

    public static void wasYourChoice() {
        System.out.print(" Was YOUR CHOICE!");
    }

    public static void rules() {
        System.out.println("Rules of Duck Casino:\n" +
                "*   1) You got 500 RUB cash\n" +
                "*   2) Choose anyone of 5 ducks (numbers 1 to 5), by typing it on your keyboard. Press 'Enter'.\n" +
                "*   3) Your choice means that you take a 100 RUB bet on the duck you've chosen.\n" +
                "*   4) Than the race begins:\n" +
                "*       4.1 Our lottery machine will randomly give to ducks the ability to fly.\n" +
                "*           4.1.1 There is at least one duck, who can fly.\n" +
                "*           4.1.2 If your duck can't fly - YOU LOSE!\n" +
                "*       4.2 Those ducks who can fly will be given 10 seconds to run the race.\n" +
                "*       4.3 Our lottery machine randomly sets the flight speed for each duck from 10 to 100 m/s\n" +
                "*           4.3.1 The speed randomly changes each second by a single bit operations '>>' and '<<'.\n" +
                "*           4.3.2 Total distance the duck has flown each second is calculated.\n" +
                "*       4.4 The duck with the longest flight distance wins the race.\n" +
                "*   5) If you bet the duck with the longest distance, you wins the prise in sum of 200 RUB.\n" +
                "*   6) If you lose all your money - GAME OVER.\n\n");
    }
}