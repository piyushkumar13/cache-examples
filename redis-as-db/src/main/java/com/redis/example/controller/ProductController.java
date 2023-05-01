/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.redis.example.domain.entity.ProductForRepo;
import com.redis.example.domain.entity.ProductForTemplate;
import com.redis.example.repository.ProductRepository;
import com.redis.example.repository.ProductRepositoryUsingTemplate;
import java.util.List;
import lombok.Data;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Data
@RestController
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductRepositoryUsingTemplate productRepositoryUsingTemplate;



    /*  Operations on ProductForRepo using productRepository  */


    @RequestMapping(path = "/products", method = RequestMethod.GET)
    Iterable<ProductForRepo> getProducts(){

        return productRepository.findAll();
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    ProductForRepo getProduct(@PathVariable("id") int id){

        return productRepository.findById(id).orElse(ProductForRepo.builder().description("default").sku("default").id(0).name("default").build());
    }


    @RequestMapping(path = "/products/{id}", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    ProductForRepo creatProduct(@PathVariable("id") int id, @RequestBody ProductForRepo product){

        return productRepository.save(product);
    }

    @RequestMapping(path = "/products/description", method = RequestMethod.GET)
    ProductForRepo getProductByDesc(@RequestParam("value") String descriptionValue){

        /* I dont have index on description so we cannot retrieve result by description. In this case, we will get default object. */
        return productRepository.findByDescription(descriptionValue).orElse(ProductForRepo.builder().description("default").sku("default").id(0).name("default").build());
    }

    @RequestMapping(path = "/products/sku", method = RequestMethod.GET)
    ProductForRepo getProductBySku(@RequestParam("value") String skuValue){

        /* I have index on sku so we can retrieve result by sku. In this case, we will get object corresponding to provided sku. */
        return productRepository.findBySku(skuValue).orElse(ProductForRepo.builder().description("default").sku("default").id(0).name("default").build());
    }



    /*  Operations on ProductForTemplate using productRepositoryUsingTemplate  */


    @RequestMapping(path = "/products/usingtemplate", method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    void creatProductUsingTemplate(@RequestBody ProductForTemplate product){

        productRepositoryUsingTemplate.saveProduct(product);
    }

    @RequestMapping(path = "/products/usingtemplate/{id}", method = RequestMethod.GET)
    ProductForTemplate getProductUsingTemplate(@PathVariable("id") String id){

         return productRepositoryUsingTemplate.getProduct(id);
    }

    @RequestMapping(path = "/products/usingtemplate", method = RequestMethod.GET)
    List<ProductForTemplate> getProductsUsingTemplate(){

        return productRepositoryUsingTemplate.getProducts();
    }
}
