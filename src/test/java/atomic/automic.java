package atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：tao
 * @date ：Created in 2020/10/19 0:32
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class automic {

    private AtomicInteger a = new AtomicInteger(0);

    public  int add() {
        return a.getAndIncrement();
    }

    public static void main(String[] args) {
        automic sequence = new automic();
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
