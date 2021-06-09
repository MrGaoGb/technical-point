package com.technical.point.eventpublishaware.other;

import com.technical.point.eventpublishaware.dao.UserInfoDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Description Spring中对BeanDefinition摸索
 * @Author gaogba
 * @Date 2020/12/23 21:58
 * @Version 1.0
 */
public class BeanDefinitionTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        UserInfoDao bean = applicationContext.getBean(UserInfoDao.class);
        System.out.println("新增信息:============EXEC BEGIN ==============");
        bean.addUser();
        System.out.println("修改信息:============EXEC BEGIN ==============");
        bean.updateUser();
        System.out.println("删除信息:============EXEC BEGIN ==============");
        bean.deleteUserById();
    }
}
