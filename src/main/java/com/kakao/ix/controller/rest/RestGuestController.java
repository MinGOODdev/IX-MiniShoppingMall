package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.User;
import com.kakao.ix.model.LoginModel;
import com.kakao.ix.service.CheckService;
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
  @Autowired
  private CheckService checkService;

  /**
   * 회원가입 - 중복 체크
   * @param loginModel
   * @return
   */
  @PostMapping("register")
  public ResponseEntity<User> postRegister(@RequestBody LoginModel loginModel) {
    String login = loginModel.getLogin();
    String password = loginModel.getPassword();

    Boolean flag = checkService.userExistCheck(login, password);
    if (flag) return new ResponseEntity(userService.findByLogin(login), HttpStatus.OK);
    else return new ResponseEntity(null, HttpStatus.OK);
  }

}
