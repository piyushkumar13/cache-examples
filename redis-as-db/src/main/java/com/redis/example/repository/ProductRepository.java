/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.repository;

import com.redis.example.domain.entity.ProductForRepo;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
public interface ProductRepository extends CrudRepository<ProductForRepo, Integer> {

    Optional<ProductForRepo> findByDescription(String description);

    Optional<ProductForRepo> findBySku(String sku);
}
