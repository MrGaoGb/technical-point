package com.pc.framework.spring.service;

import com.pc.framework.spring.framework.BeanPostProcessor;
import com.pc.framework.spring.framework.Component;

/**
 * @author: Mr.Gao
 * @date: 2021/6/13 17:19
 * @description:
 */
@Component("myBeanPostProcessor")
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if ("userService".equals(beanName)){
            System.out.println("初始化前...进行操作");
            UserService userService =  (UserService)bean;
            userService.setBeanName("BeanPostProcess进阶了");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("初始化后....进行操作");
        return bean;
    }
}
