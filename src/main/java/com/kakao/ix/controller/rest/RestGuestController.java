package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Product;
import com.kakao.ix.model.LoginModel;
import com.kakao.ix.model.ResponseModel;
import com.kakao.ix.service.CheckService;
import com.kakao.ix.service.ProductService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestGuestController {

  @Autowired
  private UserService userService;
  @Autowired
  private CheckService checkService;
  @Autowired
  private ProductService productService;

  /**
   * 메인 페이지 - 상품 목록 포함
   *
   * @return
   */
  @GetMapping("api")
  public ResponseEntity<ResponseModel> productList() {
    ResponseModel responseModel = new ResponseModel();
    List<Product> productList = productService.findAll();

    responseModel.setData(productList);
    responseModel.setMsg("메인 - 상품 목록 포함");
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

  /**
   * 회원가입 - 중복 체크
   *
   * @param loginModel
   * @return
   */
  @PostMapping("api/register")
  public ResponseEntity<ResponseModel> postRegister(@RequestBody LoginModel loginModel) {
    ResponseModel responseModel = new ResponseModel();
    String login = loginModel.getLogin();
    String password = loginModel.getPassword();
    Boolean flag = checkService.userExistCheck(login, password);

    if (flag) {
      responseModel.setData(userService.findByLogin(login));
      responseModel.setMsg("회원가입 성공");
      return new ResponseEntity(responseModel, HttpStatus.OK);
    } else {
      responseModel.setMsg("중복 회원 존재");
      return new ResponseEntity(responseModel, HttpStatus.OK);
    }
  }

}
