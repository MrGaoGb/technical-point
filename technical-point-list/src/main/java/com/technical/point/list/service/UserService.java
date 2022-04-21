package com.technical.point.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mr.Gao
 * @date: 2022年03月31日 9:56
 * @description:
 */
@Service
public class UserService {

    @Autowired
    private List<UserIService> userIListService;

    public void doWork() {
        System.out.println("doWork!");
        userIListService.stream().forEach(userIService -> {
            userIService.hello(1);
        });
    }
}
