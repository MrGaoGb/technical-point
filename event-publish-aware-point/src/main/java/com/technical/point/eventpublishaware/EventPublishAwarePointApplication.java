package com.technical.point.eventpublishaware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 *
 * 背景描述：为了达到解耦的目的，一个类应当只做一件事，例如，用户进行注册时，需要创建注册相关的类，来完成注册事务。
 * 但是，如果注册成功时，还需要发送验证邮件，以及手机校验码，很多人为了方便，直接在注册类中将发送邮件和发送短信的
 * 代码一并写入。这样做会导致后面维护的困难，耦合性高。
 *
 * 我们可以通过事件发布来达到解耦的目的，事件的原理如下。
 *
 * 工作流程： 事件的发布者发布事件，事件的监听这对对应的事件进行监听，当监听到对应的事件时，就会触发调用相关的方法。
 * 因此，在事件处理中，事件是核心，是事件发布者和事件监听者的桥梁
 *
 * 例如：对应到上面的案例，我们可以在用户注册成功后发布事件，事件监听者监听到用户注册成功的事件后，触发发送邮件和发送短信的业务。
 * 由于要触发发送邮件和短信，因此要从事件发布者那里获取对应的邮件地址和手机号码，而这些信息是通过事件这个桥梁进行连接的，所以，
 * 事件中必须包含邮件地址和手机号码这两个属性。
 * 1.使用spring的方式进行操作实例
 *  访问链接：http://localhost:8888/listener/register?id=1&name=john&phoneNum=13111111111&email=123@163.com
 * 2.使用注解形式进行操作实例
 *  访问链接：http://localhost:8888/listener/activate?linkAddress=https://mvnrepository.com/artifact/org.springframework/spring-tx/5.2.4.RELEASE
 *       新：http://localhost:8888/listener/activate?linkAddress=http://www.163.com&phoneNo=18265535612
 * @author gaogba
 */
@SpringBootApplication(scanBasePackages = {"com.technical.point.eventpublishaware"})
public class EventPublishAwarePointApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventPublishAwarePointApplication.class, args);
    }

}
