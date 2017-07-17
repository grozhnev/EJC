package task02.duck;

import task02.fly.HasNoWings;

/**
 * this duck has no wings and cannot fly
 */

public class MallardDuck extends Duck{

    public MallardDuck(){
        fly_b = new HasNoWings();
    }
}
