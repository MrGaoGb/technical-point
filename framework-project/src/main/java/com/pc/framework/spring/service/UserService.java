package com.pc.framework.spring.service;

import com.pc.framework.spring.framework.Autowired;
import com.pc.framework.spring.framework.BeanNameAware;
import com.pc.framework.spring.framework.Component;

/**
 * @description: 用户服务Service
 * @author: Mr.Gao
 * @create: 2021-05-11 22:12
 **/
@Component("userService")
public class UserService implements BeanNameAware {

    @Autowired
    private OrderService orderService;
    /**
     * 实现BeanNameAware接口Spring框架自动给beanName属性字段赋值(即在创建Bean的时候会回调该方法)
     */
    private String beanName;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }


    public void test() {
        System.out.println(orderService);
    }


}
