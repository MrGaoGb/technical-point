package com.technical.point.list.easyexcel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: Mr.Gao
 * @date: 2021/12/23 17:12
 * @description:
 */
@Data
@AllArgsConstructor
public class Student implements Serializable {
    @JsonProperty("用户名称")
    private String userName;
    @JsonProperty("年龄")
    private Integer age;
    @JsonProperty("密码")
    private String password;
}
