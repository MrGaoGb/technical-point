package com.technical.point.eventpublishaware.listener;

import com.technical.point.eventpublishaware.event.UserRegisterEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description 事件监听：监听到事件发布后，自动执行onApplicationEvent方法，所以该类需要有IOC来管理，类上要加@Component注解
 * @Author gaogba
 * @Date 2020/8/28 18:42
 * @Version 1.0
 */
@Component
public class UserRegisterListener implements ApplicationListener<UserRegisterEvent> {



    @Override
    public void onApplicationEvent(UserRegisterEvent event) {
        //发邮件
        System.out.println("正在发送邮件至： " + event.getUserInfo().getEmail());

        //发短信
        System.out.println("正在发短信到： " + event.getUserInfo().getPhoneNum());
    }
}
