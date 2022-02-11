package com.fast.summary.jvm.thread;

import java.util.concurrent.CountDownLatch;

import static com.fast.summary.jvm.utils.LoggerUtils.get;

// 原子性例子

/**
 * t1 10
 * 0: getstatic
 * t2
 * 0: getstatic 10
 * 3: iconst_5
 * 4: isub
 * 5: putstatic
 * 5
 * 3: iconst_5
 * 4: iadd
 * 5: putstatic
 * 15
 */
public class AddAndSubtract {

    static volatile int balance = 10;

    public static void subtract() {
        int b = balance;  // 1
        b -= 5;   // 2
        balance = b; //6
    }

    public static void add() {
        int b = balance;  //3
        b += 5;   //4
        balance = b;  //5
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(() -> {
            subtract();
            latch.countDown();
        }).start();
        new Thread(() -> {
            add();
            latch.countDown();
        }).start();
        latch.await();
        get().debug("{}", balance);
    }
}
