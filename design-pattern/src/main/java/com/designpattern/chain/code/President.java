package com.designpattern.chain.code;

/**
 * @author: Mr.Gao
 * @date: 2021/6/10 11:21
 * @description:董事长类：具体处理者
 */
public class President extends Approver {

    //具体请求处理方法
    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() < 500000) {
            // 处理请求
            System.out.println("董事长" + "审批采购单：" + request.getNumber() + "，金额：" + request.getAmount() + "元，采购目的：" + request.getPurpose() + "。");
        } else {
            // 转发请求
            this.successor.processRequest(request);
        }
    }
}
