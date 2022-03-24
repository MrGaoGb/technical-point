package com.technical.point.list.service;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;

/**
 * @description: 用户服务接口
 * @author: Mr.Gao
 * @create: 2021-04-21 16:27
 **/
public interface PersonService extends BeanNameAware, InitializingBean {

    void doWork();
}
