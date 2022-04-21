package com.technical.point.list.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author: Mr.Gao
 * @date: 2022年03月31日 10:00
 * @description:
 */
@Slf4j
@Component
public class UserBService implements UserIService {
    @Override
    public void hello(Integer status) {
        if (2 == status) {
            log.info("用户B begin");
            log.info("用户B say message");
            log.info("用户B end");
        }
    }
}
