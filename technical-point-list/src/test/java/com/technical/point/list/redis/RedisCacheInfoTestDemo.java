package com.technical.point.list.redis;

import com.technical.point.list.TechnicalPointListApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author: Mr.Gao
 * @date: 2022年02月22日 9:42
 * @description:
 */
@SpringBootTest(classes = TechnicalPointListApplication.class)
@Slf4j
@RunWith(SpringRunner.class)
public class RedisCacheInfoTestDemo {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String cacheKey = "test:00000000000000001";
    private static final String cacheKey2 = "test:00000000000000002";

    @Before
    public void initData() {
        log.info("#######################开始缓存信息:@@@@@@@@@@@@@@@@@@@!");
        redisTemplate.opsForValue().set(cacheKey, "hello world! 123000", 30, TimeUnit.MINUTES);
    }

    /**
     * 缓存穿透:指的是查询一个数据库一定不存在的数据.
     * 正常步骤是:先从缓存中获取，如果缓存中存在则直接返回缓存信息值，若缓存值为空(key不存在或key已过期)，则需要从数据库中获取，
     * 若从DB中获取数据为空，则不放进缓存；非空则需要放进缓存
     */
    @Test
    public void testRedisPenetrate() {
        String value = redisTemplate.opsForValue().get(cacheKey2);
        if (Objects.nonNull(value)) {
            //表示先从缓存中获取 ，如果存在直接返回!
            log.info("缓存中存在! 缓存信息为:{}", value);
            return;
        }
        log.info("缓存中未获取到信息值@@@@!");
        //表示缓存中不存在，需要从DB上获取
        String dbRespData = getDataFromDbConnect(10);
        if (Objects.nonNull(dbRespData)) {
            //如果DB中存在就缓存在cache中 否则就直接返回
            redisTemplate.opsForValue().set(cacheKey, "hello world! 123000", 30, TimeUnit.SECONDS);
            log.info("缓存信息至cache中......");
        } else {
            log.info("数据库返回信息为空 不用缓存@@@!");
        }
        return;
    }

    /**
     * 缓存穿透问题描述:在高频访问不存在key的前提下，会直接访问DB，到达一定量之后会造成数据服务宕机。
     * 优化点:
     * 针对这种情况下，对数据库不存在的空值，也进行缓存，只是缓存的时间更久一些。
     */
    @Test
    public void testPenetrateOptimize() {
        String value = redisTemplate.opsForValue().get(cacheKey2);
        if (Objects.nonNull(value)) {
            //表示先从缓存中获取 ，如果存在直接返回!
            log.info("缓存中存在! 缓存信息为:{}", value);
            return;
        }
        log.info("缓存中未获取到信息值@@@@!");
        //表示缓存中不存在，需要从DB上获取
        String dbRespData = getDataFromDbConnect(10);
        if (Objects.nonNull(dbRespData)) {
            //如果DB中存在就缓存在cache中 否则就直接返回 cache 30分钟
            redisTemplate.opsForValue().set(cacheKey, "hello world! 123000", 30, TimeUnit.MINUTES);
            log.info("缓存信息至cache中......");
        } else {
            //数据库为空 缓存时间时间设置为60s(缩短缓存时间)
            redisTemplate.opsForValue().set(cacheKey, null, 60, TimeUnit.SECONDS);
            log.info("数据库返回信息为空 不用缓存@@@!");
        }
        return;
    }

    /**
     * 缓存雪崩：指得是在某一时间段，缓存集中过期失效。
     * 原因之一：例如淘宝、京东等大型互联网公司的应用产品，在某一时间节点(双十二或双十一)要开始一波商品抢购下单，在抢购的时间节点所有的商品缓存信息
     * 均失效了，此时所有的请求压力会全部集中到数据库上，可能会导致数据库服务器宕机掉。
     * 原因之二：缓存服务器节点都宕机了，那么此时数据库的请求压力也会急剧增加，甚至达到一定成功会导致数据库服务器宕机掉。
     * <p>
     * 解决办法：
     * 一般采用不同分类商品采用不同周期，同一分类中的商品，加上一个随机因子，尽可能的分散缓存过期时间
     */
    public void testSnowSlide() {
        String value = redisTemplate.opsForValue().get(cacheKey2);
        if (Objects.nonNull(value)) {
            //表示先从缓存中获取 ，如果存在直接返回!
            log.info("缓存中存在! 缓存信息为:{}", value);
            return;
        }
        log.info("缓存中未获取到信息值@@@@!");
        //表示缓存中不存在，需要从DB上获取
        String dbRespData = getDataFromDbConnect(10);
        if (Objects.nonNull(dbRespData)) {
            //如果DB中存在就缓存在cache中 否则就直接返回 cache 30分钟
            Long storeTime = null;
            if (dbRespData.equals("图书")) {
                //冷门
                Integer randomMillSecond = new Random().nextInt(600);
                storeTime = 30 + randomMillSecond.longValue();
                redisTemplate.opsForValue().set(cacheKey, "hello world! 123000 图书@", storeTime, TimeUnit.SECONDS);
            }
            if (dbRespData.equals("计算机")) {
                //热门
                Integer randomMillSecond = new Random().nextInt(3600);
                storeTime = 30 + randomMillSecond.longValue();
                redisTemplate.opsForValue().set(cacheKey, "hello world! 123001 计算机@", storeTime, TimeUnit.SECONDS);
            }
            log.info("缓存信息至cache中......");
        } else {
            //数据库为空 缓存时间时间设置为60s(缩短缓存时间)
            redisTemplate.opsForValue().set(cacheKey, null, 60, TimeUnit.SECONDS);
            log.info("数据库返回信息为空 不用缓存@@@!");
        }
        return;
    }

    /**
     * 缓存击穿:指一个KEY非常热点，在不停的扛着高并发，大并发集中对这一个点进行访问，当这个KEY失效的瞬间，就会穿破缓存，直击数据库，就像在屏幕前凿
     * 开了一个洞。
     * 解决思路：对这些热点的KEY会做成永不过期来设置。
     */
    @Test
    public void testSnowBreakDown() {
    }

    /**
     * 模拟从数据库中获取数据
     * 获取numId以内的范围数
     *
     * @return
     */
    private String getDataFromDbConnect(Integer numId) {
        log.info("####对数据库进行了一次查询操作@@@@!");
        int number = new Random().nextInt(numId) + 1;
        if (number % 2 == 0) {
            return "hello world! 123000";
        } else {
            return null;
        }
    }

}
