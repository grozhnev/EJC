package task02;

import task02.casino.Game;

/**
 * This class launch DuckCasino Race.
 */
public class Main {

    public static void main(String[] args) {
        /*duck racer = new DuckRacer();
        duck rubber = new MallardDuck();

        racer.performFly();
        rubber.performFly();

        racer.setFlyBehaviour(new HasNoWings()); // changing behaviour in RunTime
        racer.performFly();*/

        Game game = new Game();
        game.play();
    }
}
