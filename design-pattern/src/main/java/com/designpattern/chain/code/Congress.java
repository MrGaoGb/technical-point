package com.designpattern.chain.code;

/**
 * @author: Mr.Gao
 * @date: 2021/6/10 11:22
 * @description:董事会类：具体处理者
 */
public class Congress extends Approver {

    //具体请求处理方法
    @Override
    public void processRequest(PurchaseRequest request) {
        //处理请求
        System.out.println("召开董事会审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
    }
}
