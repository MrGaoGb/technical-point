package com.pc.framework.spring.framework;

/**
 * @author: Mr.Gao
 * @date: 2021/6/13 17:16
 * @description:
 */
public interface BeanPostProcessor {

    /**
     * 初始化前
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * 初始化后
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);
}
