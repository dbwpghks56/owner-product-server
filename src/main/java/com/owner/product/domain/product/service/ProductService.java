package com.owner.product.domain.product.service;

import com.owner.product.domain.product.dto.request.ProductRequestDto;
import com.owner.product.domain.product.dto.response.ProductResponseDto;

public interface ProductService {
    String createProduct(ProductRequestDto.Save createRequestDto);
    ProductResponseDto.FindOne findDetail(Long productId);
}
