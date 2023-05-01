/*
 *  Copyright (c) 2023 DMG
 *  All Rights Reserved Worldwide.
 *
 *  THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO DMG
 *  AND CONSTITUTES A VALUABLE TRADE SECRET.
 */
package com.redis.example.domain.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Piyush Kumar.
 * @since 29/04/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@RedisHash("MyProduct") // It is not required when using template.
public class ProductForTemplate implements Serializable {

    //    @Id // It is not required when using template.
    private String id;

//    @Indexed // It is not required when using template.
    private String sku;

    private String description;
    private String name;
}
