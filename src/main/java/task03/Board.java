package task03;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Game board specs and methods.
 */
public class Board {

    public static final int BOARD_SIDE = 10;
    public static final char[] CHARS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    /* ships container */
    private static final Ship[] ships = new Ship[10];

    static {
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                ships[i] = new Ship(1);
            } else if (i >= 4 && i < 7) {
                ships[i] = new Ship(2);
            } else if (i >= 7 && i < 9) {
                ships[i] = new Ship(3);
            } else if (i == 9) {
                ships[i] = new Ship(4);
            }
        }
    }

    /* randomly arranged ships */
    private char[][] board;
    /* user shoots storage */
    private char[][] userShots;
    /* not damaged ships */
    private int shipsNumber;

    public Board() {
        this.board = new char[BOARD_SIDE][BOARD_SIDE];
        this.userShots = new char[BOARD_SIDE][BOARD_SIDE];
        this.shipsNumber = ships.length;

        for (int i = 0; i < BOARD_SIDE; i++) {
            for (int j = 0; j < BOARD_SIDE; j++) {
                board[i][j] = '.';
                userShots[i][j] = '.';
            }
        }
        buildShips();
    }

    int getShipsNumber() {
        return shipsNumber;
    }

    private void buildShips() {
        Random random = new Random();

        for (Ship currentShip : ships) {
            List<Point> shipPoints = new ArrayList<>();
            boolean isCreated = false;
            while (!isCreated) {
                int row = random.nextInt(BOARD_SIDE - 1);
                int column = random.nextInt(BOARD_SIDE - 1);
                Point field = new Point(row, column);
                boolean vertical = random.nextBoolean();

                if (vertical) {
                    if (hasEmptySpace(currentShip.getSize(), field, false)) {

                        currentShip.setFirstDeck(field);
                        currentShip.setLastDeck(new Point(row + currentShip.getSize() - 1, column));

                        for (int j = 0; j < currentShip.getSize(); j++) {
                            shipPoints.add(new Point(row + j, column));
                        }

                        currentShip.setShipDecks(shipPoints);
                        isCreated = true;
                        drawShip(currentShip);
                        drawBounds(currentShip, board);
                    } else isCreated = false;
                } else {
                    if (hasEmptySpace(currentShip.getSize(), field, true)) {
                        currentShip.setFirstDeck(field);
                        currentShip.setLastDeck(new Point(row, column + currentShip.getSize() - 1));

                        for (int j = 0; j < currentShip.getSize(); j++) {
                            shipPoints.add(new Point(row, column + j));
                        }
                        currentShip.setShipDecks(shipPoints);
                        isCreated = true;
                        drawShip(currentShip);
                        drawBounds(currentShip, board);
                    } else isCreated = false;
                }
            }
        }
    }

    private boolean hasEmptySpace(int shipSize, Point startPoint, boolean horizontal) {
        int col = startPoint.y;
        int row = startPoint.x;
        if (horizontal) {
            if (col + shipSize >= BOARD_SIDE) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (board[row][col + i] != '.') {
                    return false;
                }
            }
            return true;
        } else {
            if (row + shipSize >= BOARD_SIDE) {
                return false;
            }
            for (int i = 0; i < shipSize; i++) {
                if (board[row + i][col] != '.') {
                    return false;
                }
            }
            return true;
        }
    }

    void findDeckAlive(Point targetPoint) {
        int row = targetPoint.x;
        int col = targetPoint.y;
        if (row == -1 && col == -1) {
            System.out.println("BattleShip over.");
        } else if (board[row][col] != 'X') {
            System.out.println("You've already shot this field. Try another one.");
            if (userShots[row][col] == '.') {
                userShots[row][col] = '*';
            }
        } else {
            for (Ship ship : ships) {
                if (ship.containsPoint(targetPoint)) {
                    ship.killTheDeck();
                    if (ship.isKilled()) {
                        userShots[row][col] = 'X';
                        drawBounds(ship, userShots);
                        shipsNumber--;
                        System.out.println("\nYou've just killed " + ship.getSize() + "-decks ship!\n");
                    } else {
                        userShots[row][col] = 'X';
                        System.out.println("Good shot. You've damaged one deck of the ship.");
                    }
                    break;
                }
            }
        }
    }

    private void drawShip(Ship ship) {
        if (ship.getFirstDeck().x == ship.getLastDeck().x) {
            int row_const = ship.getFirstDeck().x;
            int col_start = ship.getFirstDeck().y;
            int col_end = ship.getLastDeck().y;
            for (int i = col_start; i <= col_end; i++) {
                board[row_const][i] = 'X';
            }
        } else {
            int col_const = ship.getFirstDeck().y;
            int row_start = ship.getFirstDeck().x;
            int row_end = ship.getLastDeck().x;
            for (int i = row_start; i <= row_end; i++) {
                board[i][col_const] = 'X';
            }
        }
    }

    private void drawBounds(Ship ship, char[][] board) {
        int row = ship.getFirstDeck().x;
        int col = ship.getFirstDeck().y;
        int shipSize = ship.getSize();
        if (ship.getFirstDeck().x == ship.getLastDeck().x) {
            for (int i = 0; i < shipSize; i++) {
                if (row != 0) {
                    board[row - 1][col + i] = '0';
                    if (col != 0 && i == 0) {
                        board[row - 1][col - 1] = '0';
                        board[row][col - 1] = '0';
                    }
                    if (col + shipSize < BOARD_SIDE && i == shipSize - 1) {
                        board[row - 1][col + shipSize] = '0';
                        board[row][col + shipSize] = '0';
                    }
                }
                if (row != BOARD_SIDE - 1) {
                    board[row + 1][col + i] = '0';
                    if (col != 0 && i == 0) {
                        board[row + 1][col - 1] = '0';
                        board[row][col - 1] = '0';
                    }
                    if (col + shipSize < BOARD_SIDE && i == shipSize - 1) {
                        board[row + 1][col + shipSize] = '0';
                        board[row][col + shipSize] = '0';
                    }
                }
            }
        } else {
            for (int i = 0; i < shipSize; i++) {
                if (col != 0) {
                    board[row + i][col - 1] = '0';
                    if (row != 0 && i == 0) {
                        board[row - 1][col - 1] = '0';
                        board[row - 1][col] = '0';
                    }
                    if (row + shipSize < BOARD_SIDE && i == shipSize - 1) {
                        board[row + shipSize][col - 1] = '0';
                        board[row + shipSize][col] = '0';
                    }
                }
                if (col != BOARD_SIDE - 1) {
                    board[row + i][col + 1] = '0';
                    if (row != 0 && i == 0) {
                        board[row - 1][col + 1] = '0';
                        board[row - 1][col] = '0';
                    }
                    if (row + shipSize < BOARD_SIDE && i == shipSize - 1) {
                        board[row + shipSize][col + 1] = '0';
                        board[row + shipSize][col] = '0';
                    }
                }
            }
        }
    }

    void drawBattleField() {
        System.out.print("\t");

        for (int i = 0; i < BOARD_SIDE; i++) {
            System.out.print(CHARS[i] + "\t");
        }

        System.out.println();

        for (int i = 0; i < BOARD_SIDE; i++) {
            System.out.print((i + 1) + "\t");
            for (int j = 0; j < BOARD_SIDE; j++) {
                System.out.print(userShots[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}