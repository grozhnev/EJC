package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;

import java.util.ArrayList;
import java.util.Collection;

public class FourDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 4;
    private String state;
    private RandomService randomly = new RandomService();
    private Collection<Field> ship;
    private int shipID;

    public FourDeckShip() {
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
