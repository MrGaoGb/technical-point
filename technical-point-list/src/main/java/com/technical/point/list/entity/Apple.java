package com.technical.point.list.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @Description java测试对象
 * @Author gaogba
 * @Date 2020/8/24 10:22
 * @Version 1.0
 */
@Getter
@Setter
public class Apple {

    private Integer id;
    private String name;
    private BigDecimal money;
    private Integer num;

    public Apple(Integer id, String name, BigDecimal money, Integer num) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.num = num;
    }



    @Override
    public String toString() {
        return "Apple{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", num=" + num +
                '}';
    }
}
