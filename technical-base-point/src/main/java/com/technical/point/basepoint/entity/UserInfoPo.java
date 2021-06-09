package com.technical.point.basepoint.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Description TODO
 * @Author gaogba
 * @Date 2020/9/4 20:41
 * @Version 1.0
 */
@Getter
@Setter
@ToString
public class UserInfoPo {

    private Long id;

    private String userName;

    private String password;

    private Integer validStatus;
}
