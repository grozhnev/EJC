package task03.GameLogic;

import task03.Board.Board;
import task03.Board.Coordinates;
import task03.Board.Field;
import task03.Ships.Ship;

import java.util.*;

/**
 * Generate random logic.
 */
public class RandomService extends Board {
    public static int shipID = 0;
    Ship shipInterface;
    Coordinates coordinatesInterface;
    private Field randomField;
    private Collection<Field> newShip = new ArrayList<>();
    private Random fortune = new Random();

    public static void killTheShipAndMarFieldsAround(ArrayList<Field> dyingShip) {
        for (Field deck : dyingShip) {
            int coordinateXOfKilledDeck = Arrays.asList(Coordinates.X).indexOf(deck.getX()); /* Coordinates.X[deck.getX()]; */
            int coordinateYOfKilledDeck = Arrays.asList(Coordinates.Y).indexOf(deck.getY()); /* Coordinates.Y[deck.getY()]; */

            for (int j = coordinateYOfKilledDeck - 1; j <= coordinateYOfKilledDeck + 1; j++) {
                for (int i = coordinateXOfKilledDeck - 1; i <= coordinateXOfKilledDeck + 1; i++) {
                    /* если статус поля DAMAGED '?' - делаем его KILLED 'x' ; все остальные  MARKED='-' */
                    if (Objects.equals(gameBoard[i][j].getStatus(), Coordinates.TYPE[2])) {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[4]);
                    } else {
                        gameBoard[i][j].setStatus(Coordinates.TYPE[3]);
                    }
                }
            }
        }
    }

    private char generateX() {
        return Coordinates.X[fortune.nextInt(9)];
    }

    private int generateY() {
        return Coordinates.Y[fortune.nextInt(9)];
    }

    /* placing ships */
    private Field generateStartFieldOfAShip() {
        return new Field(generateX(), generateY());
    }

    public ArrayList<Field> buildNewShipInEmptyFields(int numberOfDecs) {
        do {
            randomField = generateStartFieldOfAShip();
            /* мы получили поле, проверим его статус и свободное место для корабля */
            if ((Objects.equals(randomField.getStatus(), Coordinates.TYPE[0])) && hasEmptySpace(numberOfDecs)) {
                return (ArrayList<Field>) newShip;
            }
            newShip.clear();
        } while ((Objects.equals(randomField.getStatus(), Coordinates.TYPE[0])));
        return (ArrayList<Field>) newShip;
    }

    private boolean hasEmptySpace(int requiredRangeOfEmptyFields) {
        ArrayList<Field> boatBuilder = new ArrayList<>();

        int rangeToTheLeftOfX = Arrays.asList(Coordinates.X).indexOf(randomField.getX()); //Arrays.asList(Coordinates.X).indexOf(((char) randomField.getX())) + 1;
        int rangeToTheRightOfX = 9 - rangeToTheLeftOfX;
        int rangeAboveY = Arrays.asList(Coordinates.Y).indexOf(randomField.getY()); // randomField.getY();
        int rangeUnderY = 9 - rangeAboveY;

        for (int i = 0; i < requiredRangeOfEmptyFields; i++) {
            for (int j = 0; j < requiredRangeOfEmptyFields; j++) {
                if (boatBuilder.size() == requiredRangeOfEmptyFields) {
                    return true;
                } else {
                    if (Objects.equals(showFieldStatus(randomField.getX(),randomField.getY()), Coordinates.TYPE[0])) {
                        boatBuilder.add(randomField);
                        if (rangeToTheLeftOfX > 0) {
                            for (int k = rangeToTheLeftOfX - 1; k >= 0; k--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[k], randomField.getY()), Coordinates.TYPE[0])) {
                                    // здесь ловлю ArrayIndexOutOfBoundsException: -1 , когда пытаюсь ArrayList<Field>.add(Field[][]);
                                    //boatBuilder.add( gameBoard[Arrays.asList(Coordinates.X).indexOf(k)][Coordinates.Y[randomField.getY()]] );/* boatBuilder.add(gameBoard[Coordinates.X[k]][i]); */
                                    boatBuilder.add(gameBoard[k][randomField.getY()]);
                                }
                            }
                        } else if (rangeToTheRightOfX > 0) {
                            for (int f = rangeToTheLeftOfX + 1; f <= rangeToTheLeftOfX + rangeToTheRightOfX; f++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[f], randomField.getY()), Coordinates.TYPE[0])) {
                                    //boatBuilder.add( gameBoard[Arrays.asList(Coordinates.X).indexOf(f)][Coordinates.Y[randomField.getY()]] );/* boatBuilder.add(gameBoard[Coordinates.X[f]][i]); */
                                    boatBuilder.add(gameBoard[f][randomField.getY()]);
                                }
                            }
                        } else if (rangeAboveY > 0) {
                            for (int h = rangeAboveY - 1; h >= 0; h--) {
                                if (Objects.equals(showFieldStatus(randomField.getX(), h), Coordinates.TYPE[0])) {
                                    //boatBuilder.add( gameBoard[Arrays.asList(Coordinates.X).indexOf(randomField.getX())][Coordinates.Y[h]] );/* boatBuilder.add(gameBoard[Coordinates.X[j]][h]); */
                                    boatBuilder.add(gameBoard[randomField.getX()][h]);
                                }
                            }
                        } else if (rangeUnderY > 0) {
                            for (int t = rangeAboveY + 1; t <= rangeAboveY + rangeUnderY; t++) {
                                if (Objects.equals(showFieldStatus(randomField.getX(), t), Coordinates.TYPE[0])) {
                                    //boatBuilder.add( gameBoard[Arrays.asList(Coordinates.X).indexOf(randomField.getX())][Coordinates.Y[t]] );/* boatBuilder.add(gameBoard[Coordinates.X[j]][t]); */
                                    boatBuilder.add(gameBoard[randomField.getX()][t]);
                                }
                            }
                        }
                    }
                }
            }
        }

        if (boatBuilder.size() < requiredRangeOfEmptyFields) {
            boatBuilder.clear();
            return false;
        } else {
            /*в каждый элемент нового корабля поставить маячок hidden (КОРАБЛЬ спрятан) */
            for (Field field : boatBuilder) {
                field.setShipState(0); /* "Hidden", "Damaged" , "Killed"; */
            }
            newShip.addAll(boatBuilder);
            boatBuilder.clear();
            return true;
        }
    }
}
