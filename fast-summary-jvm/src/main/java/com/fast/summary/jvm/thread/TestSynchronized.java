package com.fast.summary.jvm.thread;

import static com.fast.summary.jvm.utils.LoggerUtils.get;
import static java.lang.Thread.sleep;

/**
 * @author qinfuxiang
 */
public class TestSynchronized {
    static class Number{
        public static synchronized void a() throws InterruptedException {
            sleep(1);
            get().debug("1");
        }
        public synchronized void b() {
            get().debug("2");
        }
    }

    public static void main(String[] args) {
        Number n1 = new Number();
        Number n2 = new Number();
        new Thread(()->{
            try {
                n1.a();
//                Number.a();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()->{ n2.b(); }).start();
    }
}
