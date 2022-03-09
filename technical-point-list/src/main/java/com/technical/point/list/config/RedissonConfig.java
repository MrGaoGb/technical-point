package com.technical.point.list.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson 配置类 Created on 2018/6/19
 */
@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private String port;

    @Value("${spring.redis.password}")
    private String password;

    @Bean
    public RedissonClient getRedisson() {
        org.redisson.config.Config config = new org.redisson.config.Config();
        config.useSingleServer().setAddress("redis://" + host + ":" + port);//.setPassword(password);
        // 添加主从配置
        // config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new
        // String[]{"",""});

        return Redisson.create(config);
    }

}
