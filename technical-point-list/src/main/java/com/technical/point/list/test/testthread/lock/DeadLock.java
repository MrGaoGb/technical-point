package com.technical.point.list.test.testthread.lock;

/**
 * @author: Mr.Gao
 * @date: 2021/12/1 15:56
 * @description: 死锁问题
 * <p>
 * ### 死锁
 * ### 产生死锁的四个必要条件
 * - 1、互斥条件：一个资源每次只能被一个进程使用。
 * - 2、请求与保持条件：一个进程因请求资源而阻塞时，对已获得的资源保持不放。
 * - 3、不剥夺条件：进程已获得的资源，在未使用完之前，不能进行强行剥夺。
 * - 3、循环等待条件：若干进程之间形成一种头尾相接的循环等待资源关系。
 * </p>
 */
public class DeadLock {
    public static void main(String[] args) {
        Makeup makeup1 = new Makeup(0, "灰姑凉");
        Makeup makeup2 = new Makeup(1, "白雪公主");
        new Thread(makeup1).start();
        new Thread(makeup2).start();
    }
}

/**
 * 口红
 */
class LipStick {
}

/**
 * 镜子
 */
class Mirror {
}

class Makeup implements Runnable {

    static LipStick lipStick = new LipStick();
    static Mirror mirror = new Mirror();

    private Integer choice;

    private String girlName;

    public Makeup(Integer choice, String girlName) {
        this.choice = choice;
        this.girlName = girlName;
    }

    @Override
    public void run() {

        if (choice == 0) {
            synchronized (lipStick) {
                //先拿到口红的锁
                System.out.println(girlName + "拿到口红的锁");
                //1秒延时之后再拿镜子的锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //1s之后再拿到镜子的锁
                synchronized (mirror) {
                    System.out.println(girlName + "拿到镜子的锁");
                }
            }
        } else {
            synchronized (mirror) {
                //先拿到镜子的锁
                System.out.println(girlName + "拿到镜子的锁");
                //1秒延时之后再拿口红的锁
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //1s之后再拿到口红的锁
                synchronized (lipStick) {
                    System.out.println(girlName + "拿到口红的锁");
                }
            }
        }
    }
}