package com.kakao.ix.controller.rest;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.User;
import com.kakao.ix.model.ResponseModel;
import com.kakao.ix.service.CartService;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class RestCartController {

  @Autowired
  private UserService userService;
  @Autowired
  private CartService cartService;

  /**
   * 로그인 사용자의 장바구니 목록 조회
   *
   * @return
   */
  @GetMapping("cart")
  public ResponseEntity<ResponseModel> cartList() {
    ResponseModel responseModel = new ResponseModel();
    User currentUser = userService.currentLoginUser();
    List<Cart> cartList = cartService.findByUserId(currentUser.getId());

    responseModel.setData(cartList);
    responseModel.setMsg("장바구니 조회");
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

  /**
   * 장바구니 항목 개별 삭제
   * (개수에 상관없이 삭제하고 다시 원하는 개수를 지정해서 장바구니 추가하는게 낫다고 판단)
   *
   * @param productId
   * @return
   */
  @DeleteMapping("cart/{productId}")
  public ResponseEntity<ResponseModel> removeProductInCart(@PathVariable("productId") int productId) {
    ResponseModel responseModel = new ResponseModel();
    User user = userService.currentLoginUser();
    Cart cart = cartService.findByUserIdAndProductId(user.getId(), productId);

    responseModel.setData(cart);
    responseModel.setMsg("선택 상품 장바구니에서 삭제");
    cartService.delete(cart);
    return new ResponseEntity<>(responseModel, HttpStatus.OK);
  }

}
