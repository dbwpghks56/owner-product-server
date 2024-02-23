package com.owner.product.domain.product.dto.response;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.owner.product.domain.product.dto.response.QProductResponseDto_FindWithPaging is a Querydsl Projection type for FindWithPaging
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductResponseDto_FindWithPaging extends ConstructorExpression<ProductResponseDto.FindWithPaging> {

    private static final long serialVersionUID = -1914369906L;

    public QProductResponseDto_FindWithPaging(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> category, com.querydsl.core.types.Expression<Long> sellingPrice, com.querydsl.core.types.Expression<Long> price, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<java.time.LocalDateTime> expiredTime, com.querydsl.core.types.Expression<com.owner.product.domain.product.enums.ProductEnum> size) {
        super(ProductResponseDto.FindWithPaging.class, new Class<?>[]{long.class, String.class, long.class, long.class, String.class, java.time.LocalDateTime.class, com.owner.product.domain.product.enums.ProductEnum.class}, id, category, sellingPrice, price, name, expiredTime, size);
    }

}

