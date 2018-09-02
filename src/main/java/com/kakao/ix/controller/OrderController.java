package com.kakao.ix.controller;

import com.kakao.ix.domain.Order;
import com.kakao.ix.domain.User;
import com.kakao.ix.service.OrderService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("kakao")
public class OrderController {

  @Autowired
  private UserService userService;
  @Autowired
  private OrderService orderService;

  /**
   * 장바구니 목록 구매
   *
   * @return
   */
  @PostMapping("cart/order")
  public String order() {
    User currentUser = userService.currentLoginUser();
    orderService.insert(currentUser.getId());
    return "redirect:/kakao/orders";
  }

  /**
   * 구매내역 조회
   *
   * @param model
   * @return
   */
  @GetMapping("orders")
  public String orderList(Model model) {
    User currentUser = userService.currentLoginUser();
    List<Order> orderList = orderService.findByUserId(currentUser.getId());
    Map<String, Integer> orderMap = orderService.makeReturnHashMap(orderList);

    model.addAttribute("orderList", orderList);
    model.addAttribute("orderMap", orderMap);
    return "user/order";
  }

}
