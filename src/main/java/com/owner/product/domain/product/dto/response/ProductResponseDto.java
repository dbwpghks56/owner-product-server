package com.owner.product.domain.product.dto.response;

import com.owner.product.domain.product.enums.ProductEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class ProductResponseDto {

    @Getter
    @Setter
    @Builder
    public static class FindOne {
        private Long id;
        private String category;
        private Long sellingPrice;
        private Long price;
        private String name;
        private String description;
        private String barcode;
        private LocalDateTime expiredTime;
        private ProductEnum size;
    }
}
