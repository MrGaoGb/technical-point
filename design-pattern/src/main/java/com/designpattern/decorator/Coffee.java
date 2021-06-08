package com.designpattern.decorator;

/**
 * 中间层
 * @author Mr.Gao
 */
public class Coffee extends Drink{
    @Override
    public float cost() {
        return super.getPrice();
    }
}
