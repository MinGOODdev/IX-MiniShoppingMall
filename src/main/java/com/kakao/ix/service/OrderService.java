package com.kakao.ix.service;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.Order;
import com.kakao.ix.domain.User;
import com.kakao.ix.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private CartService cartService;
  @Autowired
  private UserService userService;

  /**
   * 사용자 ID로 구매 리스트 조회
   * @param userId
   * @return
   */
  public List<Order> findByUserId(int userId) {
    List<Order> orderList = orderRepository.findByUserId(userId);
    return orderList;
  }

  /**
   * 사용자 ID와 상품 ID로 해당 상품 구매 내역 검색
   * @param userId
   * @param productId
   * @return
   */
  private Order findByUserIdAndProductId(int userId, int productId) {
    Order order = orderRepository.findByUserIdAndProductId(userId, productId);
    return order;
  }

  /**
   * 장바구니 구매 내역(주문 내역) 저장 및 업데이트 - number(개수)는 누적
   * 주문 내역을 업데이트하면서 장바구니 내역은 삭제
   * @param userId
   */
  public void insert(int userId) {
    List<Cart> cartList = cartService.findByUserId(userId);
    User user = userService.findById(userId);
    for (Cart cart : cartList) {
      Order order = this.findByUserIdAndProductId(userId, cart.getProduct().getId());
      if (order == null) {
        Order tempOrder = new Order();
        tempOrder.setUser(user);
        tempOrder.setProduct(cart.getProduct());
        tempOrder.setNumber(cart.getNumber());
        orderRepository.save(tempOrder);
        cartService.delete(cart);
      }
      else {
        order.setNumber(order.getNumber() + cart.getNumber());
        orderRepository.save(order);
        cartService.delete(cart);
      }
    }
  }

  public Map<String, Integer> makeReturnHashMap(List<Order> orderList) {
    Map<String, Integer> orderMap = new HashMap<>();
    int totalPrice = 0;
    for (Order order : orderList) {
      String productName = order.getProduct().getProductName();
      int productNumber = order.getNumber();
      int productPrice = order.getProduct().getProductPrice();
      orderMap.put(productName + ": " + productNumber + "개", productPrice * productNumber);
    }
    for (String key : orderMap.keySet()) {
      totalPrice += orderMap.get(key);
    }
    orderMap.put("총 금액", totalPrice);
    return orderMap;
  }

}
