package com.designpattern.factory.pizza;

/**
 * 希腊披萨
 * @author Mr.Gao
 */
public class GreekPizza extends Pizza{

    @Override
    public void prepare() {
        System.out.println("希腊披萨原材料准备中...");
    }

}
