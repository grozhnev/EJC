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
public class Marine_Fight  extends  Field{

    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board();
        ArrayList<ArrayList<Field>> poolOfShips = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i < 4) {
                poolOfShips.add(new oneDeckShip(i));
            } else if ( 4 <= i && i < 7) {
                poolOfShips.add(new twoDeckShip(i));
            } else if ( 7<= i && i < 9) {
                poolOfShips.add(new threeDeckShip(i));
            } else {
                poolOfShips.add(new fourDeckShip(i));
            }
            Rules.header();
            Rules.whatToDo();
            do {
                System.out.println("Currently on the field there are :" + poolOfShips.size() + ", you got" +
                        (Rules.MAX_ATTEMPTS-Player.HIT) + " to blow them up.\n");
                try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))){
                    player.setPlX((char) buff.read());
                    player.setPlY(Integer.parseInt(buff.readLine()));
                    System.out.println("Координата Х = " + player.getPlX());
                    System.out.println("Координата Y = " + player.getPlY());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Field field = new Field(player.getPlX(),player.getPlY());
                if((Objects.equals(field.getStatus(), Coordinates.TYPE[0]))) {
                    if (Objects.equals(field.getShipState(), Ship.state[0]))  {
                        for (ArrayList<Field> po: poolOfShips) {
                            if(po.contains(field)){
                                if (po.size() > 1) {
                                    field.setStatus(Coordinates.TYPE[2]);
                                    po.removeIf(field1 -> field1.equals(field));
                                } else {
                                    field.setStatus(Coordinates.TYPE[4]);
                                    po.removeIf(field1 -> field1.equals(field));
                                    RandomService.markEmptyFieldsAndKillTheShip(po);
                                }
                            }
                        }
                    } else {
                        field.setStatus(Coordinates.TYPE[1]);
                    }
                } else {
                    System.out.println("You're trying to hit the field \'"+ field.getX()+field.getY() +"\' that is not empty. Please, retry another one.");
                    Player.HIT--;
                }
                Player.HIT++ ;
            } while (Player.HIT < Rules.MAX_ATTEMPTS);
        }
    }
}
