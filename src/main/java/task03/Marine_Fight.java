package task03;

import task03.Board.Board;
import task03.Board.Coordinates;
import task03.Board.Field;
import task03.GameLogic.RandomService;
import task03.GameLogic.Rules;
import task03.Player.Player;
import task03.Ships.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class launch the "Marine Fight" game
 */
public class Marine_Fight extends Field {
    private Player player;
    private Board board;
    private Field field;
    private ArrayList<ArrayList<Field>> fleet;

    private void play() {
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
        player = new Player();
        board = new Board(); //field 10x10 is generated
        fleet = new ArrayList<>();
        instantiateShipsOnBoard();

        Rules.header();
        Rules.whatToDo();

        do {
            System.out.print("Currently " + fleet.size() + " ships on the board. You got "
                                + (Rules.MAX_ATTEMPTS - Player.HIT) + " attempts to win the game.\n");

                //display();
                player.setPlX((char) buff.read());
                player.setPlY(Integer.parseInt(buff.readLine()));
                System.out.print("You've entered " + player.getPlX() + player.getPlY() + ". ");

            field = new Field(player.getPlX(), player.getPlY());

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
                            field.setStatus(Coordinates.TYPE[2]); // field = DAMAGED
                            armada.removeIf(field1 -> field1.equals(field));
                        } else {
                            field.setStatus(Coordinates.TYPE[4]); // KILLED
                            armada.removeIf(field1 -> field1.equals(field));
                            RandomService.killTheShipAndMarFieldsAround(armada);
                        }
                    }
                }
            } else {
                field.setStatus(Coordinates.TYPE[1]); /*  We did not hit the target, mark field with '*'  */
            }
        } else {
            System.out.println("You're trying to hit the field \'" + field.getX() + field.getY() + "\' that is not empty. Please, retry another one.");
            Player.HIT--;
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

    /**
     * сделать Draw()
     */
    public static void main(String[] args) {
        Marine_Fight battle = new Marine_Fight();
        battle.play();
    }
}
