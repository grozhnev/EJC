package task03.Board;

import task03.Ships.Ship;

/**
 * represent Field as HashMap ?
 */
public class Field implements Coordinates {
    private Coordinates coord;
    private char coordX;
    private int coordY;
    private String status;
    private Ship sh;
    private String shipState;

    public Field(){
        this.status  = TYPE[0];
        this.shipState = "";
    }
    public Field(char ch, int num) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[0];
        this.shipState = "";
    }
    public Field(char ch, int num, int state) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[state];
        this.shipState = "";
    }
    public Field(char ch, int num, int state, int shipState) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[state];
        this.shipState = Ship.state[shipState];
    }
    public String getStatus() {
        return this.status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public char getX() {
        return coordX;
    }
    public int getY() {
        return coordY;
    }

    public static boolean compareFields(Field a, Field b){
        return a.getX() == b.getX() && b.getY() == b.getY();
    }
    public String getShipState() {
        return shipState;
    }
    public void setShipState(int occupied) {
        this.shipState = Ship.state[occupied];
    }
}
