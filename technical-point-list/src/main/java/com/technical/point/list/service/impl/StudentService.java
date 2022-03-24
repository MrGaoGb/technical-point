package com.technical.point.list.service.impl;

import com.technical.point.list.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Gao
 * @create: 2021-04-21 16:29
 **/
@Service
public class StudentService implements PersonService {
    @Override
    public void doWork() {
        System.out.println("学生学习........!");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("学生Service >>>> setBeanName() >>> "+name);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("学生Service >>> afterPropertiesSet()");
    }
}
