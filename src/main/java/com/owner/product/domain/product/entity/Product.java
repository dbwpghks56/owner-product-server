package com.owner.product.domain.product.entity;

import com.owner.product.domain.product.dto.response.ProductResponseDto;
import com.owner.product.domain.product.enums.ProductEnum;
import com.owner.product.global.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@Entity
@DynamicUpdate
@Table(name = "tb_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Long sellingPrice;
    private Long price;
    private String name;
    private String description;
    private String barcode;

    @Column(updatable = false)
    private LocalDateTime expiredTime;

    @Enumerated(EnumType.STRING)
    private ProductEnum size;

    public ProductResponseDto.FindOne toResponseDto() {
        return ProductResponseDto.FindOne.builder()
                .id(this.id)
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
