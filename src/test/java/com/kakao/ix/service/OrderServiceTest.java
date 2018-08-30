package com.kakao.ix.service;

import com.kakao.ix.domain.Order;
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
public class OrderServiceTest {

  @Autowired
  private OrderService orderService;

  @Test
  public void 사용자ID로_주문_내역_조회() throws Exception {
    List<Order> orderList = orderService.findByUserId(2);
    log.info("* {}", orderList);
    Assert.assertNotNull(orderList);
  }

}
