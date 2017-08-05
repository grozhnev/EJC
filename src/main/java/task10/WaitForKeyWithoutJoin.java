package task10;

/**
 * Class: WaitForKeyWithoutJoin
 * Version: 0.1
 * <p>
 * Created by: Georgii Rozhnev, https://github.com/grozhnev
 * Date: 2017-08-04
 * <p>
 * Description:
 *  Main purpose of this class is to perform wait(), notifyAll() and synchronized key in multi-threading.
 *      1) Master thread starts, in which 10 slave threads start running  one by one after 1 second pause.
 *      2) From the very beginning master thread is focused on the 'key' object that is defined wih 'wait' state.
 *      3) When the 6th slave thread starts running, the 'key' object is being notified with notifyAll().
 *      4) As we can see, Master thread ends on this 6th iteration, when 'key' object escape from wait.
 * <p>
 *      This example runs without join() method, for that reason master thread releases before it should do.
 */
public class WaitForKeyWithoutJoin {
    private static final Object key = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    System.err.println("Thread " + i + " running....");
                    if (i == 6) {
                        synchronized (key) {
                            System.err.println("It's 6  -> -> ->  Notify ALL threads!");
                            key.notifyAll();
                        }
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ie) {
                        ie.printStackTrace();
                    }
                }
            }
        });
        System.err.println("Beginning....");
        thread.start();
        synchronized (key) {
            key.wait();
        }
        System.err.println("Main thread is finished!"); /* main thread is over */
    }
}