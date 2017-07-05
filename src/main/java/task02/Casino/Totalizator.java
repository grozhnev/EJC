package task02.Casino;

import java.util.Random;

public class Totalizator {
    public static boolean countRandomBool(){
        return new Random().nextBoolean();
    }
    public static int countRandomNum(int bound){
        return new Random().nextInt(bound);
    }
}
