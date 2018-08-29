package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.Product;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.ProductService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/kakao")
public class RestProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

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

  /**
   * 해당 상품을 로그인 사용자 장바구니에 추가
   * @param productId
   * @param number
   * @return
   */
  @PostMapping("product/{productId}/{number}/cart")
  public ResponseEntity<Cart> productInCart(@PathVariable("productId") int productId,
                                            @PathVariable("number") int number) {
    Principal principal = SecurityContextHolder.getContext().getAuthentication();
    int userId = userService.findByLogin(principal.getName()).getId();

    cartService.insertProduct(userId, productId, number);
    Cart cart = cartService.findByUserIdAndProductId(userId, productId);
    return new ResponseEntity<>(cart, HttpStatus.OK);
  }

}
