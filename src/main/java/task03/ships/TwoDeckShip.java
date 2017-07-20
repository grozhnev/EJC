package task03.ships;

import task03.board.Field;
import task03.gameLogic.RandomService;

import java.util.ArrayList;
import java.util.Collection;

public class TwoDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 2;
    private Collection<Field> ship;
    private RandomService randomly = new RandomService();
    private int shipID;

    public TwoDeckShip() {
        create();
    }

    public void create() {
        ship = new ArrayList<>();
        ship.addAll(randomly.buildNewShipInEmptyFields(numberOfDecks));
        setShipID(RandomService.shipID++);
        System.out.println("2-deck ship created");
    }

    public void setShipID(int id) {
        this.shipID = id;
    }
}
