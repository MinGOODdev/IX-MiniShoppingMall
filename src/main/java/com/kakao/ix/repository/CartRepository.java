package com.kakao.ix.repository;

import com.kakao.ix.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

  Cart findByUserIdAndProductId(int userId, int productId);

  List<Cart> findByUserId(int userId);

  void delete(Cart cart);

}
