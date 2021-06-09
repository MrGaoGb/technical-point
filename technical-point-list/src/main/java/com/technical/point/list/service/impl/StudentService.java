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
}
