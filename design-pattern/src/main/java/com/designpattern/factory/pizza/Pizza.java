package com.designpattern.factory.pizza;

/**
 * 披萨类
 *
 * @author Mr.Gao
 */
public abstract class Pizza {

    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 披萨准备工作
     */
    public abstract void prepare();

    /**
     * 烘培
     */
    public void bake() {
        System.out.printf(name + "烘培...");
    }

    /**
     * 切割
     */
    public void cut() {
        System.out.printf(name + "切割...");
    }

    /**
     * 打包
     */
    public void box() {
        System.out.printf(name + "打包...");
    }
}
