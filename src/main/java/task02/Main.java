package task02;

import task02.Casino.Game;

/**
 * This class launch DuckCasino Race.
 */
public class Main {

    public static void main(String[] args) {
        /*Duck racer = new DuckRacer();
        Duck rubber = new MallardDuck();

        racer.performFly();
        rubber.performFly();

        racer.setFlyBehaviour(new HasNoWings()); // changing behaviour in RunTime
        racer.performFly();*/

        Game game = new Game();
        game.play();
    }
}
