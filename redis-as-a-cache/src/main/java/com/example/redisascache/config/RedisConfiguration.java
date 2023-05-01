/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.redisascache.config;

import java.time.Duration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * When we are using this configuration, we dont require to provide properties related to redis in application.yaml file.
 * Basically, below config.
 *
 * spring:
 *   redis:
 *     host: localhost
 *     port: 6379
 *     database: 0
 *     client-type: jedis
 *
 * also see classes
 * {@link org.springframework.boot.autoconfigure.cache.CacheConfigurations}
 * {@link org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration} (this is package private class thats why intellij showing red but you can still navigate in usual way),
 * above method class RedisCacheConfiguration#cacheManager
 * {@link RedisCacheManager},
 * above method class {@link RedisCacheManager#builder(RedisConnectionFactory connectionFactory)}
 *
 *
 * {@link RedisCacheConfiguration},
 * {@link org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration}
 *
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
public class RedisConfiguration {


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    /**
     * Notice @EnableConfigurationProperties(CacheProperties.class) at this class to enable cacheProperties
     * */
//    @Bean
//    RedisCacheManager redisCacheManager(RedisConnectionFactory jedisConnectionFactory, CacheProperties cacheProperties) {
//
//        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
//                .entryTtl(cacheProperties.getRedis().getTimeToLive());
//
//
//        return new RedisCacheManager(
//                RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory),
//                defaultCacheConfiguration);
//    }

    /* In this we are hardcoding ttl however, we can take it via application yaml. But difference would be we need to use our own
     *  custom property instead of using spring properties(which is the case in above configuration).
     *
     * When we are using this configuration, we also dont require to provide below properties related to redis in application.yaml file.
     * spring:
     *   cache:
     *     redis:
     *       time-to-live: 60000 # 10 seconds
     *  */
    @Bean
    RedisCacheManager redisCacheManager(RedisConnectionFactory jedisConnectionFactory) {

        RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.string()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                .entryTtl(Duration.ofMillis(60000));


        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(jedisConnectionFactory),
                defaultCacheConfiguration);
    }
}
