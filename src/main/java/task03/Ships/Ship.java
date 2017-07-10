package task03.Ships;

import task03.Board.Field;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This interface defines 'ship' entity, that has N-decks, coordinates and current game status.
 *
 */
public interface Ship {
    int numberOfDecks=1;
    String[] state = {"Hidden", "Damaged" , "Killed"};
    int shipID = 0;

    void create();
    // поменял логику архитектуры, этот метод исполняется внутри логики методов
    // getNewShipBuildInEmptyFreeSpace() и  hasEmptySpace() класса RandomService.
    //void addDeck();

    Collection<Field> ship = new ArrayList<>();

    //строить корабли в качестве arraylist'ов,
    // если в поле попали - remove(field) && status.TYPE[i++],
    // если ArrayList == empty, status.TYPE[i++] && create marked fields.

    // не использовал!
    //boolean alive = true;

    int getShipID();
    void setShipID(int i);

}
