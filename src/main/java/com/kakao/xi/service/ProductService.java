package com.kakao.xi.service;

import com.kakao.xi.domain.Product;
import com.kakao.xi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepository;

  /**
   * 모든 상품 목록 조회
   * @return
   */
  public List<Product> findAll() {
    List<Product> productList = productRepository.findAll();
    return productList;
  }

  /**
   * 개별 상품 조회
   * @param id: productId
   * @return
   */
  public Product findById(int id) {
    Product product = productRepository.findById(id);
    return product;
  }

}
