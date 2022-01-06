package com.pc.framework.spring.framework;

/**
 * @author: Mr.Gao
 * @date: 2021/6/13 16:53
 * @description:对Bean进行创建时候进行初始化操作,需要实现该接口即可
 */
public interface InitializingBean {

    void afterPropertiesSet();
}
