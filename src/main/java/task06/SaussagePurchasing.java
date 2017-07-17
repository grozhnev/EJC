package task06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * У нас есть деньги. Мы пришли в магазин за колбасой.
 * Ввести число, сколько палок колбасы нужно купить.
 * Каждая покупка - отдельный поток.
 * Покупки происходят циклами. Какой число ввели - столько циклов.
 * Нельзя купить колбасу на сумму больше, чем в банке.
 * метод принимает число,
 */

public class SaussagePurchasing {
    private static final int PRICE = 320;
    private static int amount;
    private static volatile int cash = 1200;

    public static void main(String[] args) {
        System.out.println("У вас " + cash + " рублей. Цена колбасы сегодня " + PRICE + " руб/шт.");
        System.out.println("Сколько колбасок купить изволите ?");

        try (BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in))) {
            amount = Integer.parseInt(buffReader.readLine().trim());

            /**
             * Instantiating threads.
             * Total amount of threads equals the number user has entered.
             */
            ArrayList<Thread> purchasingThreads = new ArrayList<>();
            for (int i = 0; i < amount; i++) {
                purchasingThreads.add(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (cash >= PRICE) {
                            cash -= PRICE;
                            System.err.println(Thread.currentThread() + " purchase a sausage. Cash available is : " + cash);
                        } else {
                            System.err.println("Not enough money.");
                        }
                    }
                }));
            }

            /**
             * Each thread make only one purchase, while there is enough money.
             */
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}