package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;
import java.util.ArrayList;
import java.util.Collection;

public class fourDeckShip extends ArrayList<Field> implements Ship  {

    private static final int numberOfDecks = 4;
    private String state;
    private RandomService randS4 = new RandomService();
    private Collection<Field> ship;
    //private boolean alive;
    private int serial_number;
    private int shipID;

    public fourDeckShip(int z){
        create();
        //setAlive(true);
        this.serial_number = z;
        System.out.println("4-deck ship added to battle field.");
    }
    public void create(){
        this.state = Ship.state[0];
        ship = new ArrayList<>();
        ship.addAll(randS4.getNewShipBuildInEmptyFreeSpace(numberOfDecks));
        setShipID(RandomService.shipID++);
    }
    //работа с состоянием короболя
    public void changeState(int i){
        this.state = Ship.state[i];
    }
    public String showState(){
        return this.state;
    }
    public int showDecksNumber(){
        return fourDeckShip.numberOfDecks;
    }

    public int getSerialNumber(){
        return serial_number;
    }
    /*public boolean isAlive() {
        return alive;
    }
    public void setAlive(boolean alive) {
        this.alive = alive;
    }*/

    public int getShipID(){
        return shipID;
    }
    public void setShipID(int id){
        this.shipID=id;
    }

    /*
     *     // add field type changing
     */


}
