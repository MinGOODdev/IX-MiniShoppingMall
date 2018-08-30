package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/kakao")
public class RestCartController {

  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

  /**
   * 로그인 사용자의 장바구니 목록 조회
   * @return
   */
  @GetMapping("cart")
  public ResponseEntity<List<Cart>> cartList() {
    Principal principal = SecurityContextHolder.getContext().getAuthentication();
    int userId = userService.findByLogin(principal.getName()).getId();

    List<Cart> cartList = cartService.findByUserId(userId);
    return new ResponseEntity<>(cartList, HttpStatus.OK);
  }

}
