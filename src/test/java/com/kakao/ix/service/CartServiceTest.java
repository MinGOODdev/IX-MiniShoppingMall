package com.kakao.ix.service;

import com.kakao.ix.domain.Cart;
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
public class CartServiceTest {

  @Autowired
  private CartService cartService;

  @Test
  public void 사용자의_장바구니_목록_조회() throws Exception {
    List<Cart> cartList = cartService.findByUserId(2);
    log.info("* {}", cartList);
  }

  @Test
  public void 장바구니_상품_추가() throws Exception {
    cartService.insertProduct(2, 1, 1);
    cartService.insertProduct(2, 1, 3);
    cartService.insertProduct(2, 2, 3);

    Assert.assertNotNull(cartService.findByUserIdAndProductId(2, 1));
    Assert.assertNotNull(cartService.findByUserIdAndProductId(2, 2));
    log.info("* {}", cartService.findByUserIdAndProductId(2, 1));
    log.info("** {}", cartService.findByUserIdAndProductId(2, 2));
  }

}
