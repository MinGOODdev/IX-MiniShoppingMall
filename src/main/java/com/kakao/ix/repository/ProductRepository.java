package com.kakao.ix.repository;

import com.kakao.ix.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findAll();

  Product findById(int id);

}
