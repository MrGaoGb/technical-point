package com.technical.point.eventpublishaware.service;

import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author gaogba
 * @Date 2021/1/7 10:41
 * @Version 1.0
 */
@Service(value = "accountTwoService")
public class AccountTwoServiceImpl implements IAccountService{
    @Override
    public void add() {
        System.out.println("AccountTwoServiceImpl");
    }
}
