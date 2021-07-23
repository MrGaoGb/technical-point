package com.technical.point.list.test;


import com.technical.point.list.config.Config;
import com.technical.point.list.service.PersonTestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: Mr.Gao
 * @create: 2021-04-21 16:37
 **/
public class TestAutowiredMap {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        PersonTestService personTestService = applicationContext.getBean(PersonTestService.class);
//        System.out.println(personTestService);
//        System.out.println(personTestService);
//        System.out.println(personTestService);
        personTestService.getBeanName("studentService");
    }
}
