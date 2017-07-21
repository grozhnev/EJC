package task03v2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This is the playing board 10x10 fields.
 */
public class Board {

    /* board 10x10*/
    static HashMap<String, Field> gameBoard = new HashMap<String, Field>(20);
    static LinkedList<Ship> fleet = new LinkedList();

    /* получаем поле введя координаты 'F' и '10', возможно переиграю через ввод String */
    static public Field getGameboardField(Character letter, Integer cifer) {
        StringBuilder keyStringGenerator = new StringBuilder();
        return gameBoard.get(keyStringGenerator.append(letter).append(cifer).toString());
    }

    static void printTheBoard() {
        int newLine = 0;
        System.out.print("\t   A B C D E F G H I J\n");
        for (int i = 1; i <= 10; i++) {
            if (i <= 9) {
                System.out.print("\t" + i + " ");
            } else if (i == 10) {
                System.out.print("   " + i + " ");
            }
            for (int j = 1; j <= 10; j++) {
                String pic = getGameboardField(ICoordinates.X[j], i).getFieldStatus();
                System.out.print(" " + pic);
                newLine++;
                if (newLine % 10 == 0) {
                    System.out.print("\n");
                }
            }
        }
        System.out.println("");
    }

    static void instantiateShipsOnBoard() {

        for (int i = 1; i <= 4; i++) {
            if (i <= 4) {
                Ship ship = new Ship(i);
                ship.addDecksToTheShip(ship.shipBoats);
                fleet.add(ship);
            }
            if (i <= 3) {
                Ship ship = new Ship(i);
                ship.addDecksToTheShip(ship.shipBoats);
                fleet.add(ship);
            }
            if (i <= 2) {
                Ship ship = new Ship(i);
                ship.addDecksToTheShip(ship.shipBoats);
                fleet.add(ship);
            }
            if (i == 1) {
                Ship ship = new Ship(i);
                ship.addDecksToTheShip(ship.shipBoats);
                fleet.add(ship);
            }
        }
        System.out.println("Currently in 'fleet' there are " + fleet.size() + " ships.");
    }

    /* every field added to the board receive its private key string, like "F10" */
    void createBoard() {
        for (int m = 1; m < 11; m++) {
            for (int n = 1; n < 11; n++) {
                StringBuilder key = new StringBuilder();
                String keyString = key.append(ICoordinates.X[Arrays.asList(ICoordinates.X).indexOf(ICoordinates.X[m])]).append(n).toString();
                gameBoard.put(keyString, new Field(ICoordinates.X[m], n));
            }
        }
    }
}
