package task10;

/**
 * Class: ThreadYielding
 * Version: 0.1
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date: 2017-08-04
 * <p>
 * Description: preforming yield() method in multi threading:
 *      We run 5 threads for 5 iterations each. As we use yield() method in each iteration, we can see that all
 *      threads perform equally, start and finish almost at the same time together ().
 */
public class ThreadYielding {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.err.println(Thread.currentThread().getName() + " #" + i);
                    Thread.yield();
                }
            }
        };

        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }
}