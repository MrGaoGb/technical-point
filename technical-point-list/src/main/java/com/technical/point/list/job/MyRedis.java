package com.technical.point.list.job;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author: Mr.Gao
 * @date: 2022年01月10日 14:24
 * @description:
 */
public class MyRedis {


    public static void main(String[] args) {
        //队列或栈 可以左存右取
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.opsForList();
        
    }
}
