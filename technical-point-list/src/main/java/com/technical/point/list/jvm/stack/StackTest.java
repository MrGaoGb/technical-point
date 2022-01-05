package com.technical.point.list.jvm.stack;

/**
 * @author: Mr.Gao
 * @date: 2022年01月05日 15:43
 * @description:
 */
public class StackTest {

    public static void main(String[] args) {
        new StackTest().r();
    }

    /**
     * StackOverflowError 循环递归调用会造成栈溢出
     */
    public void r() {
        w();
    }

    public void w() {
        r();
    }
}
