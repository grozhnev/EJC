package task03.GameLogic;

import task03.Board.Board;
import task03.Board.Coordinates;
import task03.Board.Field;
import task03.Ships.Ship;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Generate random logic.
 */

public class RandomService extends Board{
    Ship sh;
    Coordinates coo;
    private Random fortune = new Random();
    private Field point;
    private Collection<Field> ship = new ArrayList<>();

    private int generateY(){
        return Coordinates.Y[fortune.nextInt(9)];
    }
    private char generateX(){
        return Coordinates.X[fortune.nextInt(9)];
    }

    private Field generateStartField(){
        return new Field(generateX(), generateY());
    }
    public static int shipID = 0;
    public ArrayList<Field> getNewShipBuildInEmptyFreeSpace(int Ndecks){
        do {
            point = generateStartField();
            //мы получили поле, проверим его статус и свободное место для корабля
            if ( (Objects.equals(point.getStatus(), Coordinates.TYPE[0]))  && hasEmptySpace(Ndecks) ){
                return (ArrayList<Field>) ship;
            }
            ship.clear();
        } while( (Objects.equals(point.getStatus(), Coordinates.TYPE[0]))  );
        return (ArrayList<Field>) ship;
    }

    private boolean hasEmptySpace(int range){
        Collection<Field> boatBuilder = new ArrayList<>();
        int rangeXleft =Arrays.asList(Coordinates.X).indexOf(((char) point.getX())) + 1 ;
        int rangeXright = 9 - rangeXleft ;
        int rangeYup = point.getY();
        int rangeYdown = 9 - rangeYup;

        for (int i = 0; i < range ; i++) {
            for (int j = 0; j < range ; j++) {
                if (boatBuilder.size() == range){
                   return true;
                } else {
                    if(Objects.equals(showFieldStatus(Coordinates.X[j], i), Coordinates.TYPE[0])) {
                        if (rangeXleft > 0) {
                            for (int k = rangeXleft; k >= 0; k--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[k], i), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[k]][i]);
                                }
                            }
                        } else if (rangeXright  > 0) {
                            for (int f = rangeXleft; f <= rangeXright+rangeXleft; f++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[f], i), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[f]][i]);
                                }
                            }
                        } else if (rangeYup > 0) {
                            for (int h = rangeYup; h >= 0; h--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[j], h), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[j]][h]);
                                }
                            }
                        } else if ( rangeYdown > 0) {
                            for (int t = rangeYup; t <= rangeYup+rangeYdown; t++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[j], t), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[j]][t]);
                                }
                            }
                        }
                    }
                }
            }
        }
        if (boatBuilder.size() < range) {
            return false;
        } else {
            for (Field f:boatBuilder) {
                f.setShipState(0);
            }
            ship.addAll(boatBuilder);
            return true;
        }
    }

    public static void markEmptyFieldsAndKillTheShip(ArrayList<Field> dyingShip) {
        for (Field deck:dyingShip) {
            int xCo = Coordinates.X[deck.getX()];
            int yCo = Coordinates.Y[deck.getY()];

            for (int j = (yCo); j <= (yCo+1); j++) {
                for (int i = (xCo-1); i <= (xCo+1) ; i++){
                    if (Objects.equals(gameBoard[i][j].getStatus(), Coordinates.TYPE[2])) {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[4]);
                    } else {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[3]);
                    }
                }
            }
        }

    }
}
