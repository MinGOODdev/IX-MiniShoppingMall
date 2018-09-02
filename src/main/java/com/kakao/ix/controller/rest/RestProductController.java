package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.Product;
import com.kakao.ix.domain.User;
import com.kakao.ix.model.ResponseModel;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.ProductService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestProductController {

  @Autowired
  private ProductService productService;
  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

  /**
   * 상품 목록 전체 조회
   *
   * @return
   */
  @GetMapping("products")
  public ResponseEntity<ResponseModel> productList() {
    ResponseModel responseModel = new ResponseModel();
    List<Product> productList = productService.findAll();

    responseModel.setData(productList);
    responseModel.setMsg("상품 목록 전체 조회");
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

  /**
   * 상품 목록 개별 조회
   *
   * @param id: productId
   * @return
   */
  @GetMapping("product/{productId}")
  public ResponseEntity<ResponseModel> getProduct(@PathVariable("productId") int id) {
    ResponseModel responseModel = new ResponseModel();
    Product product = productService.findById(id);

    responseModel.setData(product);
    responseModel.setMsg("상품 개별 조회");
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

  /**
   * 해당 상품을 로그인 사용자 장바구니에 추가
   *
   * @param productId
   * @param number
   * @return
   */
  @PutMapping("product/{productId}/{number}/cart")
  public ResponseEntity<ResponseModel> productInCart(@PathVariable("productId") int productId,
                                                     @PathVariable("number") int number) {
    ResponseModel responseModel = new ResponseModel();
    User currentUser = userService.currentLoginUser();
    cartService.insertProduct(currentUser.getId(), productId, number);
    Cart cart = cartService.findByUserIdAndProductId(currentUser.getId(), productId);

    responseModel.setData(cart);
    responseModel.setMsg("해당 상품 장바구니에 추가");
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

}
