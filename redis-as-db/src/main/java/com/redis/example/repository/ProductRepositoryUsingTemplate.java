/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.repository;

import com.redis.example.domain.entity.ProductForTemplate;
import java.util.List;
import lombok.Data;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Piyush Kumar.
 * @since 30/04/23.
 */
@Data
@Repository
public class ProductRepositoryUsingTemplate {

    private static final String KEY = "My_Product";

    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    public ProductRepositoryUsingTemplate(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }


    public void saveProduct(ProductForTemplate product){

        hashOperations.put(KEY, product.getId(), product);
    }

    public ProductForTemplate getProduct(String id){

        return (ProductForTemplate) hashOperations.get(KEY, id);
    }

    public List<ProductForTemplate> getProducts(){

        return hashOperations.values(KEY);
    }
}
