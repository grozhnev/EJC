package task03.ships;

import task03.board.Field;
import task03.gameLogic.RandomService;

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
        System.out.println("4-deck ship created");
    }

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int id) {
        this.shipID = id;
    }
}
