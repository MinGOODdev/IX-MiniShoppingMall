package com.kakao.ix.controller;

import com.kakao.ix.domain.Product;
import com.kakao.ix.service.CheckService;
import com.kakao.ix.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuestController {

  @Autowired
  private ProductService productService;
  @Autowired
  private CheckService checkService;

  /**
   * 로그인 후 첫 View - 상품 목록까지
   *
   * @return
   */
  @GetMapping({"/", "index"})
  public String index(Model model) {
    List<Product> productList = productService.findAll();
    model.addAttribute("productList", productList);
    return "user/index";
  }

  /**
   * 로그인 View
   *
   * @return
   */
  @GetMapping("login")
  public String login() {
    return "guest/login";
  }

  /**
   * 회원가입 View
   *
   * @return
   */
  @GetMapping("register")
  public String getRegister() {
    return "guest/register";
  }

  /**
   * 회원가입 POST - 중복 회원가입을 못하도록 체크
   *
   * @param login
   * @param password
   * @return
   */
  @PostMapping("register")
  public String postRegister(@RequestParam("login") String login,
                             @RequestParam("password") String password) {
    Boolean flag = checkService.userExistCheck(login, password);
    if (flag) return "guest/login";
    else return "guest/register";
  }

}
