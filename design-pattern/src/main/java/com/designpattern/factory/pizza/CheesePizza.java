package com.designpattern.factory.pizza;

/**
 * 中国披萨
 * @author Mr.Gao
 */
public class CheesePizza extends Pizza{

    @Override
    public void prepare() {
        System.out.println("奶酪披萨原材料准备中...");
    }

}
