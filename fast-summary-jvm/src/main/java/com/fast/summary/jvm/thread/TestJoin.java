package com.fast.summary.jvm.thread;

import static com.fast.summary.jvm.utils.LoggerUtils.get;
import static java.lang.Thread.sleep;

public class TestJoin {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        test4();
    }

    public static void test4() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });
        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致 join 结束
        t1.join(1500);
        long end = System.currentTimeMillis();
        get().debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }

    public static void test3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        });
        long start = System.currentTimeMillis();
        t1.start();
        // 线程执行结束会导致 join 结束
        t1.join(1500);
        long end = System.currentTimeMillis();
        get().debug("r1: {} r2: {} cost: {}", r1, r2, end - start);
    }
}
