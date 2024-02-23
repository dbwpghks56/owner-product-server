package com.owner.product.domain.product.dto.response;

import com.owner.product.domain.product.enums.ProductEnum;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Page;

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

    @Setter
    @Getter
    @Builder
    @NoArgsConstructor
    public static class FindWithPaging {
        private Long id;
        private String category;
        private Long sellingPrice;
        private Long price;
        private String name;
        private LocalDateTime expiredTime;
        private ProductEnum size;

        @QueryProjection
        public FindWithPaging(
                Long id,
                String category,
                Long sellingPrice,
                Long price,
                String name,
                LocalDateTime expiredTime,
                ProductEnum size
        ) {
            this.id = id;
            this.category = category;
            this.sellingPrice = sellingPrice;
            this.price = price;
            this.name = name;
            this.expiredTime = expiredTime;
            this.size = size;
        }
    }

    @Getter
    @Setter
    @Builder
    public static class FindWhole {
        private Page<FindWithPaging> findWithPagingPage;
        private Long lastCursor;
    }
}
