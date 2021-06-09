package com.technical.point.eventpublishaware.controller;

import com.technical.point.eventpublishaware.entity.UserInfo;
import com.technical.point.eventpublishaware.service.IAccountService;
import com.technical.point.eventpublishaware.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 用户中心UserController
 * @Author gaogba
 * @Date 2020/8/28 18:16
 * @Version 1.0
 */
@RestController
public class UserController {

    @Autowired
    private UserService userRegisterService;
    @Autowired
    private IAccountService accountTwoService;
    @Autowired
    private IAccountService accountOneService;

    /**
     * 账户1新增成功
     *
     * @return
     */
    @RequestMapping("/addRecord")
    public String addRecord() {
        //进行注册
        accountOneService.add();
        return "账户1新增成功";
    }

    /**
     * 账户2新增成功
     *
     * @return
     */
    @RequestMapping("/addRecord2")
    public String addRecord2() {
        //进行注册
        accountTwoService.add();
        return "账户2新增成功";
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public String register(@RequestBody UserInfo user) {
        //进行注册
        userRegisterService.register(user);
        return "[UserController]注册用户成功！";
    }

    /**
     * 用户激活并发送短信通知
     *
     * @param linkAddress
     * @return
     */
    @RequestMapping("/activate")
    public String activate(@RequestParam String linkAddress, @RequestParam String phoneNo) throws Exception {
        userRegisterService.activate(linkAddress, phoneNo);
        return "[UserController]用户激活成功！";
    }

}
