package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.User;
import com.kakao.ix.model.LoginModel;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("rest")
public class RestGuestController {

  @Autowired
  private UserService userService;

  /**
   * 회원가입 - 중복 체크
   * @param loginModel
   * @return
   */
  @PostMapping("register")
  public ResponseEntity<User> postRegister(@RequestBody LoginModel loginModel) {
    String login = loginModel.getLogin();
    String password = loginModel.getPassword();

    User user = userService.findByLogin(login);
    if (user == null) {
      userService.insert(login, password);
      return new ResponseEntity(userService.findByLogin(login), HttpStatus.OK);
    }
    else return new ResponseEntity(null, HttpStatus.OK);
  }

}
