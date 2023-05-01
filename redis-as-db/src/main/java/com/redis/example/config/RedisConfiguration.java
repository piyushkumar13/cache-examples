/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.config;

import com.redis.example.domain.entity.ProductForTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Configuration
public class RedisConfiguration {

    /* Customization of redis configuration with default. */

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(jedisConnectionFactory());
//        return template;
//    }


    /* Customization of redis configuration. */

//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
//        configuration.setHostName("localhost");
//        configuration.setPort(6379);
//        configuration.setDatabase(0);
//
//        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
//        return factory;
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(jedisConnectionFactory());
//
//        redisTemplate.setHashKeySerializer(RedisSerializer.string()); // Represents serializer for hashKey which is id of ProductForTemplate object in this case.
//        redisTemplate.setHashValueSerializer(RedisSerializer.json()); // Represents serializer for hashValue which is ProductForTemplate object in this case.
//        redisTemplate.setKeySerializer(RedisSerializer.string()); // Represents serializer for key which is My_Product in this case
////        redisTemplate.setValueSerializer(RedisSerializer.json()); // this is not required. Represents serializer for key which is nothing in this case therefore not required.
//
//        redisTemplate.setEnableTransactionSupport(true);
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setHostName("localhost");
        configuration.setPort(6379);
        configuration.setDatabase(0);

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration);
        return factory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        redisTemplate.setHashKeySerializer(RedisSerializer.json()); // Represents serializer for hashKey which is id of ProductForTemplate object in this case.
        redisTemplate.setHashValueSerializer(RedisSerializer.json()); // Represents serializer for hashValue which is ProductForTemplate object in this case.
        redisTemplate.setKeySerializer(RedisSerializer.json()); // Represents serializer for key which is My_Product in this case
//        redisTemplate.setValueSerializer(RedisSerializer.json()); // this is not required. Represents serializer for key which is nothing in this case therefore not required.

        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
