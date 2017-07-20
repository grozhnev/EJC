package task03v2;

import java.util.ArrayList;

/**
 * There will be placed Fields of the ship.
 */
public class Ship extends RandomShipPlacement {

    public static RandomShipPlacement randomConstructor = new RandomShipPlacement();
    public ArrayList<Field> shipBoats;
    private int shipID = 0;

    public Ship(int decksNumber) {
        create(decksNumber);
    }

    private void create(int decks) {
        shipBoats = new ArrayList<>();
        randomConstructor.buildNewShip(decks);
        setShipID(decks);
        System.out.println("Ship with " + decks + " decks created");
    }

    public int getShipID() {
        return this.shipID;
    }

    public void setShipID(int id) {
        this.shipID = id;
    }

    public void addDecksToTheShip(ArrayList randomlyGeneratedBoat) {
        shipBoats.addAll(randomlyGeneratedBoat);
        return;
    }
}
