package com.fast.summary.jvm.oom;

import groovy.lang.GroovyShell;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

// -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=10m
// 模拟不断生成类, 但类无法卸载的情况
public class TestOomTooManyClass {
    static String base = "string";
    public static void main(String[] args) {
        testDynamicCreateClass();
    }

    private static void testDynamicCreateClass() {
        AtomicInteger c = new AtomicInteger();
        while (true) {
            try (FileReader reader = new FileReader("script")) {
                GroovyShell shell = new GroovyShell();
                shell.evaluate(reader);
                System.out.println(c.incrementAndGet());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
