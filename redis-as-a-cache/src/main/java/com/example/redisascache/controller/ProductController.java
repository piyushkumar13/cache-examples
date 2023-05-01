/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.redisascache.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.example.redisascache.domain.entity.ProductEntity;
import com.example.redisascache.repository.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Data
@Slf4j
@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    @Cacheable({"products", "productEntities"})
    public Iterable<ProductEntity> getProducts(){

        log.info("Fetching data from datastore");

        return productRepository.findAll();
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    @Cacheable(value = {"productPerId"}, key = "#id")
    public ProductEntity getProduct(@PathVariable("id") Integer id){

        log.info("Fetching data by id, id={}", id);
        return productRepository.findById(id).orElse(ProductEntity.builder().description("default").sku("default").id(0).name("default").build());
    }

    @RequestMapping(path = "/products/{id}/withConditions/{name}", method = RequestMethod.GET)
    @Cacheable(value = {"productPerId"}, key = "{#id.concat(#name)}")
    public ProductEntity getProductAndCacheWithCondition(@PathVariable("id") String id, @PathVariable("name") String name){

        log.info("Fetching data by id, id={}", id);
        return productRepository.findById(Integer.valueOf(id)).orElse(ProductEntity.builder().description("default").sku("default").id(0).name("default").build());
    }


    @CacheEvict(cacheNames = "productPerId", allEntries = true)
    @RequestMapping(path = "/products", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public ProductEntity creatProduct(@RequestBody ProductEntity product){

        return productRepository.save(product);
    }

    @CachePut(cacheNames = {"productPerId"}, key = "#product.id")
    @RequestMapping(path = "/products", method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public ProductEntity updateProduct(@RequestBody ProductEntity product){

        return productRepository.save(product);
    }
}
