package com.owner.product.domain.product.controller;

import com.owner.product.domain.product.dto.request.ProductRequestDto;
import com.owner.product.domain.product.service.ProductService;
import com.owner.product.global.responses.success.codes.ProductSuccessCode;
import com.owner.product.global.responses.success.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping()
    public ResponseEntity<SuccessResponse> save(
            @RequestBody ProductRequestDto.Save saveRequest
            ) {
        return SuccessResponse.toResponseEntity(
                ProductSuccessCode.SAVE,
                productService.createProduct(saveRequest)
        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<SuccessResponse> findOne(
            @PathVariable(name = "productId") Long productId
    ) {
        return SuccessResponse.toResponseEntity(
                ProductSuccessCode.FIND,
                productService.findDetail(productId)
        );
    }

    @GetMapping()
    public ResponseEntity<SuccessResponse> findWithPaging(
            @ModelAttribute ProductRequestDto.Find findRequest
    ) {
        return SuccessResponse.toResponseEntity(
                ProductSuccessCode.FIND,
                productService.findWhole(findRequest)
        );
    }
}
