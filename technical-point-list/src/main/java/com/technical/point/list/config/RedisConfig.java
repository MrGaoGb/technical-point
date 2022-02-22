package com.technical.point.list.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

/**
 * @author: Mr.Gao
 * @date: 2022年02月22日 9:36
 * @description:
 */
@Configuration
public class RedisConfig {

    private final ObjectMapper objectMapper = getObjectMapper();

    /**
     * 缓存配置管理器
     *
     * @param redisConnectionFactory the redis connection factory
     * @return the cache manager
     * @author leiguoqing
     * @date 2020 -07-23 21:43:28
     */
    @Bean(name = "redisCacheManager")
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // 以锁写入的方式创建RedisCacheWriter对象
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory);
        // 设置 CacheManager 的 Value 序列化方式为 Jackson2JsonRedisSerialize, RedisCacheConfiguration 默认就是使用 StringRedisSerializer序列化key，
        // JdkSerializationRedisSerializer 序列化 value
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer());
        // 创建默认缓存配置对象
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().serializeValuesWith(pair);
        return new RedisCacheManager(writer, config);
    }


    /**
     * redisTemplate 序列化默认使用的 JdkSerializationRedisSerializer, 存储二进制字节码，
     * 这里改为使用 jackson2JsonRedisSerializer 自定义序列化
     *
     * @param factory
     * @return
     */
    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

        // 设置序列化
        @SuppressWarnings({"rawtypes", "unchecked"})
        // 使用 Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        StringRedisTemplate template = new StringRedisTemplate(factory);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 初始化 ObjectMapper
     *
     * @return
     */
    private ObjectMapper getObjectMapper() {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        return objMapper;
    }

    /**
     * Jackson 2 json redis serializer jackson 2 json redis serializer.
     *
     * @return the jackson 2 json redis serializer
     * @author leiguoqing
     * @date 2020 -07-25 14:31:07
     */
    private Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
        // 使用 Jackson2JsonRedisSerialize 替换默认序列化
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        return jackson2JsonRedisSerializer;
    }
}
