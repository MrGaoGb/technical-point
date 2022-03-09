package com.technical.point.list.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Mr.Gao
 * @date: 2022年03月08日 20:29
 * @description:
 */
@Slf4j
@RestController
public class DistributeController {

    @Autowired
    private RedissonClient redissonClient;
    //采用非公平竞争
    private Lock lock = new ReentrantLock();
    //采用公平竞争
    //private Lock lock = new ReentrantLock(true);

    /**
     * 操作钱包扣减操作模拟
     *
     * @param agentCode
     * @return
     */
    @GetMapping("/user/optWalletDeduct")
    public String optWalletDeduct(@RequestParam String agentCode) {
        //v1(agentCode);
        v2(agentCode);
        //v3(agentCode);
        return "optWalletDeduct Success!";
    }

    /**
     * synchronized锁
     *
     * @param agentCode
     */
    private void v3(String agentCode) {
        synchronized (this) {
            log.info("(V3) 线程[{}] >> {}进行扣款操作!", Thread.currentThread().getName(), agentCode);
            log.info("线程[{}] >> 进行扣款操作!", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                log.info("(V3) @@@系统异常!");
                e.printStackTrace();
            }
            log.info("(V3) 线程[{}] >> 扣款完成操作!", Thread.currentThread().getName());
        }
    }

    /**
     * ReentrantLock锁
     *
     * @param agentCode
     */
    private void v2(String agentCode) {
        lock.lock();
        try {
            log.info("(V2) 线程[{}] >> {}进行扣款操作!", Thread.currentThread().getName(), agentCode);
            log.info("(V2) 线程[{}] >> 进行扣款操作!", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(3);
        } catch (Exception e) {
            log.info("(V2) 系统异常!");
        } finally {
            lock.unlock();
            log.info("(V2) 线程[{}] >> 扣款完成操作!", Thread.currentThread().getName());
        }
    }

    /**
     * Redisson分布式锁
     *
     * @param agentCode
     */
    private void v1(String agentCode) {
        RLock lock = redissonClient.getLock("TERMINAL_NO_ACTIVE_OPT_KEY_" + agentCode);
        try {
            if (lock.tryLock(10, 60, TimeUnit.SECONDS) && lock.isLocked()) {
                log.info("(V1) 线程[{}] >> {}进行扣款操作!", Thread.currentThread().getName(), agentCode);
                log.info("(V1) 线程[{}] >> 进行扣款操作!", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } else {
                lock = null;
                log.info("(V1) 线程[{}] >> 操作失败:请勿重复操作！reqParam:{}", Thread.currentThread().getName(), agentCode);
            }
        } catch (Exception ex) {
            log.info("(V1) 系统异常!");
        } finally {
            //释放锁资源
            if (null != lock) {
                lock.unlock();
            }
            log.info("(V1) 线程[{}] >> 扣款完成操作!", Thread.currentThread().getName());
        }
    }
}
