package com.technical.point.list.service;

import com.technical.point.list.service.config.ScanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author: Mr.Gao
 * @date: 2022年03月04日 13:49
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ScanConfig.class);
        PersonTestService personService = context.getBean(PersonTestService.class);
        personService.getBeanName("0002TeacherService");
    }
}
