package com.pc.framework.spring.service;

import com.pc.framework.spring.framework.Autowired;
import com.pc.framework.spring.framework.Component;

/**
 * @description: 用户服务Service
 * @author: Mr.Gao
 * @create: 2021-05-11 22:12
 **/
@Component("userService")
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }

}
