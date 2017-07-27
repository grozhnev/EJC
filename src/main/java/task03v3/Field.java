package task03v3;


public class Field {
    /* coordinates */
    private char coordX;
    private int coordY;
    /* value */
    private char image;
    /* state */
    private String status;

    /* ship id */
    private int shipID;


    public Field(char charCoordinateX, int intCoordinateY){
        setCoordX(charCoordinateX);
        setCoordY(intCoordinateY);
        setImage(Board.VIEW[0]);
        setStatus(Board.status.EMPTY.name());
    }

    public char getCoordX() {
        return coordX;
    }
    public void setCoordX(char coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public char getImage() {
        return image;
    }
    public void setImage(char image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public int getShipID() {
        return shipID;
    }
    public void setShipID(int shipID) {
        this.shipID = shipID;
    }
}