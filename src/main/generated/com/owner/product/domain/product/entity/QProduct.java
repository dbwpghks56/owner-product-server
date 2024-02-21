package com.owner.product.domain.product.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QProduct is a Querydsl query type for Product
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QProduct extends EntityPathBase<Product> {

    private static final long serialVersionUID = -78032794L;

    public static final QProduct product = new QProduct("product");

    public final com.owner.product.global.base.QBaseEntity _super = new com.owner.product.global.base.QBaseEntity(this);

    public final StringPath barcode = createString("barcode");

    public final StringPath category = createString("category");

    public final StringPath description = createString("description");

    public final DateTimePath<java.time.LocalDateTime> expiredTime = createDateTime("expiredTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Long> sellingPrice = createNumber("sellingPrice", Long.class);

    public final EnumPath<com.owner.product.domain.product.enums.ProductEnum> size = createEnum("size", com.owner.product.domain.product.enums.ProductEnum.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updateDate = _super.updateDate;

    public QProduct(String variable) {
        super(Product.class, forVariable(variable));
    }

    public QProduct(Path<? extends Product> path) {
        super(path.getType(), path.getMetadata());
    }

    public QProduct(PathMetadata metadata) {
        super(Product.class, metadata);
    }

}

