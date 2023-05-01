/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.example.redisascache.repository;

import com.example.redisascache.domain.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Piyush Kumar.
 * @since 01/05/23.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
