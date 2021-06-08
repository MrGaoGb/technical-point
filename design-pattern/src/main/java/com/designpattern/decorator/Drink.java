package com.designpattern.decorator;

/**
 * @author Mr.Gao
 */
public abstract class Drink {

    private String desc;

    private float price =0.0f;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //用于计算
    public abstract float cost();
}
