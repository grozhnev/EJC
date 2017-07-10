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
    private Ship sh; //для доступа к типу state в interface Ship
    private String shipState;
    //private int fieldID;

    public Field(){
        this.status  = TYPE[0]; // clean by default
        this.shipState = ""; // empty by default
    }

    // Игрок вводит координаты (A1), создается поле
    public Field(char ch, int num) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[0]; // clean by default
        this.shipState = ""; // empty by default
    }

    public Field(char ch, int num, int state) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[state]; //0="CLEAN", 1="PADDED", 2="DAMAGED", 3="MARKED", 4="KILLED"
        this.shipState = ""; // empty by default
    }

    public Field(char ch, int num, int state, int shipState) {
        this.coordX = ch;
        this.coordY = num;
        this.status  = TYPE[state]; //0="CLEAN", 1="PADDED", 2="DAMAGED", 3="MARKED", 4="KILLED"
        this.shipState = Ship.state[shipState]; //{"Hidden", "Damaged" , "Killed"};
    }


    public String getStatus(){
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
    //  статус корабля
    public String getShipState() {
        return shipState;
    }
    public void setShipState(int occupied) {
        this.shipState = Ship.state[occupied];
    }

    /*public int getFieldID(){
        return fieldID;
    }
    public void setFieldID(int id){
        this.fieldID=id;
    }*/

}
