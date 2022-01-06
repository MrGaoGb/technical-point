package com.pc.framework.spring.test;

import com.pc.framework.spring.config.AppConfig;
import com.pc.framework.spring.service.UserService;
import com.pc.framework.spring.framework.MrGaoApplicationContext;

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
