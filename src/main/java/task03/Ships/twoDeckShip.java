package task03.Ships;

import task03.Board.Field;
import task03.GameLogic.RandomService;
import java.util.ArrayList;
import java.util.Collection;

public class twoDeckShip extends ArrayList<Field> implements Ship {

    private static final int numberOfDecks = 2;
    private String state;
    private Collection<Field> ship;
    private RandomService randS2 = new RandomService();
    private int serial_number;
    private int shipID;

    public twoDeckShip(int y){
        create();
        this.serial_number= y ;
        System.out.println("2-deck ship added to battle field.");
    }
    public void create(){
        this.state = Ship.state[0];
        ship = new ArrayList<>();
        ship.addAll(randS2.getNewShipBuildInEmptyFreeSpace(numberOfDecks));
        setShipID(RandomService.shipID++);
    }
    public void changeState(int i){
        this.state = Ship.state[i];
    }
    public String showState(){
        return this.state;
    }
    public int showDecksNumber(){
        return twoDeckShip.numberOfDecks;
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
