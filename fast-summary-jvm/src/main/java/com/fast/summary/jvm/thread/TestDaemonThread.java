package com.fast.summary.jvm.thread;

import static com.fast.summary.jvm.utils.LoggerUtils.get;
import static java.lang.Thread.sleep;

public class TestDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        get().debug("开始运行...");
        Thread t1 = new Thread(() -> {
            get().debug("开始运行...");
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            get().debug("运行结束...");
        }, "daemon");
        // 设置该线程为守护线程
        t1.setDaemon(true);
        t1.start();
        sleep(1000);
        get().debug("运行结束...");
    }
}
