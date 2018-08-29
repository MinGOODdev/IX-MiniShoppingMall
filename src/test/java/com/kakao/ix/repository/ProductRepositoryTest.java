package com.kakao.ix.repository;

import com.kakao.ix.domain.Product;
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
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository productRepository;

  @Test
  public void 상품_전체_조회() throws Exception {
    List<Product> productList = productRepository.findAll();
    Assert.assertNotNull(productList);
    log.info("* {}", productList);
  }

  @Test
  public void 개별_상품_조회() throws Exception {
    Product product = productRepository.findById(1);
    Assert.assertNotNull(product);
    log.info("* {}", product);
  }

}
