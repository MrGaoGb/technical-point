package com.gaogba.thread.entity;

/**
 * @author: Mr.Gao
 * @date: 2021/6/26 17:39
 * @description:
 */
public class User {
    private Integer age;

    private String userName;

    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
