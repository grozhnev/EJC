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


    //placing ships
    private Field generateStartField(){
        return new Field(generateX(), generateY());
    }
    public static int shipID = 0;
    public ArrayList<Field> getNewShipBuildInEmptyFreeSpace(int Ndecks){

        do {
            point = generateStartField();
            //мы получили поле, проверим его статус и свободное место для корабля
            if ( (Objects.equals(point.getStatus(), Coordinates.TYPE[0]))  && hasEmptySpace(Ndecks) ){
                /*
                 * присвоить  здесь shipID
                 *  <<< ?
                 */
                return (ArrayList<Field>) ship;
            }
            ship.clear();
        } while( (Objects.equals(point.getStatus(), Coordinates.TYPE[0]))  /* &&  есть свободное место */  );
        return (ArrayList<Field>) ship;
    }

    private boolean hasEmptySpace(int range){
        Collection<Field> boatBuilder = new ArrayList<>();
            // расстояние от краев
        /*
         * почему rangeXleft константная величина  = или -1  или  3  ?
         */
        int rangeXleft =Arrays.asList(Coordinates.X).indexOf(((char) point.getX())) + 1 ; //Arrays.asList(Coordinates.X).indexOf(point.getX());//Array.getInt(point, Arrays.asList(Coordinates.X).indexOf(point.getX()));
        // Array.getInt(point, Coordinates.X[point.getX()]);
        // Arrays.asList(Coordinates.X).indexOf(((char) point.getX())) + 1 ;
        int rangeXright = 9 - rangeXleft ;//Arrays.asList(Coordinates.X).indexOf(point.getX())+1;

        int rangeYup = point.getY();
        int rangeYdown = 9 - rangeYup;

        for (int i = 0; i < range ; i++) {
            for (int j = 0; j < range ; j++) {
                if (boatBuilder.size() == range){
                   return true;
                } else {

                    //проверяем начальную точку
                    if(Objects.equals(showFieldStatus(Coordinates.X[j], i), Coordinates.TYPE[0])) {
                        if (rangeXleft > 0) {
                            for (int k = rangeXleft; k >= 0; k--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[k], i), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[k]][i] /*new Field(Coordinates.X[k] ,i)*/ );
                                }
                            }
                        } else if (rangeXright  > 0) {
                            for (int f = rangeXleft; f <= rangeXright+rangeXleft; f++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[f], i), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[f]][i] /*new Field(Coordinates.X[f] ,i)*/ );
                                }
                            }
                        } else if (rangeYup > 0) {
                            for (int h = rangeYup; h >= 0; h--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[j], h), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[j]][h] /*new Field(Coordinates.X[j] ,h)*/ );
                                }
                            }
                        } else if ( rangeYdown > 0) {
                            for (int t = rangeYup; t <= rangeYup+rangeYdown; t++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[j], t), Coordinates.TYPE[0])) {
                                    boatBuilder.add( gameBoard[Coordinates.X[j]][t] /*new Field(Coordinates.X[j] ,t)*/ );
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
            //в каждый элемент нового корабля поставить маячок hidden (КОРАБЛЬ спрятан)
            for (Field f:boatBuilder) {
                f.setShipState(0);
                //f.setShipID(shipID++);
            }
            ship.addAll(boatBuilder);
            return true;
        }
    }

    /*
     * написать метод помечаем поля вокруг корабля, как "MARKED"   <<<<<<<<
     */
    public static void markEmptyFieldsAndKillTheShip(ArrayList<Field> dyingShip) {
        for (Field deck:dyingShip) {
            int xCo = Coordinates.X[deck.getX()];
            int yCo = Coordinates.Y[deck.getY()];

            for (int j = (yCo); j <= (yCo+1); j++) {
                for (int i = (xCo-1); i <= (xCo+1) ; i++){
                    // если DAMAGED '?' - то KILLED 'x' ; все остальные  MARKED='-'
                    if (Objects.equals(gameBoard[i][j].getStatus(), Coordinates.TYPE[2])) {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[4]);
                    } else {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[3]);
                    }
                }
            }
        }

    }

    /*public void setMarkedFieldsArroundKilledShip(ArrayList<Field> deadShip){
        // 0- что передает сущность корабля? где и чем вызывается этот метод ????
        // 1- получить все координаты полей в составе корабля
        // 2- пробежаться foreach() по кажому Field, если status == DAMAGED , меняем на KILLED, в остальных случаях setStatus(MARKED)
    }*/

}
