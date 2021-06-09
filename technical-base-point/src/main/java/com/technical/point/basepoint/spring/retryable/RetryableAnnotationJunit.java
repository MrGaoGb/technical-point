package com.technical.point.basepoint.spring.retryable;

import com.technical.point.basepoint.entity.UserInfoPo;
import com.technical.point.basepoint.mapper.UserMapper;
import com.technical.point.basepoint.spring.deprecated.AnnotationDeprecatedJunit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description @Retryable:重处理机制
 * @Author gaogba
 * @Date 2020/9/4 20:08
 * @Version 1.0
 */
@Slf4j
public class RetryableAnnotationJunit {

    @Autowired
    private UserMapper userMapper;


    /**
     * 尝试5次
     */
    private static final int TRY_COUNT = 5;
    /**
     * 等待1s
     */
    private static final long WAIT_MILLS = 1000;

    /**
     * 自定义重处理机制
     *
     * @param payRecvableIdList
     * @return
     */
    private List<UserInfoPo> queryRecvablesByPayRecvableIds(List<String> payRecvableIdList) {
        // 1、构造查询条件
        if (CollectionUtils.isEmpty(payRecvableIdList)) {
            return new ArrayList<>();
        }
        // 2、重试策略实现,TRY_COUNT是重试次数;WAIT_MILLS是等待毫秒数
        List<UserInfoPo> queryResult = new ArrayList<>();
        for (int i = 1; i <= TRY_COUNT; i++) {
            try {
                queryResult = userMapper.selectAll();
            } catch (Exception e) {
                log.info("try query---");
                if (i == TRY_COUNT) {
                    log.error("查询收款单重试失败");
                    throw new RuntimeException("查询收款单,重试失败");
                }
                try {
                    Thread.sleep(WAIT_MILLS);
                } catch (Exception ex) {
                    log.error("sleep error");
                }
                continue;
            }
            break;
        }
        return queryResult;
    }

    /**
     * 详解描述：
     * @Retryable注解
     * 被注解的方法发生异常时会重试
     * value:指定发生的异常进行重试
     * include:和value一样，默认空，当exclude也为空时，所有异常都重试
     * exclude:指定异常不重试，默认空，当include也为空时，所有异常都重试
     * maxAttemps:重试次数，默认3
     * backoff:重试补偿机制，默认没有
     *
     * @Backoff注解
     * delay:指定延迟后重试
     * multiplier:指定延迟的倍数，比如delay=5000l,multiplier=2时，第一次重试为5秒后，第二次为10秒，第三次为20秒
     *
     * @Recover
     * 当重试到达指定次数时，被注解的方法将被回调，可以在该方法中进行日志处理。需要注意的是发生的异常和入参类型一致时才会回调
     */
    @Retryable(value = Exception.class,backoff = @Backoff(value = 3000L))
    public void query() {
        log.info("execute query,time={}", System.currentTimeMillis());
        if (true) {
            throw new RuntimeException("error");
        }
        AnnotationDeprecatedJunit annotationDeprecatedJunit = new AnnotationDeprecatedJunit();

    }
}
