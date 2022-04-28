package com.technical.point.list.jmh.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 15:35
 * @description:
 */
@Data
public class AccountInf implements Serializable {
    private String accountName;
    private Integer status;//有效 无效
    private String accountNo;//账户编号
}
