package com.fast.summary.jvm.oom;


import com.fast.summary.jvm.utils.LoggerUtils;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.sun.deploy.util.BufferUtil.MB;

// -Xmx8m
public class TestOomThreadPool {
    public static void main(String[] args) {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();
        System.out.println("\t最大(上限)(M):"+headMemory.getMax()/MB);
        fixedThreadPool();
//        singleThreadExecutor();
//        cachedThreadPool();
//        scheduledThreadPool();
    }


    private static void fixedThreadPool() {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        LoggerUtils.get().debug("begin...");
        while (true) {
            executor.submit(() -> {
                try {
                    LoggerUtils.get().debug("send sms...");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void singleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        LoggerUtils.get().debug("begin...");
        while (true) {
            executor.submit(() -> {
                try {
                    LoggerUtils.get().debug("send sms...");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void cachedThreadPool() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new Task());
        }
    }

    static ExecutorService executorService = Executors.newCachedThreadPool();

    static class Task implements Runnable {
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void scheduledThreadPool() {
        ExecutorService executor = Executors.newScheduledThreadPool(2);
        LoggerUtils.get().debug("begin...");
        while (true) {
            executor.submit(() -> {
                try {
                    LoggerUtils.get().debug("send sms...");
                    TimeUnit.SECONDS.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
