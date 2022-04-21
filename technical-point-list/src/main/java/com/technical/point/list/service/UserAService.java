package com.technical.point.list.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author: Mr.Gao
 * @date: 2022年03月31日 9:59
 * @description:
 */
@Slf4j
@Service
public class UserAService implements UserIService {
    @Override
    public void hello(Integer status) {
        if (1 == status) {
            log.info("用户A begin");
            log.info("用户A say message");
            log.info("用户A end");
        }
    }
}
