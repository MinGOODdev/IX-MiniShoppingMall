package com.kakao.ix.controller;

import com.kakao.ix.domain.Product;
import com.kakao.ix.domain.User;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.ProductService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("kakao")
public class ProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

  /**
   * 상품 목록 전체 조회
   *
   * @param model
   * @return
   */
  @GetMapping("products")
  public String productList(Model model) {
    List<Product> productList = productService.findAll();
    model.addAttribute("productList", productList);
    return "user/productList";
  }

  /**
   * 상품 목록 개별 조회
   *
   * @param model
   * @param id
   * @return
   */
  @GetMapping("product/{productId}")
  public String getProduct(Model model,
                           @PathVariable("productId") int id) {
    Product product = productService.findById(id);
    model.addAttribute("product", product);
    return "user/productDetail";
  }

  /**
   * 해당 상품 장바구니에 담기
   *
   * @param productId
   * @param number
   * @return
   */
  @PostMapping("product/cart")
  public String productInCart(@RequestParam("productId") int productId,
                              @RequestParam("number") int number) {
    User currentUser = userService.currentLoginUser();
    cartService.insertProduct(currentUser.getId(), productId, number);
    return "redirect:/kakao/cart";
  }

}
