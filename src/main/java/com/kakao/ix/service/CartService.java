package com.kakao.ix.service;

import com.kakao.ix.domain.Cart;
import com.kakao.ix.domain.Product;
import com.kakao.ix.domain.User;
import com.kakao.ix.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

  @Autowired
  private CartRepository cartRepository;
  @Autowired
  private UserService userService;
  @Autowired
  private ProductService productService;

  /**
   * 해당 유저의 장바구니 목록 조회
   *
   * @param userId
   * @return
   */
  public List<Cart> findByUserId(int userId) {
    List<Cart> cartList = cartRepository.findByUserId(userId);
    return cartList;
  }

  /**
   * 사용자의 장바구니에 해당 상품 정보 저장 및 업데이트 - number(개수)는 누적
   *
   * @param userId
   * @param productId
   * @param number
   */
  public void insertProduct(int userId, int productId, int number) {
    Cart tempCart = this.findByUserIdAndProductId(userId, productId);
    User user = userService.findById(userId);
    Product product = productService.findById(productId);

    if (tempCart == null) {
      Cart cart = new Cart();
      cart.setUser(user);
      cart.setProduct(product);
      cart.setNumber(number);
      cartRepository.save(cart);
    } else {
      tempCart.setNumber(tempCart.getNumber() + number);
      cartRepository.save(tempCart);
    }
  }

  /**
   * 사용자 ID와 상품 ID로 장바구니 해당 컬럼 조회
   *
   * @param userId
   * @param productId
   * @return
   */
  public Cart findByUserIdAndProductId(int userId, int productId) {
    Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
    return cart;
  }

  /**
   * 해당 장바구니 항목 삭제
   *
   * @param cart
   */
  public void delete(Cart cart) {
    cartRepository.delete(cart);
  }

}
