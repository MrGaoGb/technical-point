package com.technical.point.eventpublishaware.dao;

import org.springframework.stereotype.Repository;

/**
 * @Description 用户信息Dao层
 * @Author gaogba
 * @Date 2020/12/23 22:01
 * @Version 1.0
 */
@Repository
public interface UserInfoDao {

    /**
     * 添加用户
     */
   default void addUser(){
       System.out.println("添加用户!");
   }

    /**
     * 删除用户
     */
    default void deleteUserById(){
        System.out.println("删除用户信息!");
    }

    /**
     * 修改用户信息
     */
    default void updateUser(){
        System.out.println("修改户信息!");
    }
}
