package task02.Duck;

import task02.Fly.HasNoWings;

/**
 * this Duck has no wings and cannot fly
 */

public class MallardDuck extends Duck{

    public MallardDuck(){
        fly_b = new HasNoWings();
    }
}
