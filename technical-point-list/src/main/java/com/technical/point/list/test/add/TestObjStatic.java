package com.technical.point.list.test.add;

import com.technical.point.list.entity.Apple;

/**
 * @author: Mr.Gao
 * @date: 2022年08月03日 19:15
 * @description:
 */
public class TestObjStatic {
    private static final Apple a = new Apple();
    private static final Apple b = new Apple();

    public static void main(String[] args) {
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
        System.out.println(a == b);
    }
}
