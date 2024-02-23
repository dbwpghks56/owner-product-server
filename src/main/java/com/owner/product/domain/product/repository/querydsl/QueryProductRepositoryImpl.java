package com.owner.product.domain.product.repository.querydsl;

import com.owner.product.domain.product.dto.request.ProductRequestDto;
import com.owner.product.domain.product.dto.response.ProductResponseDto;
import com.owner.product.domain.product.dto.response.QProductResponseDto_FindWithPaging;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.owner.product.domain.product.entity.QProduct.product;

@Slf4j
@RequiredArgsConstructor
public class QueryProductRepositoryImpl implements QueryProductRepository{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ProductResponseDto.FindWhole productCursorBasedPaging(
            ProductRequestDto.Find findRequest
    ) {
        Pageable pageable = PageRequest.of(findRequest.getPage(), findRequest.getSize());
        BooleanBuilder predicate = new BooleanBuilder();

        if (findRequest.getCursor() != null) {
            predicate.and(product.id.gt(findRequest.getCursor())); // Assuming id is the cursor field
        }

        QProductResponseDto_FindWithPaging selectExpression = new QProductResponseDto_FindWithPaging(
                product.id,
                product.category,
                product.sellingPrice,
                product.price,
                product.name,
                product.expiredTime,
                product.size
        );

        JPAQuery<ProductResponseDto.FindWithPaging> queryFactory =
                jpaQueryFactory.select(selectExpression).from(product).where(predicate);

        if(findRequest.getName() != null) {
            queryFactory.where(product.name.like(findRequest.getName()));
        }

        List<ProductResponseDto.FindWithPaging> productList = queryFactory
                .limit(findRequest.getSize())
                .offset(findRequest.getOffSet())
                .orderBy(product.regDate.desc())
                .distinct()
                .fetch();

        Long lastCursor = productList.get(productList.size() - 1).getId();

        return ProductResponseDto.FindWhole.builder()
                .findWithPagingPage(new PageImpl<>(productList, pageable, productList.size()))
                .lastCursor(lastCursor)
                .build();
    }
}