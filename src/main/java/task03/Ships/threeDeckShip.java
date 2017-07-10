package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;
import java.util.ArrayList;
import java.util.Collection;

public class threeDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 3;
    private String state;
    private Collection<Field> ship;
    private RandomService randS3 = new RandomService();
    private int serial_number;
    private int shipID;

    public threeDeckShip(int x){
        create();
        this.serial_number = x;
        System.out.println("3-deck ship added to battle field.");
    }
    public void create(){
        this.state = Ship.state[0];
        ship = new ArrayList<>();
        ship.addAll(randS3.getNewShipBuildInEmptyFreeSpace(numberOfDecks));
        setShipID(RandomService.shipID++);
    }
    public void changeState(int i){
        this.state = Ship.state[i];
    }
    public String showState(){
        return this.state;
    }
    public int showDecksNumber(){
        return threeDeckShip.numberOfDecks;
    }
    public int getSerialNumber(){
        return serial_number;
    }
    public int getShipID(){
        return shipID;
    }
    public void setShipID(int id){
        this.shipID=id;
    }
}
