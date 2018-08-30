package com.kakao.ix.controller;

import com.kakao.ix.domain.User;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GuestController {

  @Autowired
  private UserService userService;

  /**
   * 로그인 후 첫 View
   * @return
   */
  @GetMapping("index")
  public String index() {
    return "user/index";
  }

  /**
   * 로그인 View
   * @return
   */
  @GetMapping({"/", "login"})
  public String login() {
    return "guest/login";
  }

  /**
   * 회원가입 View
   * @return
   */
  @GetMapping("register")
  public String getRegister() {
    return "guest/register";
  }

  /**
   * 회원가입 POST - 중복 회원가입을 못하도록 체크
   * @param login
   * @param password
   * @return
   */
  @PostMapping("register")
  public String postRegister(@RequestParam("login") String login,
                             @RequestParam("password") String password) {
    User user = userService.findByLogin(login);
    if (user == null) {
      userService.insert(login, password);
      return "guest/login";
    }
    else return "guest/register";
  }

}
