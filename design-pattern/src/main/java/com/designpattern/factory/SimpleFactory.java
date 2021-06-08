package com.designpattern.factory;

import com.designpattern.factory.pizza.CheesePizza;
import com.designpattern.factory.pizza.GreekPizza;
import com.designpattern.factory.pizza.PepperPizza;
import com.designpattern.factory.pizza.Pizza;

/**
 * 简单工厂模式：
 *  1、简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例。
 *  2、另外一种说法是：定义了一个创建对象的类，由该类封装实例化对象的行为（代码）
 */
public class SimpleFactory {

    /**
     * 使用简单工厂模式
     * @param orderType
     * @return
     */
    public Pizza createPizza(String orderType){
        Pizza pizza = null;
        if (orderType.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        }else if (orderType.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName("中国披萨");
        }else if (orderType.equals("pepper")){
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
    /**
     * 简单工厂模式 也叫静态工厂模式
     * @param orderType
     * @return
     */
    public static Pizza createPizza2(String orderType){
        Pizza pizza = null;
        if (orderType.equals("greek")){
            pizza = new GreekPizza();
            pizza.setName("希腊披萨");
        }else if (orderType.equals("cheese")){
            pizza = new CheesePizza();
            pizza.setName("中国披萨");
        }else if (orderType.equals("pepper")){
            pizza = new PepperPizza();
            pizza.setName("胡椒披萨");
        }
        return pizza;
    }
}
