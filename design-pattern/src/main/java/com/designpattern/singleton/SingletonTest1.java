package com.designpattern.singleton;

/**
 * @author Mr.Gao
 */
public class SingletonTest1 {


}

/**
 * 单例模式之懒汉式（在类装载的时候，初始化类的实例，1和2不涉及线程同步问题）
 * 1、静态常量饿汉式:private static final Singleton instance = new Singleton();
 * 2、静态代码块饿汉式：static{
 * instance = new Singleton();
 * }
 * 3、线程不安全懒汉式，只有当使用getInstance()方法时候，才创建对象实例。
 *  a.起到了懒加载作用，仅限于单线程使用
 *  b.在多线程环境，一个线程进入if(instance == null) 判断代码块，还未来得及往下执行，另一个线程也通过了这个判断语句，此时就会产生多个实例对象，
 *    这样就不满足单例模式，因此不适用于多线程环境中。
 * 4、线程安全懒汉式：在静态方法中加入synchronized同步锁，解决线程安全问题。
 *   缺点：效率太低。
 * 5、同步代码块懒汉式：（不推荐使用）
 * ==============================
 * 6、双重检查（Double Check）：既解决了线程安全问题，又保证了延时加载问题，同时也保证了效率。推荐使用此单例模式。
 * 7、静态内部类：（该类有一个内部属性Singleton），推荐使用此单例模式。
 *   a.在类装载过程中，静态内部类不会立即被装载。
 *   b.在调用getInstance方法的时候，会导致静态内部类的装载(利用JVM转载类的时候是线程安全的)，这样既保证了延时、也保证了线程安全
 *   **********************************
 *   c、该方式采用类装载的机制来保证实例化时只有一个线程。
 *   d、静态内部类方式在类被转载时候并不会被立即实例化，而是在需要的实例化时进行实例化，调用getInstance方法时，才会装载SingleInstance类，从而
 *    完成Singleton的实例化。
 * e、类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类的初始化时，别的线程是无法进入的。
 * 8、枚举方式：（强烈推荐使用）
 *    该方式不仅能避免线程同步问题，而且还能防止反序列重新创建新的对象。
 *
 * 单例模式注意事项以及细节说明：
 *  a、单例模式保证了系统内存中只存在一个对象，节省了系统资源，对于一些需要频繁创建销毁的对象，使用单例模式可以提高系统性能。
 *  b、在获得一个对象的实例时，通过获取实例方法即可，而不是使用new。
 *  c、单例模式使用的场景：需要频繁的创建销毁对象、创建对象耗时过多或耗费资源过多（即重量级对象），但又经常用的对象，工具类对象、频繁访问数据库或文件
 *  的对象（比如数据源、session工厂等）。
 *
 *  #############################
 * 在JDK中，java.lang.Runtime就是经典的单例模式（饿汉式）
 */
class Singleton {

    /**
     * 构造器私有化，防止new 对象
     */
    private Singleton() {
    }

    /**
     * 本类内部创建对象实例(静态常量饿汉式)
     */
    private static final Singleton instance = new Singleton();

    /**
     * 提供一个共有的静态方法，返回对象实例
     *
     * @return
     */
    public static Singleton getInstance() {
        return instance;
    }

    /**
     * 双重检查单例模式
     *
     * @return
     */
    public static Singleton getDoubleCheckInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) {
                    return new Singleton();
                }
            }
        }
        return instance;
    }

    /**
     * 静态内部类
     */
    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    /**
     * 静态内部类对外提供一个获取实例的方法
     *
     * @return
     */
    public static Singleton getStaticSingletonInstance() {
        return SingletonInstance.INSTANCE;
    }
}
