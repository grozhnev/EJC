package task03v3;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 *
 */
public class Logic {

    /*
    VIEW      '.'     '*'     '-'     '?'      'x'
    status   EMPTY    SHOT     BOUND  DAMAGED   KILLED
    */
    static ArrayList<Field> ship;
    Random random = new Random();

    /* Building ships */
    public void buildNewShip(int amountOfDecks) {
        ship = new ArrayList<>();
        /*construct the ship*/
        do {
            if (findField().getStatus() == Board.status.EMPTY.name()) {
                ship.add(this.findField());

                if (ship.size() < amountOfDecks) {
                    int indexOfRandomCoordX = Board.defineIndexOfCoordinateX(ship.get(0).getCoordX());
                    int indexOfRandomCOordY = Board.defineIndexOfCoordinateY(ship.get(0).getCoordY());
                    if (indexOfRandomCoordX>0 && indexOfRandomCOordY>0 && amountOfDecks>1){
                        searchForEmptySpace(indexOfRandomCoordX, indexOfRandomCOordY, amountOfDecks - 1);
                    } else {
                        continue;
                    }
                }

                if (ship.size() != amountOfDecks) {
                    ship.clear();
                }
            } else {
                ship.clear();
            }
        } while (ship.size() < amountOfDecks);

        /* ship is build */
        int ID = new Random().nextInt();
        for (Field boat : ship) {
            boat.setShipID(ID);
            boat.setImage(Board.VIEW[4]); /* ? not sure it right place for this VIEW*/
        }
        /* surround ship with boundary fields*/

        /**
         * <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * SOME MISTAKE IN createBoundaryFields(ship);
         * temporary disabled  /
         */
        /*createBoundaryFields(ship);*/

        /* Temporary action -
        * we use shipID for tracking alive deck of the ship
        * but we can track the ArrayList.size() for making fields boundary fields appear */

        ship.clear();
    }

    private Field findField() {
        int randomX;
        int randomY;
        boolean found = false;
        do {
            randomX = random.nextInt(Board.SIDELENGTH);
            randomY = random.nextInt(Board.SIDELENGTH);

            if (Objects.equals(Board.gameBoard[randomX][randomY].getStatus(), Board.status.EMPTY.name())) {
                Board.gameBoard[randomX][randomY].setImage(Board.VIEW[4]);
                found = true;
            }
        } while (!found);
        return Board.gameBoard[randomX][randomY];
    }


    private void searchForEmptySpace(int startCoordX, int startCoordY, int numberOfDecksRequired) {
        int spacePending = numberOfDecksRequired;

        flagShipIsReady:
        { /* range 0-9 */
            flagLeftX:
            for (int l = startCoordX - 1; l >= 0; l--) {
                if (Objects.equals(Board.gameBoard[l][startCoordY].getStatus(), Board.status.EMPTY.name())) {
                    ship.add(Board.gameBoard[l][startCoordY]);
                    spacePending--;
                    if (spacePending == 0) {
                        break flagShipIsReady;
                    }
                } else {
                    break flagLeftX;
                }
            }
            flagRightX:
            for (int r = startCoordX + 1; r <= 9; r++) {
                if (Objects.equals(Board.gameBoard[r][startCoordY].getStatus(), Board.status.EMPTY.name())) {
                    ship.add(Board.gameBoard[r][startCoordY]);
                    spacePending--;
                    if (spacePending == 0) {
                        break flagShipIsReady;
                    }
                } else {
                    break flagRightX;
                }
            }
            flagUpY:
            for (int u = startCoordY - 1; u >= 0; u--) {
                if (Objects.equals(Board.gameBoard[startCoordX][u].getStatus(), Board.status.EMPTY.name())) {
                    ship.add(Board.gameBoard[startCoordX][u]);
                    spacePending--;
                    if (spacePending == 0) {
                        break flagShipIsReady;
                    }
                } else {
                    break flagUpY;
                }
            }
            flagDownY:
            for (int d = startCoordY + 1; d <= 9; d++) {
                if (Objects.equals(Board.gameBoard[startCoordX][d].getStatus(), Board.status.EMPTY.name())) {
                    ship.add(Board.gameBoard[startCoordX][d]);
                    spacePending--;
                    if (spacePending == 0) {
                        break flagShipIsReady;
                    }
                } else {
                    break flagDownY;
                }
            }
        }
    }

    /* make surrounding fields */
    private void createBoundaryFields(ArrayList<Field> newShip) {
        for (Field deckField : newShip) {

            for (int j = deckField.getCoordY()-1; j <= deckField.getCoordY()+1; j++) {
                for (int i = Board.defineIndexOfCoordinateX(deckField.getCoordX())-1; i <= Board.defineIndexOfCoordinateX(deckField.getCoordX())+1 ; i++) {
                    if ((i>=0 || i<=9) && (j>=0 || j<=9) ){
                        if (Objects.equals(Board.gameBoard[i][j].getStatus(), Board.status.EMPTY.name())) {
                            Board.gameBoard[i][j].setStatus(Board.status.BOUND.name());
                            Board.gameBoard[i][j].setImage(Board.VIEW[2]);
                            Board.gameBoard[i][j].setShipID(deckField.getShipID());
                        }
                    }
                }
            }

            /*
            int deckCoordX = Board.defineIndexOfCoordinateX(deckField.getCoordX());
            int deckCoordY = Board.defineIndexOfCoordinateY(deckField.getCoordY());


            for (int j = deckCoordY-1; j <= deckCoordY+1; j++) {
                for (int i = deckCoordX-1; i <= deckCoordX+1 ; i++) {
                    if ((i>0 || i<10) && (j>0 || j<10) ){
                        if (Objects.equals(Board.gameBoard[i][j].getStatus(), Board.status.EMPTY.name())) {
                            Board.gameBoard[i][j].setStatus(Board.status.BOUND.name());
                            Board.gameBoard[i][j].setImage(Board.VIEW[2]);
                            Board.gameBoard[i][j].setShipID(deckField.getShipID());
                        }
                    } else {
                        continue;
                    }
                }
            }
            */


        }
    }
}