package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;
import java.util.ArrayList;
import java.util.Collection;

public class oneDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 1;
    private String state;
    private Collection<Field> ship;
    private RandomService randS1 = new RandomService();
    //private boolean alive;
    private int serial_number;
    private int shipID;

    public oneDeckShip(int w){
        create();
        //setAlive(true);
        this.serial_number = w;
        System.out.println("1-deck ship added to battle field.");
    }
    public void create(){
        this.state = Ship.state[0];
        ship = new ArrayList<>();
        ship.addAll(randS1.getNewShipBuildInEmptyFreeSpace(numberOfDecks));
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
        return oneDeckShip.numberOfDecks;
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
}
