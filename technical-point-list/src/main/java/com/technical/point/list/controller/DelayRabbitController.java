package com.technical.point.list.controller;

import com.technical.point.list.rabbit.RabbitSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Mr.Gao
 * @date: 2022年03月09日 14:59
 * @description:
 */
@RestController
public class DelayRabbitController {

    @Autowired
    private RabbitSender rabbitSender;

    /**
     * 使用延时队列延缓消息的发送
     * <p>
     * 在消息过期之后会从队列prod_no_active_queue转发到dead_letter_queue队列，实现了消息延时消费
     * 一、TTL分为两种：
     * 1、TTL:设置 队列 过期时间实现延时消费
     * 2、TTL:设置 消息 过期时间实现延时消费
     * 二、插件实现
     * </p>
     *
     * @param msg
     */
    @GetMapping("/delay/logicReqParam")
    public void logicReqParam(@RequestParam String msg) {
        rabbitSender.send(msg);
    }
}
