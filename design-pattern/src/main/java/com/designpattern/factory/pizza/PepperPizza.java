package com.designpattern.factory.pizza;

/**
 * 胡椒披萨
 */
public class PepperPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("胡椒披萨材料准备...");
    }
}
