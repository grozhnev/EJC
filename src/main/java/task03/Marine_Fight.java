package task03;

import task03.Board.Board;
import task03.Board.Coordinates;
import task03.Board.Field;
import task03.Display.Display;
import task03.GameLogic.RandomService;
import task03.GameLogic.Rules;
import task03.Player.Player;
import task03.Ships.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class launch the "Marine Fight" game
 */
public class Marine_Fight extends Field {
    private Player player;
    private Board board;
    private Field field;

    public static Field[][] gameBoard;
    private ArrayList<ArrayList<Field>> fleet;

    private void play() {
        Rules.header();

        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
        player = new Player();
        board = new Board(); //field 10x10 is generated
        fleet = new ArrayList<>();

        instantiateShipsOnBoard();
        Rules.whatToDo();
        do {
            Display.drawTheBoard();
            System.out.print("Currently " + fleet.size() + " ships on the board. You got "
                                            + (Rules.MAX_ATTEMPTS - Player.HIT) + " attempts to win the game.\n");
            /**
             * <<<<<<<<<<<<<
             * фильтр ввода
             * где корабли-то?
             * почему ход не запоминается?
             * и что с попытками?
             */
            player.setPlayerChoiceX((char) buff.read());
            player.setPlayerChoiceY(Integer.parseInt(buff.readLine()));
            System.out.print("You've entered " + player.getPlayerChoiceX() + (player.getPlayerChoiceY()+1)+ ". ");
            field = Board.board[Arrays.asList(Coordinates.X).indexOf(player.getPlayerChoiceX())][player.getPlayerChoiceY()];
            specifyTheField();
            Player.HIT++;
            if(playerWins()){
                break;
            }
        } while (Player.HIT <= Rules.MAX_ATTEMPTS);
        computerWins();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void instantiateShipsOnBoard() {
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                fleet.add(new OneDeckShip());
            } else if (4 <= i && i < 7) {
                fleet.add(new TwoDeckShip());
            } else if (7 <= i && i < 9) {
                fleet.add(new ThreeDeckShip());
            } else {
                fleet.add(new FourDeckShip());
            }
        }
    }

    private void specifyTheField(){
        /* field status  0: CLEAN='0', 1: PADDED='*',  2:DAMAGED='?', 3: MARKED='-', 4: KILLED='x' */
        /* ship status   0="Hidden",   1="Damaged" ,   2="Killed" */
        if ((Objects.equals(field.getStatus(), Coordinates.TYPE[0]))) {
            if (Objects.equals(field.getShipState(), Ship.state[0])) {
                for (ArrayList<Field> armada : fleet) {
                    if (armada.contains(field)) {
                        if (armada.size() > 1) {
                            System.out.println("Ship DAMAGED!\n");
                            field.setStatus(Coordinates.TYPE[2]); // field = DAMAGED
                            field.setShipState(1); //ship damaged
                            armada.removeIf(field1 -> field1.equals(field));
                        } else {
                            System.out.println("THE SHIP IS KILLED !\n");
                            field.setStatus(Coordinates.TYPE[4]); // KILLED
                            field.setShipState(2); // killed
                            armada.removeIf(field1 -> field1.equals(field));
                            RandomService.killTheShipAndMarFieldsAround(armada);
                        }
                    }
                }
            } else {
                field.setStatus(Coordinates.TYPE[1]); /*  We did not hit the target, mark field with '*'  */
                System.out.println(" It was empty field =0)\n");
            }
        } else {
            System.out.println("You're trying to hit the field \'" + field.getX() + field.getY() + "\' that is not empty. Please, retry another one.\n");
            Player.HIT += 2;
        }
    }

    private boolean playerWins() {
        if (fleet.size() == 0) {
            System.out.println("YOU WIN!");
            return true;
        } else {
            return false;
        }
    }

    private void computerWins(){
        if (fleet.size() > 0) {
            System.out.println("YOU LOSE!");
        } else {
            System.err.println("All ships are killed, while all attempts are done. Please check.");
        }
    }

    public static void main(String[] args) {
        Marine_Fight battle = new Marine_Fight();
        battle.play();
    }
}