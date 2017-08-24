package task03;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Basic game specs + work with user shots.
 */
public class BattleShip {

    /* Shots available */
    private int shots = 30;
    private Board board;
    private BufferedReader userInput;

    /* Launch game, create game board */
    public BattleShip() {
        userInput = new BufferedReader(new InputStreamReader(System.in));
        this.board = new Board();
    }

    void launch() {
        System.out.print("Welcome to the Marine Fight!\n");
        System.out.print("You got " + shots + " shots to destroy 10 hidden ships on board\n");
        board.drawBattleField();

        while (shots > 0 && board.getShipsNumber() > 0) {
            makeShot();
        }
        if (board.getShipsNumber() == 0) {
            System.out.println("Congratulations! You WIN!");
        } else if (shots == 0) {
            System.out.println("Sorry, but today you lose.");
        }
        stopInput(userInput);
    }

    private Point shotCoordinates() {
        Point coordinate = new Point(-1, -1);
        do {
            System.out.print("Enter coordinate [letter]+[number]: ");
            try {
                String input = userInput.readLine().toLowerCase();
                if (input.matches("^([a-j])([1-9]|10)$")) {
                    int coordinateX = Integer.parseInt(input.substring(1, input.length())) - 1;
                    int coordinateY = input.codePointAt(0) - 97;
                    coordinate.setLocation(coordinateX, coordinateY);
                }
            } catch (IOException e) {
                e.printStackTrace();
                stopInput(userInput);
            }
        } while (coordinate.x == -1 || coordinate.y == -1);
        return coordinate;
    }

    private void makeShot() {
        board.findDeckAlive(shotCoordinates());
        System.out.print("\nThere are still " + board.getShipsNumber() + " ships on board\n");
        board.drawBattleField();
        shots--;
        System.out.println("You've got " + shots + " shots");
    }

    private void stopInput(BufferedReader reader) {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
