package task03v2;

import java.util.Arrays;

/**
 * Field - это игровое поле, на котором может расположиться одна из палуб корабля в игре Морской бой.
 * Поле имеет координаты ( D3, J10, F4 ), имеет отображаемое состояние '.' пустое, '*' мимо, 'o' убитый корабль.
 * Поле можно добавить в корабль, учесть в качестве введенного ранее поля (два раза бить в одно поле нельзя).
 */
public class Field implements ICoordinates {
    private Character coordX;
    private Integer coordY;
    private String status;
    private int shipID;

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

    public int getShipID() {
        return shipID;
    }

    public void setShipID(int shipID) {
        this.shipID = shipID;
    }
}
