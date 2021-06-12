package com.designpattern.chain.code;

import lombok.*;

/**
 * @author: Mr.Gao
 * @date: 2021/6/10 11:13
 * @description:
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest {
    /**
     * 采购金额
     */
    private double amount;
    /**
     * 采购单编号
     */
    private int number;
    /**
     * 采购目的
     */
    private String purpose;
}
