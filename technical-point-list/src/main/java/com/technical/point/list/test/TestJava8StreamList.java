package com.technical.point.list.test;


import com.technical.point.list.entity.Apple;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description java8用例实例-使用lambda表达式
 * @Author gaogba
 * @Date 2020/8/24 10:24
 * @Version 1.0
 */
public class TestJava8StreamList {

    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果",new BigDecimal("3.25"),10);
        Apple apple2 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple12 = new Apple(1,"苹果12",new BigDecimal("8.35"),20);
        Apple apple3 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple4 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

        appleList.add(apple1);
        appleList.add(apple2);
        appleList.add(apple12);
        appleList.add(apple3);
        appleList.add(apple4);

        /**
         * 1.分组 List里面的对象元素，
         * 以某个属性来分组，例如，以id分组，将id相同的放在一起
         */
        Map<Integer, List<Apple>> collect = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        //System.out.printf("groupBy:"+collect);

        /**
         * 2 : List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> collectMap = appleList.stream().collect(Collectors.toMap(Apple::getId,  apple -> apple,(k1, k3) -> k1));
//        System.out.println("List -> Map :"+collectMap);

        /**
         * 过滤出符合条件的数据
         */
        List<Apple> list = appleList.stream().filter(apple -> {
            return apple.getName().equals("荔枝");
        }).collect(Collectors.toList());
        System.out.println("filter:"+list);

        /**
         * 过滤求和
         */
        int filterTotalCount = appleList.stream().filter(a -> a.getName().equals("苹果")).mapToInt(a -> a.getNum()).sum();
        System.out.println(filterTotalCount);
        /**
         * 求和
         */
        BigDecimal totalMoney = appleList.stream().map(Apple::getMoney).reduce(BigDecimal.ZERO, BigDecimal::add);
//        System.out.println("totalMoney:"+totalMoney);
        /**
         * T identity:表示初始化值
         */
        Integer reduce = appleList.stream().map(Apple::getNum).reduce(0, Integer::sum);
        System.out.println(reduce);

        /**
         * 遍历
         */
        System.out.println("=================================================");
        appleList.stream().forEachOrdered(apple -> System.out.println(apple));
        appleList.stream().forEach(apple -> System.out.println(apple));

        /**
         * ========Stream流的map使用=========
         * map方法用于映射每个元素到对应的结果，一对一。
         */
        //示例一：转换大写
        List<String> list3 = Arrays.asList("zhangSan", "liSi", "wangWu");
        System.out.println("转换之前的数据:" + list3);
        List<String> list4 = list3.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list4);
        //示例二：转换数据类型
        List<String> list5 = Arrays.asList("1", "2", "3");
        System.out.println("转换之前的数据:" + list5);
        List<Integer> list6 = list5.stream().map(Integer::valueOf).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list6);
        //示例三：获取平方
        Integer[] a = new Integer[]{1,2,3,4,5,6};
        List<Integer> list7 = Arrays.asList(a);
        System.out.println("转换之前的数据:" + list7);
        List<Integer> list8 = list7.stream().map((m) -> m * m).collect(Collectors.toList());
        System.out.println("转换之后的数据:" + list8);

    }

    public String testQuery(String likeName) throws IOException {
        return "select * from cp_user_info where user_name like "+likeName;
    }

}
