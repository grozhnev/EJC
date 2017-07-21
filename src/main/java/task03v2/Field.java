package task03v2;

import java.util.Arrays;

public class Field implements ICoordinates {
    private Character coordX;
    private Integer coordY;
    private String status;

    public Field(char character, int number) {
        this.coordX = character;
        this.coordY = number;
        this.status = TYPE[0];
    }

    public Character getCoordinateX() {
        return coordX;
    }

    public Integer getOrderOfCoorinateX() {
        return Arrays.asList(ICoordinates.X).indexOf(this.coordX);
    }

    public Integer getCoordinateY() {
        return coordY;
    }

    public String getFieldStatus() {
        return status;
    }

    public void setFieldStatus(int statusNumber) {
        this.status = ICoordinates.TYPE[statusNumber];
    }

}
