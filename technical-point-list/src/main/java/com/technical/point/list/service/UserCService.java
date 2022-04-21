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
public class UserCService implements UserIService {
    @Override
    public void hello(Integer status) {
        if (3 == status) {
            log.info("用户C begin");
            log.info("用户C say message");
            log.info("用户C end");
        }
    }
}
