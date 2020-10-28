package atomic;

import org.apache.poi.ss.formula.functions.T;

/**
 * @author ：tao
 * @date ：Created in 2020/10/18 23:55
 * @description：${description}
 * @modified By：
 * @version: version
 */

public class threadlocal {
    private static ThreadLocal<Integer> a = new ThreadLocal<Integer>();



    public static void main(String[] args) {

        new Thread(new Runnable() {
            public void run() {
                int i=0;
                while (true) {
                    try {
                        a.set(i++);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "   " + a.get());
                }
            }
        }).start();

        new Thread() {
            public void run() {
                int i=0;
                while (true) {
                    try {
                        a.set(i++);
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "   " + a.get());
                }
            };
        }.start();

    }


}


