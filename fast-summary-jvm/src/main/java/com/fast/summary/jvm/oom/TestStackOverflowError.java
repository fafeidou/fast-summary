package com.fast.summary.jvm.oom;

/**
 * StackOverflowError ：栈溢出
 * 什么时候会让 Java Method Stack 栈溢出啊？
 * 栈的基本特点就是 FILO（First In Last Out），
 * 如果 in 的太多而 out 的太少，就好 overflow 了。
 * 而 Java Method Stack 的功能就是保存每一次函数调用时的“现场”，
 * 即为入栈，函数返回就对应着出栈，所以函数调用的深度越大，栈就变得越大，足够大的时候就会溢出。
 * 所以模拟 Java Method Stack 溢出，只要不断递归调用某一函数就可以。
 * @author qinfuxiang
 */
public class TestStackOverflowError {
    private static int stackLength = 0;

    public void stackOverflow() {
        ++stackLength;
        stackOverflow();
    }

    public static void main(String[] args) {
        TestStackOverflowError test = new TestStackOverflowError();
        try {
            test.stackOverflow();
        } catch (Throwable e) {
            System.out.println("stack length: " + test.stackLength);
            throw e;
        }
    }
}
