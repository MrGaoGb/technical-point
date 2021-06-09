package com.technical.point.eventpublishaware.service;

import com.technical.point.eventpublishaware.entity.UserInfo;
import com.technical.point.eventpublishaware.event.UserRegisterEvent;
import org.apache.catalina.User;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 逻辑处理层
 * @Author gaogba
 * @Date 2020/8/28 18:17
 * @Version 1.0
 */
@Service
public class UserService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    private ConcurrentHashMap<String,Object> concurrentHashMap = new ConcurrentHashMap<>();
    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public boolean register(UserInfo user) {

        //用户注册
        System.out.println("[service]用户[" + user + "]注册中......！");

        //消息发布
        applicationEventPublisher.publishEvent(new UserRegisterEvent(this, user));

        System.out.println("[service]用户[" + user + "]注册成功！");
        return true;
    }


    /**
     * 用户激活  concurrentHashMap:用于控制并发量避免重复提交
     *
     * @param linkAddress
     * @return
     */
    public boolean activate(String linkAddress,String phoneNo) throws Exception {
        //避免重复提交
        if (concurrentHashMap.containsKey(phoneNo)){
            throw new Exception("您已经发起请求，请勿重复提交！");
        }

        concurrentHashMap.put(phoneNo,Byte.MIN_VALUE);
        //用户激活
        System.out.println("[service]用户[" + linkAddress + "]激活成功！");
        UserInfo info = new UserInfo();
        info.setActivateLinkAddress(linkAddress);
        info.setPhoneNum(phoneNo);
        applicationEventPublisher.publishEvent(new PayloadApplicationEvent<UserInfo>(this,info));
        System.out.println("[service]用户[" + linkAddress + "]激活中.....！");
        return true;
    }

    /**
     * 注意事项
     * 1、监听器方法中一定要try-catch异常，否则会造成发布事件(有事物的)的方法进行回滚
     * 2、可以使用@Order注解来控制多个监听器的执行顺序，@Order传入的值越小，执行顺序越高
     * 3、对于需要进行事物监听或不想try-catch runtime异常，可以使用@TransactionalEventListener注解
     *
     * 修改监听事物的范围：@TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
     * @param event
     */
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void listenerActivate(PayloadApplicationEvent<UserInfo> event){
        System.out.println("========开始激活=======");
        //链接地址
        concurrentHashMap.remove(event.getPayload().getPhoneNum());
        System.out.println("正在跳转至： " + event.getPayload().getActivateLinkAddress());
        System.out.println("========激活成功!=======");
    }



    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
