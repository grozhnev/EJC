package task03v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Launcher of task03v2 game.
 */
public class Launch extends Board {

    public static void main(String[] args) {
        Say.header();
        Say.whatToDo();
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(System.in))) {
            Player player = new Player();
            player.buildPlayground();
            printTheBoard();
            instantiateShipsOnBoard();
            do {
                Say.forQuit();
                Player.choice = buff.readLine().toUpperCase().trim();

                if (Player.choice == "Q") {
                    break;
                } else {
                    Say.chooseField();
                    String inputString = buff.readLine().toUpperCase().trim();

                    if (inputString.matches("[a-jA-J]([1-9]|10)")) {

                        if (Player.userShots.contains(Board.gameBoard.get(inputString))) {
                            Say.tryAnotherOne();
                        } else {
                            /* add user choice to 'userShots' to escape the endless shooting at same field */
                            Player.userShots.add(Board.gameBoard.get(inputString));
                            if (Board.gameBoard.get(inputString).getFieldStatus() == ICoordinates.TYPE[0]) {
                                Board.gameBoard.get(inputString).setFieldStatus(5);
                                System.out.println("It was empty.");
                            } else if (Board.gameBoard.get(inputString).getFieldStatus() == ICoordinates.TYPE[1]) {
                                Board.gameBoard.get(inputString).setFieldStatus(3);
                                System.out.println("Field over the deck.");
                            } else if (Board.gameBoard.get(inputString).getFieldStatus() == ICoordinates.TYPE[2]) {
                                breakCycleFlag:
                                for (Ship boat : Board.fleet) {
                                    if (boat.shipBoats.contains(Board.gameBoard.get(inputString))) {
                                        /* we found the required ship*/
                                        for (int i = 0; i < boat.shipBoats.size(); i++) {
                                            if (boat.shipBoats.get(i).getFieldStatus() == ICoordinates.TYPE[2] &&
                                                    boat.shipBoats.get(i) != Board.gameBoard.get(inputString)) {
                                                Board.gameBoard.get(inputString).setFieldStatus(4);
                                                break breakCycleFlag;
                                            }
                                        }
                                        /* Set status of killed ship */
                                        for (int j = 0; j < boat.shipBoats.size(); j++) {
                                            boat.shipBoats.get(j).setFieldStatus(7);
                                        }

                                        /* Mark field arround dead ship */
                                        for (int k = 0; k < boat.shipBoats.size(); k++) {
                                            Character coordX = boat.shipBoats.get(k).getCoordinateX();
                                            int coordXAsInt = Arrays.asList(ICoordinates.X).indexOf(coordX);
                                            Integer coordY = boat.shipBoats.get(k).getCoordinateY();
                                            for (int p = coordXAsInt - 1; p <= coordXAsInt + 1; p++) {
                                                for (int q = coordY - 1; q <= coordY + 1; q++) {
                                                    if ((getGameboardField(ICoordinates.X[p], ICoordinates.Y[q]).getFieldStatus() == ICoordinates.TYPE[1]) ||
                                                            getGameboardField(ICoordinates.X[p], ICoordinates.Y[q]).getFieldStatus() == ICoordinates.TYPE[3]) {
                                                        getGameboardField(ICoordinates.X[p], ICoordinates.Y[q]).setFieldStatus(6);
                                                    }
                                                }
                                            }
                                        }
                                        /* ship is leaving the game */
                                        fleet.remove(boat);
                                        System.out.println("Removing " + boat.toString() + " ship. ");
                                        System.out.println("Currently in 'fleet' there are " + fleet.size() + " ships.");
                                    }
                                }
                            }
                        }
                    } else {
                        Say.wrongCoordinates();
                    }
                    printTheBoard();
                }
            } while (fleet.size() > 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
