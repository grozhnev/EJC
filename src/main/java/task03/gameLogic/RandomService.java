package task03.gameLogic;

import task03.board.Board;
import task03.board.Coordinates;
import task03.board.Field;

import java.util.*;

/**
 * Generate random logic.
 */
public class RandomService extends Board {
    public static int shipID = 0;
    private Field randomField;
    private Collection<Field> newShip = new ArrayList<>();
    private Random fortune = new Random();

    public static void killTheShipAndMarFieldsAround(ArrayList<Field> dyingShip) {
        for (Field deck : dyingShip) {
            int coordinateXOfKilledDeck = Arrays.asList(Coordinates.X).indexOf(deck.getX());
            int coordinateYOfKilledDeck = Arrays.asList(Coordinates.Y).indexOf(deck.getY());

            for (int j = coordinateYOfKilledDeck - 1; j <= coordinateYOfKilledDeck + 1; j++) {
                for (int i = coordinateXOfKilledDeck - 1; i <= coordinateXOfKilledDeck + 1; i++) {
                    if (Objects.equals(board[i][j].getStatus(), Coordinates.TYPE[2])) {
                        board[i][j].setStatus(Coordinates.TYPE[4]);
                    } else {
                        board[i][j].setStatus(Coordinates.TYPE[3]);
                    }
                }
            }
        }
    }

    private char generateX() {
        return Coordinates.X[fortune.nextInt(Rules.FIELD_SIDE - 1)];
    }

    private int generateY() {
        return Coordinates.Y[fortune.nextInt(Rules.FIELD_SIDE - 1)];
    }

    public ArrayList<Field> buildNewShipInEmptyFields(int numberOfDecs) {
        do {
            randomField = Board.board[Arrays.asList(Coordinates.X).indexOf(generateX())][generateY()];
            System.out.println("randomField [" + randomField.getX() + "][" + randomField.getY() + "], status = " + randomField.getStatus());
            if ((Objects.equals(randomField.getStatus(), Coordinates.TYPE[0])) && hasEmptySpace(numberOfDecs)) {
                return (ArrayList<Field>) newShip;
            }
            newShip.clear();
        } while ((Objects.equals(randomField.getStatus(), Coordinates.TYPE[0])));
        return (ArrayList<Field>) newShip;
    }

    private boolean hasEmptySpace(int requiredRangeOfEmptyFields) {
        ArrayList<Field> boatBuilder = new ArrayList<>();

        int rangeToTheLeftOfX = Arrays.asList(Coordinates.X).indexOf(randomField.getX());
        int rangeToTheRightOfX = 9 - rangeToTheLeftOfX;
        int rangeAboveY = Arrays.asList(Coordinates.Y).indexOf(randomField.getY());
        int rangeUnderY = 9 - rangeAboveY;

        for (int i = 0; i < requiredRangeOfEmptyFields; i++) {
            for (int j = 0; j < requiredRangeOfEmptyFields; j++) {
                if (boatBuilder.size() == requiredRangeOfEmptyFields) {
                    return true;
                } else {
                    if (Objects.equals(randomField.getStatus(), Coordinates.TYPE[0])) {
                        boatBuilder.add(randomField);
                        if (rangeToTheLeftOfX > 0) {
                            for (int k = rangeToTheLeftOfX - 1; k >= 0; k--) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[k], randomField.getY()), Coordinates.TYPE[0])) {
                                    boatBuilder.add(Board.board[k][randomField.getY()]);
                                }
                            }
                        } else if (rangeToTheRightOfX > 0) {
                            for (int f = rangeToTheLeftOfX + 1; f <= rangeToTheLeftOfX + rangeToTheRightOfX; f++) {
                                if (Objects.equals(showFieldStatus(Coordinates.X[f], randomField.getY()), Coordinates.TYPE[0])) {
                                    boatBuilder.add(Board.board[f][randomField.getY()]);
                                }
                            }
                        } else if (rangeAboveY > 0) {
                            for (int h = rangeAboveY - 1; h >= 0; h--) {
                                if (Objects.equals(showFieldStatus(randomField.getX(), h), Coordinates.TYPE[0])) {
                                    boatBuilder.add(Board.board[randomField.getX()][h]);
                                }
                            }
                        } else if (rangeUnderY > 0) {
                            for (int t = rangeAboveY + 1; t <= rangeAboveY + rangeUnderY; t++) {
                                if (Objects.equals(showFieldStatus(randomField.getX(), t), Coordinates.TYPE[0])) {
                                    boatBuilder.add(Board.board[randomField.getX()][t]);
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
            for (Field field : boatBuilder) {
                field.setShipState(0);
            }
            newShip.addAll(boatBuilder);
            boatBuilder.clear();
            return true;
        }
    }
}