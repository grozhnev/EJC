package task03.Ships;

import task03.Board.Field;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface defines 'ship' entity, that has N-decks, coordinates and current game status.
 */
public interface Ship {
    int numberOfDecks=1;
    String[] state = {"Hidden", "Damaged" , "Killed"};
    int shipID = 0;
    Collection<Field> ship = new ArrayList<>();

    void create();
    int getShipID();
    void setShipID(int i);

}
