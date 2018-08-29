package com.kakao.xi.controller;

import com.kakao.xi.domain.Product;
import com.kakao.xi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("kakao")
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * 상품 목록 전체 조회
   * @return
   */
  @GetMapping("products")
  public ResponseEntity<List<Product>> productList() {
    List<Product> productList = productService.findAll();
    return new ResponseEntity<>(productList, HttpStatus.OK);
  }

  /**
   * 상품 목록 개별 조회
   * @param id: productId
   * @return
   */
  @GetMapping("product/{productId}")
  public ResponseEntity<Product> getProduct(@PathVariable("productId") int id) {
    Product product = productService.findById(id);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

}
