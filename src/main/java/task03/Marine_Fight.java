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
//import java.util.Collection;
//import java.util.LinkedList;

/**
 * This class launch the "Marine Fight" game
 */
public class Marine_Fight  extends  Field{

    public static void main(String[] args) {
        Player player = new Player();
        Board board = new Board(); //field 10x10 is generated

        //creating & adding ships
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
        }
        //// create logic of drawing ships

        Rules.header();
        Rules.whatToDo();

        do {
            System.out.println("Currently on the field there are :" + poolOfShips.size() + ", you got" +
                    (Rules.MAX_ATTEMPTS-Player.HIT) + " to blow them up.\n");
            // 1- player enter coordinates
            try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))){
                player.setPlX((char) buff.read());
                player.setPlY(Integer.parseInt(buff.readLine()));
                //проверочные поля
                System.out.println("Координата Х = " + player.getPlX());
                System.out.println("Координата Y = " + player.getPlY());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 2- we instantiate Field object with coordinates, received from player and add it on the board
            Field field = new Field(player.getPlX(),player.getPlY());
            /*
             * сравнить статусы Field, и проверить, есть ли на нем корабль из poolOfShips
             *
             * (class Board)    public void changeFieldStatus(char chr, int nmr, int type);
             */
            // 0: CLEAN='0', 1: PADDED='*',  2:DAMAGED='?', 3: MARKED='-', 4: KILLED='x'
            // shipState :  Ship.state[];  0="Hidden", 1="Damaged" , 2="Killed"};

            if((Objects.equals(field.getStatus(), Coordinates.TYPE[0]))) {
                if (Objects.equals(field.getShipState(), Ship.state[0]))  {
                //мы попали в корабль.
                    //int ID = field.getFieldID();
                    // получаем корабль, в который мы попали // ID корабля (ArrayList<Field>) класс Field getShipID
                    /*
                     *  найти Field с тем же shipID или найти способ получить ArrayList<Field>  serial_number
                     */
                    for (ArrayList<Field> po: poolOfShips) {
                        if(po.contains(field)){
                            if (po.size() > 1) {
                                field.setStatus(Coordinates.TYPE[2]); // field = DAMAGED
                                po.removeIf(field1 -> field1.equals(field)); /*po.remove(field);*/
                            } else {
                                field.setStatus(Coordinates.TYPE[4]); // KILLED
                                po.removeIf(field1 -> field1.equals(field)); /*po.remove(field);*/
                                RandomService.markEmptyFieldsAndKillTheShip(po);// все окружающие поля делаем Marked, удаляем
                            }
                        }
                    }
                } else {
                    field.setStatus(Coordinates.TYPE[1]); //мы промазали, ставим '*'
                }
            } else {
                //координата не пустая ...
                System.out.println("You're trying to hit the field \'"+ field.getX()+field.getY() +"\' that is not empty. Please, retry another one.");
                //ход игрока не засчитываем
                Player.HIT--;
            }




            /*
             * сравниваем статус выбора игрока со статусом поля
             * - - если поле пустое - просто меняется его статус, итерация попытки и выстрел заново
             *
             * - если поле занято кораблем - меняем статус поля, меняем статус корабля, удаляем поле из массива полей
             * корабля)
             * ***(добавить метод определения целостности корабля )
             */
            //


            // 3- we put the field on the board.




            // attempt is over
            Player.HIT++ ;
        } while (Player.HIT < Rules.MAX_ATTEMPTS);
    }
}
