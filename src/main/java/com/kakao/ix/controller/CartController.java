package com.kakao.ix.controller;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.User;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("kakao")
public class CartController {

  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

  /**
   * 로그인 사용자의 장바구니 목록 조회
   *
   * @param model
   * @return
   */
  @GetMapping("cart")
  public String cartList(Model model) {
    User currentUser = userService.currentLoginUser();
    List<Cart> cartList = cartService.findByUserId(currentUser.getId());

    model.addAttribute("cartList", cartList);
    return "user/cart";
  }

  /**
   * 장바구니 항목 개별 삭제
   * (개수에 상관없이 삭제하고 다시 원하는 개수를 지정해서 장바구니 추가하는게 낫다고 판단)
   *
   * @param productId
   * @return
   */
  @GetMapping("cart/out")
  public String removeProductInCart(@RequestParam("productId") int productId) {
    User user = userService.currentLoginUser();
    Cart cart = cartService.findByUserIdAndProductId(user.getId(), productId);
    cartService.delete(cart);
    return "redirect:/kakao/cart";
  }

}
