package com.fast.summary.jvm.thread;

import java.util.concurrent.locks.LockSupport;

import static com.fast.summary.jvm.utils.LoggerUtils.get;
import static java.lang.Thread.sleep;

/**
 * @author qinfuxiang
 */
public class TestPark {
    public static void main(String[] args) throws InterruptedException {
        test4();
    }
    private static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            get().debug("park...");
            LockSupport.park();
            get().debug("unpark...");
            get().debug("打断状态：{}", Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        sleep(500);
        t1.interrupt();
    }
    private static void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                get().debug("park...");
                LockSupport.park();
                get().debug("打断状态：{}", Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        sleep(1000);
        t1.interrupt();
    }
}
