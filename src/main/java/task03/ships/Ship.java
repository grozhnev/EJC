package task03.ships;

import task03.board.Field;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface defines 'ship' entity, that has N-decks, coordinates and current game status.
 */
public interface Ship {
    int numberOfDecks = 0;
    int shipID = 0;
    String[] state = {"Hidden", "Damaged" , "Killed"};
    Collection<Field> ship = new ArrayList<>();

    void create();
    int getShipID();
    void setShipID(int i);
}
