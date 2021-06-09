package com.pc.spring.service;

import com.pc.spring.framework.Autowired;
import com.pc.spring.framework.Component;
import com.pc.spring.framework.Scope;

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
