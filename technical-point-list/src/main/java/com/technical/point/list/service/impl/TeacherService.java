package com.technical.point.list.service.impl;

import com.technical.point.list.service.PersonService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Mr.Gao
 * @create: 2021-04-21 16:30
 **/
@Service
public class TeacherService implements PersonService {
    @Override
    public void doWork() {
        System.out.println("教师工作.........!");
    }
}
