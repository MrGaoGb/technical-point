package com.technical.point.list.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author: Mr.Gao
 * @date: 2021/7/26 16:18
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/get")
    public Map<String, Object> getUserInfo(@RequestParam Map<String, Object> map) {
        return map;
    }
}
