package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Order;
import com.kakao.ix.domain.User;
import com.kakao.ix.service.OrderService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("rest/kakao")
public class RestOrderController {

  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;

  /**
   * 장바구니 상품 목록 구매
   * @return
   */
  @PutMapping("cart/order")
  public ResponseEntity<List<Order>> orderListUpdate() {
    User currentUser = userService.currentLoginUser();
    orderService.insert(currentUser.getId());

    List<Order> orderList = orderService.findByUserId(currentUser.getId());
    return new ResponseEntity<>(orderList, HttpStatus.OK);
  }

  /**
   * 주문 내역 조회
   * @return
   */
  @GetMapping("orders")
  public ResponseEntity<Map<String, Integer>> orderList() {
    User currentUser = userService.currentLoginUser();
    List<Order> orderList = orderService.findByUserId(currentUser.getId());

    // 상품 별 금액과 총 금액을 보기 쉽게 하기 위한 Map
    Map<String, Integer> orderMap = orderService.makeReturnHashMap(orderList);
    return new ResponseEntity<>(orderMap, HttpStatus.OK);
  }

}
