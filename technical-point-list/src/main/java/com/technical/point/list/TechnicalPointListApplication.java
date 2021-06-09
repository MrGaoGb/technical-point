package com.technical.point.list;

import com.technical.point.list.service.PersonTestService;
import com.technical.point.list.test.TestAutowiredMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动类
 *
 * @desciption: 该工程主要是和list集合相关的技术点总结
 *
 * @author gaogba
 */
@SpringBootApplication
public class TechnicalPointListApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalPointListApplication.class, args);
//        SpringApplication springApplication = new SpringApplication(TechnicalPointListApplication.class);
//        ApplicationContext context = springApplication.run(args);
//        PersonTestService personService = context.getBean(PersonTestService.class);
//        personService.getBeanName("studentService");
    }

}
