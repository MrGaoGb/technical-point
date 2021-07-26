package com.technical.point.list.config;

import com.technical.point.list.service.PersonTestService;
import com.technical.point.list.service.impl.StudentService;
import com.technical.point.list.service.impl.TeacherService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Mr.Gao
 * @date: 2021/7/23 10:55
 * @description:
 */
@Configuration
public class Config {

    @Bean
    public StudentService studentService1(){
        return new StudentService();
    }
    @Bean
    public TeacherService teacherService1(){
        return new TeacherService();
    }
    @Bean
    public PersonTestService personTestService1(){
        return new PersonTestService();
    }
}
