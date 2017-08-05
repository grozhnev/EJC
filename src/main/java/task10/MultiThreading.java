package task10;

/**
 * Class: MultiThreading
 * Version: 0.1
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date: 2017-08-04
 * <p>
 * Description: Performing multi-threading programming: start(), join(), sleep().
 */
public class MultiThreading {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 5; i++) {
                    System.err.println("Thread " + i + " started. ");
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.err.println("Beginning of the master thread.");
        thread.start();
        System.err.println("\tBecause of join(), master thread releases it the end.");
        thread.join();
        System.err.println("Master thread is over.");
    }
}
