package task02.Casino;

import task02.Duck.Duck;
import task02.Duck.DuckRacer;
import task02.Duck.MallardDuck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class specify the rules of the Game and race's logic.
 */
public class Game {
    private static int BET = 100;
    private static int MAX_SPEED = 100;
    private static int MIN_SPEED = 10;
    private static int N_ducks = 5;
    private static int Winner;
    private List<Duck> participants;

    public static int getMaxSpeed() {
        return MAX_SPEED;
    }
    public static int getMinSpeed() {
        return MIN_SPEED;
    }

    DuckRacer d_racer = new DuckRacer();
    MallardDuck m_duck = new MallardDuck();
    Player player = new Player();

    public void createDucks(){
        participants = new ArrayList<>(N_ducks);
        for (int i = 0; i < 5; i++) {
            if(Totalizator.countRandomBool()) {
                participants.add(new DuckRacer(i));
            } else {
                participants.add(new MallardDuck());
            }
        }
    }
    private void setSpeed(){
        for (int j = 0; j <participants.size()-1 ; j++) {
            if (participants.get(j) instanceof DuckRacer) {
                participants.get(j).setFlightSpeed(((DuckRacer) participants.get(j)).calculateSpeed());
            } else if ( participants.get(j) instanceof MallardDuck) {
                participants.get(j).setFlightSpeed(0);
            }
        }
    }

    private void runTheRace(){
        createDucks();
        for (int m = 0; m <10 ; m++) {
            for (int n = 0 ; n <participants.size()-1 ; n++) {
                if(participants.get(n) instanceof DuckRacer){
                    ((DuckRacer) participants.get(n)).calculateDistance();
                } else if (participants.get(n) instanceof MallardDuck) {
                    participants.get(n).setDistance(0);
                }
            }
        }
    }

    private void findTheWinner(){
        int temp_distance = 0;
        for (Duck d: participants ) {
            if (d.getDistance() > temp_distance) {
                temp_distance = d.getDistance();
                Winner = participants.indexOf(d);
            } else{
                d.setDistance(0);
            }
        }
    }

    public int getTheWinner(){
        return Winner;
    }

    private void accounting(){
        if (getTheWinner()+1 == player.getAnswer()){
            Announcement.winTheRace();
            player.earnTheMoney(Game.BET);
            System.out.println("Now you've got " + player.showTheMoney() + " RUB.\n");
        } else {
            Announcement.loseTheRace();
            player.payTheMoney(Game.BET);
            System.out.println("Now you've got " + player.showTheMoney() + " RUB.\n");
        }
    }

    private void showTheResultsOfEachDuck(){
        for (Duck d:participants ) {
            System.out.print("\nThe Duck #" + (participants.indexOf(d)+1));
            if (d instanceof DuckRacer) {
                System.out.print(" flew " + d.getDistance() + " meters.");
                if (getTheWinner() == participants.indexOf(d)) {
                    Announcement.isTheWinner();
                }
            } else if ( d instanceof MallardDuck) {
                Announcement.cannotFly();
            }
            if (participants.indexOf(d) == (player.getAnswer()-1) ) Announcement.wasYourChoice();
        }
        System.out.println();
    }

    /**
     * Игра реализована через Scanner, а не через BufferedReader, поскольку поток по какой-то причине закрывался в любом
     * случае после первой итерации цикла do().
     * Теперь игра просто продолжается, пока не закончится банк.
     *
     * Также, был опущен в комментарии цикл проверки достаточности денег и считывание желания кользователя продолжить.
     */
    public void play(){
        Announcement.rules();
        /*try (*/Scanner num = new Scanner(System.in) ;/*BufferedReader num = new BufferedReader(new InputStreamReader(System.in))*//*){*/
            //do {
                while(player.showTheMoney() != 0){
                //Scanner sc = new Scanner(System.in);

                Announcement.greet();
                createDucks();
                setSpeed();
                player.setAnswer(Integer.parseInt(num.next()/*num.readLine().trim()*/));
                runTheRace();
                findTheWinner();
                Announcement.results();
                showTheResultsOfEachDuck();
                accounting();
                //Announcement.proceed();
                //player.setAnswer(Integer.parseInt(num.next()/*num.readLine().trim()*/));
                //player.isAbleToPlay();
            } /*while (player.canPlay());*/
        /*} catch (IOException e){
            e.printStackTrace();
        }*/
        num.close();
    }
}
