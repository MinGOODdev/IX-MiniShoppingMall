package com.kakao.xi.controller;

import com.kakao.xi.domain.Product;
import com.kakao.xi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("kakao")
public class ProductController {

  @Autowired
  private ProductService productService;

  /**
   * 상품 목록 전체 조회
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

}
