package com.technical.point.list.test.testthread.sync;

/**
 * @author: Mr.Gao
 * @date: 2021/12/1 10:45
 * @description:
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account("IMP小鬼抖音账户", 100);
        final Bank mine = new Bank(account, 50, "MINE");
        final Bank girlFriend = new Bank(account, 100, "GIRL FRIEND");
        new Thread(mine).start();
        new Thread(girlFriend).start();
    }
}

/**
 * 账户信息
 */
class Account {
    //账户名
    private String name;
    //账户余额
    private Integer money;

    public Account(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}

class Bank implements Runnable {

    //账户信息
    private Account account;
    //获取余额
    private Integer drawMoney;
    //当前余额
    private Integer nowMoney = 0;

    //取款对象
    private String name;

    public Bank(Account account, Integer drawMoney, String name) {
        this.account = account;
        this.drawMoney = drawMoney;
        this.name = name;
    }

    @Override
    public void run() {
        if (account.getMoney() - drawMoney <= 0) {
            System.out.println(Thread.currentThread().getName() + "当前账户余额不足");
            return;
        }

        //模拟延时放大问题的发生性
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //账户充足
        //进行取钱
        System.out.println(account.getName() + "原账户余额为:" + account.getMoney() + "W");
        account.setMoney(account.getMoney() - drawMoney);//账户余额
        nowMoney = nowMoney + drawMoney;//取到手里的钱
        System.out.println(account.getName() + "账户余额为:" + account.getMoney() + "W");
        System.out.println(this.name + "手上余额为:" + nowMoney + "W");
    }
}