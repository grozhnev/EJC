package task03v2;

import java.util.ArrayList;

/**
 * There will be placed Fields of the ship.
 */
public class Ship extends RandomShipPlacement {

    static RandomShipPlacement randomConstructor = new RandomShipPlacement();
    static ArrayList<Field> shipBoats = new ArrayList<>();

    public Ship(int decksNumber) {
        create(decksNumber);
    }

    private void create(int decks) {
        randomConstructor.buildNewShip(decks);
        System.out.println("Ship with " + decks + " decks created");
    }

    void addDecksToTheShip(ArrayList randomlyGeneratedBoat) {
        shipBoats.addAll(randomlyGeneratedBoat);
    }
}
