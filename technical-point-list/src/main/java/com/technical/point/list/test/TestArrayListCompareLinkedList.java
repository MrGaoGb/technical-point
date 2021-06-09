package com.technical.point.list.test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description ArrayList和LinkedList比较
 * @Author gaogba
 * @Date 2020/8/27 14:37
 * @Version 1.0
 */
public class TestArrayListCompareLinkedList {
    public static void main(String[] args) {
        List arrayList = new ArrayList(); //底层 数组 查询快
        arrayList.add(3,"C");


        List linkedList = new LinkedList();//底层 链表 插入快
        linkedList.add(3,'C');

    }
}
