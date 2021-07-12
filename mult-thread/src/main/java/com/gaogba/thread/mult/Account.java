package com.gaogba.thread.mult;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2021/7/4 16:57
 * @description:模拟账户转帐: 在获取账户余额是否需要新增synchronized来修饰，若不加会使得用户账户余额出现幻读的情况
 */
public class Account {
    private String name;

    private BigDecimal money;

    /**
     * 账户加钱
     *
     * @param name
     * @param money
     * @return
     */
    public synchronized void setAddMoney(String name, BigDecimal money) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.money = money;
    }

    /**
     * 在获取余额这块，若不加synchronized，会出现幻读的情况，若加上，则两次账户余额获取值均为10
     *
     * @param name
     */
    public /*synchronized*/ void getBalance(String name) {
        System.out.println("当前用户:" + name + ":所剩余额为:" + this.money);
    }

    public static void main(String[] args) {
        Account account = new Account();
        new Thread(() -> account.setAddMoney("zhangSan", BigDecimal.TEN)).start();
        try {
            //延时2s
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.getBalance("zhangSan");//此时获取该账户余额为null(获取不加synchronized修饰)
        //延时3s
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        account.getBalance("zhangSan");//此时获取用户余额为10（获取不加synchronized修饰）
    }

}
