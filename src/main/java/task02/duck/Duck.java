package task02.duck;

import task02.fly.FlyBehaviour;

public abstract class Duck {

    FlyBehaviour fly_b;
    private int flightSpeed;
    private int distance;

    public Duck() {
    }

    public int getFlightSpeed() {
        return flightSpeed;
    }

    public void setFlightSpeed(int flightSpeed) {
        this.flightSpeed = flightSpeed;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance += distance;
    }

    public void setFlyBehaviour(FlyBehaviour fb) {
        fly_b = fb;
    }

    public String showFlyBehaviour() {
        return fly_b.toString();
    }

    public void performFly() {
        fly_b.fly();
    }
}
