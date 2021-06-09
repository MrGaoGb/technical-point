package com.technical.point.eventpublishaware.event;

import com.technical.point.eventpublishaware.entity.UserInfo;
import org.springframework.context.ApplicationEvent;

/**
 * @Description 事件类：由于需要发送邮件和短信，因此需要封装邮件地址和手机号码到事件类中，为了方便，直接将用户注册填写的信息封装成User类，
 *                  并将该类直接作为事件的属性
 * @Author gaogba
 * @Date 2020/8/28 18:29
 * @Version 1.0
 */
public class UserRegisterEvent<U> extends ApplicationEvent {

    private final UserInfo userInfo;


    public UserRegisterEvent(Object source,UserInfo userInfo) {
        super(source);
        this.userInfo = userInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }


}
