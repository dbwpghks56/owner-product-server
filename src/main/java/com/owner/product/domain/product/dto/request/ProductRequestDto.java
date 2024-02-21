package com.owner.product.domain.product.dto.request;

import com.owner.product.domain.product.entity.Product;
import com.owner.product.domain.product.enums.ProductEnum;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Valid
public class ProductRequestDto {
    @Getter
    @Setter
    public static class Save {
        private String category;
        private Long sellingPrice;
        private Long price;
        private String name;
        private String description;
        private String barcode;
        private LocalDateTime expiredTime;
        private ProductEnum size;

        public Product toEntity() {
            return Product.builder()
                    .category(this.category)
                    .sellingPrice(this.sellingPrice)
                    .price(this.price)
                    .name(this.name)
                    .description(this.description)
                    .barcode(this.barcode)
                    .expiredTime(this.expiredTime)
                    .size(this.size)
                    .build();
        }
    }
}
