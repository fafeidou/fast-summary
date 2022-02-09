package com.fast.summary.jvm.oom;

import java.nio.ByteBuffer;

// -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
public class TestOomDirectBufferMemory {
    public static void main(String[] args) {
        System.out.println("查看配置的本地内存maxDirectMemory:" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        //停顿一下方便看效果
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //-XX:MaxDirectMemorySize=5m 本地内存配置的是5MB,这里实际使用的是6MB
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
