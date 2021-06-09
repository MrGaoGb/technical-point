package com.technical.point.eventpublishaware.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

/**
 * @Description 实体类
 * @Author gaogba
 * @Date 2020/8/28 18:30
 * @Version 1.0
 */
@Getter
@Setter
public class UserInfo {
    private String name;
    private String phoneNum;
    private String email;

    private String activateLinkAddress;//激活地址

    @Override
    public String toString() {
        return new StringJoiner(", ", UserInfo.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("phoneNum='" + phoneNum + "'")
                .add("email='" + email + "'")
                .add("activateLinkAddress='" + activateLinkAddress + "'")
                .toString();
    }
}
