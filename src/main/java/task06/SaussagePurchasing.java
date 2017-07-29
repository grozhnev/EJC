package task06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * You got the money. You came to the shop to buy some sausages.
 * Enter the amount of sausages you want to purchase.
 * Each purchase runs in a different thread.
 * The whole purchasing process runs in cycle.
 * As it happens - the input number is the amount of running cycles.
 * You can't buy more sausages than your money allow you to do so.
 */

public class SaussagePurchasing {
    private static final int PRICE = 41;
    private static int amount;
    private static volatile int cash = 645;

    public static void main(String[] args) {
        System.out.println("You've got " + cash + " money. Sausage price today is " + PRICE + " money.");
        System.out.print("How much sausage would you like to purchase? ");
        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))) {
            purchase(buffReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void purchase(BufferedReader buffReader) throws IOException {
        amount = Integer.parseInt(buffReader.readLine().trim());
        ArrayList<Thread> purchasingThreads = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            purchasingThreads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    if (cash >= PRICE) {
                        cash -= PRICE;
                        System.err.println(Thread.currentThread() + " purchase sausage. Cash available : " + cash);
                    } else {
                        System.err.println("Not enough money.");
                    }
                }
            }));
        }
        do {
            for (Thread eachThread : purchasingThreads) {
                try {
                    eachThread.start();
                    amount--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } while (amount > 0);
    }
}
