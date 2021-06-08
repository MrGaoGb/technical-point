package com.designpattern.decorator;

/**
 * 装饰者
 * @author Mr.Gao
 */
public class Decorator extends Drink {

    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        //super.getPrice() 自己(装饰者)价格 + 被装饰者的价格
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDesc() {
        return super.getDesc() + " " + super.getPrice() + "$ &&" + drink.getDesc();
    }
}
