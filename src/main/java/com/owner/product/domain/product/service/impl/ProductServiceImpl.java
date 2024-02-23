package com.owner.product.domain.product.service.impl;

import com.owner.product.domain.product.dto.request.ProductRequestDto;
import com.owner.product.domain.product.dto.response.ProductResponseDto;
import com.owner.product.domain.product.entity.Product;
import com.owner.product.domain.product.repository.ProductRepository;
import com.owner.product.domain.product.service.ProductService;
import com.owner.product.global.responses.errors.codes.ProductErrorCode;
import com.owner.product.global.responses.errors.exceptions.RestBusinessException;
import com.owner.product.global.responses.success.codes.ProductSuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public String createProduct(ProductRequestDto.Save createRequestDto) {
        productRepository.save(createRequestDto.toEntity());

        return ProductSuccessCode.SAVE.getMessage();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto.FindOne findDetail(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new RestBusinessException(ProductErrorCode.NOT_FOUND)
        );

        return product.toResponseDto();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto.FindWhole findWhole(ProductRequestDto.Find findRequest) {
        return productRepository.productCursorBasedPaging(findRequest);
    }
}
