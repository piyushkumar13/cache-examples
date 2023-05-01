/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("Product")
public class ProductForRepo {

    @Id // optional if field name is id itself.
    private int id;

    @Indexed // use to index field for faster access.
    private String sku;

    private String description;
    private String name;
}
