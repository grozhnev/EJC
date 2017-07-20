package task03.ships;

import task03.board.Field;
import task03.gameLogic.RandomService;

import java.util.ArrayList;
import java.util.Collection;

public class OneDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 1;
    private Collection<Field> ship;
    private RandomService randomly = new RandomService();
    private int shipID;

    public OneDeckShip() {
        create();
    }

    public void create() {
        ship = new ArrayList<>();
        ship.addAll(randomly.buildNewShipInEmptyFields(numberOfDecks));
        setShipID(RandomService.shipID++);
        System.out.println("1-deck ship created");
    }

    public void setShipID(int id) {
        this.shipID = id;
    }
}
