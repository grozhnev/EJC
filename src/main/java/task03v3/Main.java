package task03v3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BattleShip Launcher.
 */
public class Main {

    public static void main(String[] args) {

        /* create board */
        Board board = new Board();
        Logic gameLogic = new Logic();
        board.generateBoard();

        /**
         * 1) Ships Array Index Out Of Boundary.
         * 2) They are placing randomly.
         */
        for (int i = 1; i <=4 ; i++) {
            if(i<4){
                gameLogic.buildNewShip(i);
            }
            if(i<3){
                gameLogic.buildNewShip(i);
            }
            if(i<2){
                gameLogic.buildNewShip(i);
            }
            gameLogic.buildNewShip(i);
        }


        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            int roll = 30;
            while (roll > 0) {
                board.displayBoard();

                /* user input */
                System.out.print("\nEnter coorinates [LETTER] + [NUMBER] : ");
                Character userX = (char) buff.read();
                userX = userX.toString().toUpperCase().charAt(0);
                Integer userY = Integer.parseInt(buff.readLine());

                /* find index of coordinate X for working with array */
                int indexOfCoordX = Board.defineIndexOfCoordinateX(userX);
                int indexOfCoordY = Board.defineIndexOfCoordinateY(userY);


                /**
                 * this is temporary visualisation of field selection
                 *
                 * ДОБАВИТЬ ЛОГИКУ ДЕЙСТВИЙ ПОЛТХОВАТЕЛЯ
                 *
                 * ЧТОБЫ КОРАБЛИ СТРОИЛИСЬ ПОСЛЕДОВАТЕЛЬНО
                 * И ЧТОБЫ КОРАБЛИ ВЗЫРВАЛИСЬ
                 */
                System.out.println("Текуший вид поля," + userX + userY + ", которое выбрал пользователь " +
                        Board.gameBoard[indexOfCoordX][indexOfCoordY].getImage());

                Board.gameBoard[indexOfCoordX][indexOfCoordY] = new Field(userX, userY);
                Board.gameBoard[indexOfCoordX][indexOfCoordY].setImage(Board.VIEW[1]);

                System.out.print("Мы меняем вид поля на \'" + Board.gameBoard[indexOfCoordX][indexOfCoordY].getImage() + "\'\n");



                /* temporary cycle exit*/
                roll--;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
