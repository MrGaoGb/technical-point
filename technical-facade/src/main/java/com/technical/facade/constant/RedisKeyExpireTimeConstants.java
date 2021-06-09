package com.technical.facade.constant;

/**
 * @Description redis的key值有效缓存时间
 * @Author gaogba
 * @Date 2020/9/22 22:27
 * @Version 1.0
 */
public class RedisKeyExpireTimeConstants {

    /**
     * sessionId的缓存有效期;半个小时(单位：s)
     */
    public static final long SESSION_KEY_CACHE_EXPIRE_TIME = 1 * 30 * 60;

    /**
     * 用户登录时间延迟6个小时（单位：ms）
     */
    public static final long LOGIN_EXPIRE_TIME = 6 * 60 * 60 * 1000;

    /**
     * REDISSION锁的等待时间为10s（单位: ms）
     */
    public static final long REDISSION_LOCK_WAIT_TIME = 10000;

    /**
     * REDISSION锁的租赁时间60s（单位: ms）
     */
    public static final long REDISSION_LOCK_LEASE_TIME = 60000;
}
