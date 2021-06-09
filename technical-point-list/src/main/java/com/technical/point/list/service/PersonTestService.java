package com.technical.point.list.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @description: 测试使用@Autowired注解注入集合（List或Map）
 * @author: Mr.Gao
 * @create: 2021-04-21 16:33
 **/
@Service
public class PersonTestService {

    /**
     * 获取该接口bean的所有应用实例
     */
    @Autowired
    private List<PersonService> personServiceList;
    /**
     * 键值对存储Bean的实例，全部存放在Map中
     */
    @Autowired
    private Map<String, PersonService> personServiceMap;

    public void getBeanName(String name) {
        PersonService personService = personServiceMap.get(name);
        personService.doWork();
    }
}
