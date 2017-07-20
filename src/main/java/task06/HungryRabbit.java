package task06;

import java.io.IOException;

/**
 * Press 'ENTER' to stop rabbit eating carrot.
 */
public class HungryRabbit {
    volatile static boolean canEat = true;

    /**
     * Both  thread are running. UserThread is waitng for entry, after that it change the boolean flag,
     * that stops RabbitThread executing the precess of console text output.
     *
     * @param args
     */
    public static void main(String[] args) {
        new HungryRabbit.UserThread().start();
        new HungryRabbit.RabbitThread().start();
    }

    /**
     * Reads user input, after that it switches the boolean flag.
     */
    public static class UserThread extends Thread {
        @Override
        public void run() {
            try {
                int text = System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            canEat = false;
        }
    }

    /**
     * Outputs text in console, while user didn't press the 'ENTER'
     */
    public static class RabbitThread extends Thread {
        @Override
        public void run() {
            while (canEat) {
                System.err.println("Rabbit eating carrot....");
            }
            System.err.println("Enter is pressed. Rabbit stopped eating carrot.");
        }
    }
}