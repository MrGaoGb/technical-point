package com.technical.point.list.jmh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.technical.point.list.jmh.entity.AccountInf;
import org.junit.Test;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;


/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 15:30
 * @description:JMH之性能测试工具
 */
public class JsonBenchmark {

    private static final String jsonStr = "{\"accountName\":\"高通\",\"status\":1,\"accountNo\":\"a2328b13-ac9c-433d-a59c-3b94ce19a01f\"}";

//    @Test
//    public void addPrint() throws Exception {
//        AccountInf accountInf = new AccountInf();
//        accountInf.setAccountNo(UUID.randomUUID().toString());
//        accountInf.setAccountName("高通");
//        accountInf.setStatus(1);
//        System.out.println(new ObjectMapper().writeValueAsString(accountInf));
//    }

    @Benchmark
    @Test
    public void test() throws Exception {
        final AccountInf accountInf = new ObjectMapper().readValue(jsonStr, AccountInf.class);
        System.out.println(accountInf);
        //TimeUnit.SECONDS.sleep(2);
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(JsonBenchmark.class.getSimpleName())
                .result("result.json")//数据json的跑分结果
                .resultFormat(ResultFormatType.JSON)
                .mode(Mode.AverageTime)
                .warmupIterations(2)
                .warmupTime(TimeValue.seconds(10))
                .warmupBatchSize(5)
                .measurementIterations(2)
                .measurementTime(TimeValue.seconds(5))
                .measurementBatchSize(5)
                .forks(1)
                .output("run.log")//输出打印日志
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
