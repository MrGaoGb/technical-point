package com.technical.point.list.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author: Mr.Gao
 * @date: 2021/7/26 16:18
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @GetMapping("/get")
    public Map<String, Object> getUserInfo(@RequestParam Map<String, Object> map) {
        return map;
    }

    @PostMapping("/editRate")
    public String editRate(@RequestBody RateInfo rateInfo) {
        return rateInfo.toString();
    }

    @Data
    public class RateInfo {
        private BigDecimal cFeeRate;
        private BigDecimal cfeeRate;
        private String userName;
    }
}
