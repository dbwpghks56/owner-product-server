package com.owner.product.domain.product.repository.querydsl;

import com.owner.product.domain.product.dto.request.ProductRequestDto;
import com.owner.product.domain.product.dto.response.ProductResponseDto;
import com.owner.product.domain.product.entity.Product;
import com.owner.product.domain.product.enums.ProductEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryProductRepository {
    ProductResponseDto.FindWhole productCursorBasedPaging(ProductRequestDto.Find findRequest);
}
