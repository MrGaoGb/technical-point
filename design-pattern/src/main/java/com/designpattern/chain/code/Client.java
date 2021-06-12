package com.designpattern.chain.code;

/**
 * @author: Mr.Gao
 * @date: 2021/6/10 11:23
 * @description:
 */
public class Client {
    public static void main(String[] args) {
        Approver director, manager, vicePresident, president, congress;
        //主任类：具体处理者
        director = new Director();
        //经理类：具体处理者（扩展类）
        manager = new Manager();
        //副董事长类：具体处理者
        vicePresident = new VicePresident();
        //董事长类：具体处理者
        president = new President();
        //董事会类：具体处理者
        congress = new Congress();

        //创建责任链
        director.setSuccessor(manager);
        manager.setSuccessor(vicePresident);
        vicePresident.setSuccessor(president);
        president.setSuccessor(congress);

        //创建采购单，并从主任开始处理
        PurchaseRequest pr1 = new PurchaseRequest(45000, 10001, "购买倚天剑");
        director.processRequest(pr1);

        PurchaseRequest pr5 = new PurchaseRequest(55000, 10001, "购买屠龙宝刀");
        director.processRequest(pr5);

        PurchaseRequest pr2 = new PurchaseRequest(80000, 10002, "购买《葵花宝典》");
        director.processRequest(pr2);

        PurchaseRequest pr3 = new PurchaseRequest(160000, 10003, "购买《金刚经》");
        director.processRequest(pr3);

        PurchaseRequest pr4 = new PurchaseRequest(800000, 10004, "购买桃花岛");
        director.processRequest(pr4);

        /**
         * console打印结果:
         * 主任审批采购单：10001，金额：45000.0元，采购目的：购买倚天剑。
         * 副董事长审批采购单：10002，金额：60000.0元，采购目的：购买《葵花宝典》。
         * 董事长审批采购单：10003，金额：160000.0元，采购目的：购买《金刚经》。
         * 召开董事会审批采购单：10004，金额：800000.0元，采购目的：购买桃花岛。
         */
    }
}
