package com.designpattern.decorator;

public class CoffeeBarClient {
    public static void main(String[] args) {
        //装饰模式下 ：下单 2份巧克力+一份巧克力的LongBlack
        Drink order = new LongBlack();
        System.out.println("order 一杯美式咖啡 费用 price:"+order.getPrice());
        System.out.println("order 一杯美式咖啡 描述 desc:"+order.getDesc());

        //加一份巧克力
        order =  new Chocolate(order);
        System.out.println("order 一杯美式咖啡 加一份巧克力 费用 price:"+order.cost());
        System.out.println("order 一杯美式咖啡 加一份巧克力 描述 desc:"+order.getDesc());

        //加一份巧克力
        order =  new Chocolate(order);
        System.out.println("order 一杯美式咖啡 加2份巧克力 费用 price:"+order.cost());
        System.out.println("order 一杯美式咖啡 加2份巧克力 描述 desc:"+order.getDesc());
    }
}
