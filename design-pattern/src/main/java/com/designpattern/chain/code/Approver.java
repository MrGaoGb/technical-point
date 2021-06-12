package com.designpattern.chain.code;

/**
 * @author: Mr.Gao
 * @date: 2021/6/10 11:18
 * @description:
 */
public abstract class Approver {
    /**
     * 定义后继处理对象
     */
    protected Approver successor;

    /**
     * 设置后继者
     *
     * @param successor
     */
    public void setSuccessor(Approver successor) {
        this.successor = successor;
    }

    /**
     * 抽象请求处理方法
     *
     * @param request
     */
    public abstract void processRequest(PurchaseRequest request);
}
