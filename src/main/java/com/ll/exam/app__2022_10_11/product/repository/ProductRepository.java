package com.ll.exam.app__2022_10_11.product.repository;

import com.ll.exam.app__2022_10_11.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
