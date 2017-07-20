package task02.duck;

import task02.casino.Game;
import task02.casino.Totalizator;
import task02.fly.FlyWithWings;

/**
 * This duck can participate the race
 */
public class DuckRacer extends Duck {

    private int number;

    public DuckRacer(int number) {
        this.number = number;
    }

    public DuckRacer() {
        fly_b = new FlyWithWings();
    }

    public int calculateSpeed() {
        if (Totalizator.countRandomBool()) {
            return ((Totalizator.countRandomNum(Game.getMaxSpeed() - Game.getMinSpeed()) + 10) << 1);
        } else {
            return ((Totalizator.countRandomNum(Game.getMaxSpeed() - Game.getMinSpeed()) + 10) >> 1);
        }
    }

    public void calculateDistance() {
        setDistance(calculateSpeed());
    }
}
