package com.designpattern.decorator;

/**
 * 具体的Decorator 调味品
 * @author Mr.Gao
 */
public class Chocolate extends Decorator{
    
    public Chocolate(Drink drink) {
        super(drink);
        setDesc(" 巧克力");
        setPrice(5.0f);
    }


}
