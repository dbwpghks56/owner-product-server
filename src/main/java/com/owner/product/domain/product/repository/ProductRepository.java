package com.owner.product.domain.product.repository;

import com.owner.product.domain.product.entity.Product;
import com.owner.product.domain.product.repository.querydsl.QueryProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryProductRepository {

}
