package task03.Board;

import task03.Ships.Ship;

/**
 * represent Field entity.
 */
public class Field implements Coordinates {

    private char coordinateX;
    private int coordinateY;
    private String fieldStatus;
    private String shipStatus;

    private Coordinates coordinatesInterface;
    private Ship shipInterface;

    public Field() {
        this.fieldStatus = TYPE[0]; // clean
        this.shipStatus = ""; // empty
    }

    public Field(char characterX, int numberY) {
        this.coordinateX = characterX;
        this.coordinateY = numberY;
        this.fieldStatus = TYPE[0]; // clean
        this.shipStatus = ""; // empty
    }

    public static boolean compareFields(Field a, Field b) {
        return a.getX() == b.getX() && b.getY() == b.getY();
    }

    public String getStatus() {
        return this.fieldStatus;
    }

    public void setStatus(String status) {
        this.fieldStatus = status;
    }

    public char getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }

    //  статус корабля
    public String getShipState() {
        return shipStatus;
    }

    public void setShipState(int occupied) {
        this.shipStatus = Ship.state[occupied];
    }
}