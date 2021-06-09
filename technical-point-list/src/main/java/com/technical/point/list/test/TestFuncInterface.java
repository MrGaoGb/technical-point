package com.technical.point.list.test;

import com.technical.point.list.service.AccountService;

/**
 * @author gaogba
 * @description: 测试用例：函数式接口
 * @title: TestFuncInterface
 * @date 2021/4/16 17:13
 */
public class TestFuncInterface {
    public static void main(String[] args) {
        AccountService accountService = (uName, sayMsg) -> {
            System.out.println("TestFuncInterface[函数式接口] userName：" + uName + ",sayMsg:" + sayMsg);
            return uName;
        };
        String george = accountService.processHandler("GEORGE", "GEORGE dont want to say!!!!!");
        String tomcat = accountService.processHandler("TOMCAT", "TOMCAT dont want to say!!!!!");
        System.out.println(george + ":" + tomcat);
    }
}
