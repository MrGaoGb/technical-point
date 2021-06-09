package com.pc.spring.test;

import com.pc.spring.config.AppConfig;
import com.pc.spring.framework.MrGaoApplicationContext;
import com.pc.spring.service.UserService;

/**
 * @description: 测试自定义ApplicationContext
 * @author: Mr.Gao
 * @create: 2021-05-11 22:11
 **/
public class TestApplicationContext {
    public static void main(String[] args) {
        MrGaoApplicationContext applicationContext = new MrGaoApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        System.out.println(userService);
        userService.test();
    }
}
