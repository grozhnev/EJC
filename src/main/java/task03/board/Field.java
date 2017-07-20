package task03.board;

import task03.ships.Ship;

/**
 * represent Field entity.
 */
public class Field implements Coordinates {

    private char coordinateX;
    private int coordinateY;
    private String fieldStatus;
    private String shipStatus;

    protected Field() {
        this.fieldStatus = TYPE[0]; // clean
        this.shipStatus = ""; // empty
    }

    public Field(char characterX, int numberY) {
        this.coordinateX = characterX;
        this.coordinateY = numberY;
        this.fieldStatus = TYPE[0]; // clean
        this.shipStatus = ""; // empty
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

    public String getShipState() {
        return shipStatus;
    }

    public void setShipState(int occupied) {
        this.shipStatus = Ship.state[occupied];
    }
}