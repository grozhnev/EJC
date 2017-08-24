package task03;

import java.awt.*;
import java.util.List;

/**
 * Fields and methods of ship entity.
 */
public class Ship {

    private int size;
    private int restOfDecks;
    private Point firstDeck;
    private Point lastDeck;
    private List<Point> shipPoints;

    public Ship(int size) {
        this.size = size;
        this.restOfDecks = size;
    }

    int getSize() {
        return size;
    }

    Point getFirstDeck() {
        return firstDeck;
    }

    void setFirstDeck(Point first) {
        this.firstDeck = first;
    }

    Point getLastDeck() {
        return lastDeck;
    }

    void setLastDeck(Point last) {
        this.lastDeck = last;
    }

    void setShipDecks(List<Point> shipDecks) {
        this.shipPoints = shipDecks;
    }

    boolean containsPoint(Point target) {
        return shipPoints.contains(target);
    }

    void killTheDeck() {
        restOfDecks--;
    }

    boolean isKilled() {
        return restOfDecks == 0;
    }
}