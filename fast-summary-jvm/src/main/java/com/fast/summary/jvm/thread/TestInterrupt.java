package com.fast.summary.jvm.thread;

import static com.fast.summary.jvm.utils.LoggerUtils.get;
import static java.lang.Thread.sleep;

/**
 * @author qinfuxiang
 */
public class TestInterrupt {
    public static void main(String[] args) throws InterruptedException {
        test2();
    }
    private static void test1() throws InterruptedException {
        Thread t1 = new Thread(()->{
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();
        sleep(500);
        t1.interrupt();
        get().debug(" 打断状态: {}", t1.isInterrupted());
    }

    private static void test2() throws InterruptedException {
        Thread t2 = new Thread(()->{
            while(true) {
                Thread current = Thread.currentThread();
                boolean interrupted = current.isInterrupted();
                if(interrupted) {
                    get().debug(" 打断状态: {}", interrupted);
                    break;
                }
            }
        }, "t2");
        t2.start();
        sleep(500);
        t2.interrupt();
    }
}
