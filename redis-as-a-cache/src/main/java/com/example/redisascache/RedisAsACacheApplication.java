package com.example.redisascache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class RedisAsACacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisAsACacheApplication.class, args);
    }
}