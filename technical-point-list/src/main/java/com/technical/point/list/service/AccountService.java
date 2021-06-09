package com.technical.point.list.service;

/**
 * @author gaogba
 * @description: Java8函数式接口:只包含一个抽象方法
 * @title: AccountService
 * @date 2021/4/16 17:08
 */
@FunctionalInterface
public interface AccountService {

    /**
     * 抽象方法
     *
     * @param uName
     * @param sayMsg
     */
    String processHandler(String uName, String sayMsg);

    /**
     * Object的equals方法不是抽象方法
     *
     * @param obi
     * @return
     */
    @Override
    public boolean equals(Object obi);

    /**
     * default方法不是抽象方法
     */
    default void defaultProcess() {
    }

    /**
     * static方法不是抽象方法
     */
    static void staticProcess() {
    }
}
