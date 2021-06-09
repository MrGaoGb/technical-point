package com.technical.point.list.test;


import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.function.*;

/**
 * @Description Optional工具类学习
 * @Author gaogba
 * @Date 2020/12/22 15:08
 * @Version 1.0
 */
public class TestLocalDateTimeClass {

    public static void main(String[] args) {
        //测试时间
        testGetTime();

        //测试Stream
        testStream();

        //测试Func
        testFunction();

        //测试Consumer
        testConsumer();

        //测试Predicate
        testPredicate();

        //测试Supplier
        testSupplier();
    }

    /**
     * 获取时间:
     */
    public static void testGetTime() {
        /**
         * 1 把Date转化为LocalDateTime
         */
        Date date = new Date();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        System.out.println("date:" + date);
        String format = localDateTime.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("format:" + format);
        /**
         * 2 把LocalDateTime转化为Date
         */
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date from = Date.from(instant);
        System.out.println("from:" + from);
    }

    /**
     * 练习Stream流
     */
    public static void testStream() {
        ObjDoubleConsumer<String> doubleConsumer = (s, d) -> System.out.println(s + d);
        doubleConsumer.accept("张三:", 333.64);
    }

    /**
     * 练习Function：接收一个参数并返回结果的函数
     */
    public static void testFunction() {
        Function<String, String> fun1 = (param) -> param + " HELLO FUNCTION1!";
        Function<String, String> fun2 = (param) -> param + " HELLO FUNCTION2!";
        System.out.println("fun1:" + appendStr(fun1, "TIM"));
        System.out.println("fun2:" + appendStr(fun2, "TOM"));

        BiFunction<Integer, Integer, Integer> biFunc = (p1, p2) -> p1 * p2;
        BiFunction<Integer, Integer, Integer> biFunc1 = (p3, p4) -> p3 - p4;

        System.out.println("biFunc 结果计算:" + calculateResult(biFunc, 30, 20));
        System.out.println("biFunc1 结果计算:" + calculateResult(biFunc1, 30, 20));
    }

    /**
     * 练习Consumer: 提供一个T类型的参数，不返回执行结果
     */
    public static void testConsumer() {
        StringBuilder sb = new StringBuilder("JAVA ");
        Consumer<StringBuilder> consumer = (str) -> str.append("Jack!");
        consumer.accept(sb);

        System.out.println("Consumer:" + sb.toString());

    }

    /**
     * 测试Predicate:对给定的参数，返回一个boolean类型的值
     */
    public static void testPredicate() {

        Predicate<Integer> predicate = (num) -> num.equals(1);
        System.out.println("Predicate:" + predicate.test(1));

        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a.equals(b);
        System.out.println("BiPredicate:" + biPredicate.test(1, 1));
    }

    /**
     * 测试Supplier:无需提供输入参数，返回一个T类型的参数
     */
    public static void testSupplier() {

        Supplier<String> supplier = () -> "HELLO SUPPLIER!";
        System.out.println("supplier:" + supplier.get());
    }

    /**
     * BiFunction函数应用
     *
     * @param biFunc
     * @param param1
     * @param param2
     * @return
     */
    public static Integer calculateResult(BiFunction<Integer, Integer, Integer> biFunc, Integer param1, Integer param2) {
        return biFunc.apply(param1, param2);
    }

    /**
     * Function函数应用
     * 字符串拼接
     *
     * @param fun
     * @param inputParam
     * @return
     */
    public static String appendStr(Function<String, String> fun, String inputParam) {
        return fun.apply(inputParam);
    }
}
