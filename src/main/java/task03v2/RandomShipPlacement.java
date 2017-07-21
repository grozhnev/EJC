package task03v2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

/**
 * Random field selection. And placement new ship on it.
 */

public class RandomShipPlacement {

    static int newShipID ;
    Field randomField;
    ArrayList<Field> boat;
    private Random fortune = new Random();

    private Character generateX() {
        return ICoordinates.X[fortune.nextInt(9) + 1];
    }

    private Integer generateY() {
        return ICoordinates.Y[fortune.nextInt(9) + 1];
    }

    public void buildNewShip(int numberOfDecks) {
        /* запоминаем поля, которые мы посетили, чтобы не было бесконечного поиска */
        ArrayList<Field> visitedFields = new ArrayList<>(100);
        boolean shipIsAdded = false;

        /* process of building a ship */
        while (!shipIsAdded || visitedFields.size() < 100) {
            randomField = Board.getGameboardField(generateX(), generateY());
            if (visitedFields.contains(randomField)) {
                break;
            } else {
                visitedFields.add(randomField);
                System.out.println("Случайно выбранное поле для построения корабля: ["
                        + randomField.getCoordinateX() + "][" + randomField.getCoordinateY() + "], status = "
                        + randomField.getFieldStatus());
                if ((Objects.equals(randomField.getFieldStatus(), ICoordinates.TYPE[0])) && hasEmptySpace(numberOfDecks)) {
                    System.out.println("Корабль с " + boat.size() + " палубами построен и добавлен во флот.");
                    shipIsAdded = true;
                }
            }
        }
        visitedFields.clear();
    }

    /**
     * Method hasEmptySpace() is searching for empty space for placing new ship in the board.
     * If the required space is detected, this method returns TRUE, creates new boat and adds it to the fleet
     * (containre of all ships, participating the game).
     * Otherwise, this method returns FALSE, that means that there is no space for placing the ship.
     */
    private boolean hasEmptySpace(int rangeOfEmptyFields) {
        boat = new ArrayList<>();
        ArrayList<Field> visitedFieldsContainer = new ArrayList<>(100);
        boolean spaceIsFound = false;

        int rangeToTheLeftOfX = Arrays.asList(ICoordinates.X).indexOf(randomField.getCoordinateX()) - 1;
        int rangeToTheRightOfX = 9 - rangeToTheLeftOfX;
        int rangeAboveY = Arrays.asList(ICoordinates.Y).indexOf(randomField.getCoordinateY()) - 1;
        int rangeUnderY = 9 - rangeAboveY;

        /* process of building a ship */
        while (!spaceIsFound || (boat.size() < rangeOfEmptyFields) || (visitedFieldsContainer.size() < 100)) {
            if (visitedFieldsContainer.contains(randomField)) {
                break;
            } else {
                visitedFieldsContainer.add(randomField);
                for (int i = 0; i < rangeOfEmptyFields; i++) {
                    for (int j = 0; j < rangeOfEmptyFields; j++) {

                        if (boat.size() == rangeOfEmptyFields) {
                            System.out.println("Корабль нужного размера готов к сборке.");
                            spaceIsFound = true;
                            break;
                        } else {
                            if (Objects.equals(randomField.getFieldStatus(), ICoordinates.TYPE[0])) {
                                boat.add(randomField);
                                if (rangeToTheLeftOfX > 0) {
                                    for (int k = 1; k <= rangeToTheLeftOfX; k++) {
                                        if (Objects.equals(
                                                Board.getGameboardField(
                                                        ICoordinates.X[randomField.getOrderOfCoorinateX() - k],
                                                        randomField.getCoordinateY()).getFieldStatus(),
                                                ICoordinates.TYPE[0])) {
                                            boat.add(Board.getGameboardField(
                                                    ICoordinates.X[randomField.getOrderOfCoorinateX() - k],
                                                    randomField.getCoordinateY())
                                            );
                                        }
                                    }
                                } else if (rangeToTheRightOfX > 0) {
                                    for (int f = 1; f <= rangeToTheRightOfX; f++) {
                                        if (Objects.equals(
                                                Board.getGameboardField(
                                                        ICoordinates.X[randomField.getOrderOfCoorinateX() + f],
                                                        randomField.getCoordinateY()).getFieldStatus(),
                                                ICoordinates.TYPE[0])) {
                                            boat.add(
                                                    Board.getGameboardField(
                                                            ICoordinates.X[randomField.getOrderOfCoorinateX() + f],
                                                            randomField.getCoordinateY())
                                            );
                                        }
                                    }
                                } else if (rangeAboveY > 0) {
                                    for (int h = 1; h <= rangeAboveY; h++) {
                                        if (Objects.equals(
                                                Board.getGameboardField(randomField.getCoordinateX(),
                                                        Arrays.asList(ICoordinates.Y).indexOf(randomField.getCoordinateY()) - h).getFieldStatus(),
                                                ICoordinates.TYPE[0])) {
                                            boat.add(Board.getGameboardField(randomField.getCoordinateX(),
                                                    Arrays.asList(ICoordinates.Y).indexOf(randomField.getCoordinateY()) - h)
                                            );
                                        }
                                    }
                                } else if (rangeUnderY > 0) {
                                    for (int t = 1; t <= rangeUnderY; t++) {
                                        if (Objects.equals(Board.getGameboardField(randomField.getCoordinateX(),
                                                Arrays.asList(ICoordinates.Y).indexOf(randomField.getCoordinateY()) + t).getFieldStatus(),
                                                ICoordinates.TYPE[0])) {
                                            boat.add(Board.getGameboardField(randomField.getCoordinateX(),
                                                    Arrays.asList(ICoordinates.Y).indexOf(randomField.getCoordinateY()) + t));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        visitedFieldsContainer.clear();

        if (boat.size() < rangeOfEmptyFields) {
            System.err.println("Корабль не построен. Нужно искать другое свободное поле.");
            return false;
        } else {
            for (Field deck : boat) {
                deck.setFieldStatus(2);
                deck.setShipID(newShipID);
            }

            System.out.println("Корабль с " + boat.size() + " палубами, добавляется к боевому флоту.");
            return true;
        }
    }
}
