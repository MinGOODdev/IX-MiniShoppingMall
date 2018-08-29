package com.kakao.xi.service;

import com.kakao.xi.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceTest {

  @Autowired
  private ProductService productService;

  @Test
  public void 상품_전체_조회() throws Exception {
    List<Product> productList = productService.findAll();
    Assert.assertNotNull(productList);
    log.info("* {}", productList);
  }

  @Test
  public void 개별_상품_조회() throws Exception {
    Product product = productService.findById(2);
    Assert.assertNotNull(product);
    log.info("* {}", product);
  }

}
