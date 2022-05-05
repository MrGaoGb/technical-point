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

import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * @author: Mr.Gao
 * @date: 2022年04月28日 15:30
 * @description:JMH之性能测试工具
 */
@BenchmarkMode({Mode.Throughput})
//@OutputTimeUnit(TimeUnit.SECONDS) 输出打印结果
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@Fork(1)
@Threads(4)
public class JsonBenchmark {

    private static final String jsonStr = "{\"accountName\":\"高通\",\"status\":1,\"accountNo\":\"a2328b13-ac9c-433d-a59c-3b94ce19a01f\"}";

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        ObjectMapper GLOBAL_MAP = new ObjectMapper();
        ThreadLocal<ObjectMapper> GLOBAL_MAP_THREAD = new ThreadLocal<>();
    }


//    @Test
//    public void addPrint() throws Exception {
//        AccountInf accountInf = new AccountInf();
//        accountInf.setAccountNo(UUID.randomUUID().toString());
//        accountInf.setAccountName("高通");
//        accountInf.setStatus(1);
//        System.out.println(new ObjectMapper().writeValueAsString(accountInf));
//    }

    @Benchmark
    public Map globalTest(BenchmarkState state) throws Exception{
        Map map = state.GLOBAL_MAP.readValue(jsonStr, Map.class);
        return map;
    }

    @Benchmark
    public Map globalTestThreadLocal(BenchmarkState state) throws Exception{
        if(null == state.GLOBAL_MAP_THREAD.get()){
            state.GLOBAL_MAP_THREAD.set(new ObjectMapper());
        }
        Map map = state.GLOBAL_MAP_THREAD.get().readValue(jsonStr, Map.class);
        return map;
    }


    @Benchmark
    public AccountInf test() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        AccountInf accountInf = objectMapper.readValue(jsonStr, AccountInf.class);
        System.out.println(accountInf);
        return accountInf;
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(JsonBenchmark.class.getSimpleName())
                .result("result.json")//数据json的跑分结果
                .resultFormat(ResultFormatType.JSON)
                .output("run.log")//输出打印日志
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
    }
}
