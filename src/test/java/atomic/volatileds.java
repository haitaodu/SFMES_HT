package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：tao
 * @date ：Created in 2020/10/19 0:55
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class volatileds {
    private volatile int a=0;

    public  int add() {
        return a++;
    }

    public static void main(String[] args) {
        volatileds sequence = new volatileds();
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "   " + sequence.add());
                }
            }
        }).start();

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "   " + sequence.add());
                }
            };
        }.start();

    }
}
