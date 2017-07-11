package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;

import java.util.ArrayList;
import java.util.Collection;

public class ThreeDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 3;
    private String state;
    private Collection<Field> ship;
    private RandomService randomly = new RandomService();
    private int shipID;

    public ThreeDeckShip() {
        create();
    }

    public void create() {
        ship = new ArrayList<>();
        ship.addAll(randomly.buildNewShipInEmptyFields(numberOfDecks));
        this.state = Ship.state[0];
        setShipID(RandomService.shipID++);
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int id) {
        this.shipID = id;
    }
}
