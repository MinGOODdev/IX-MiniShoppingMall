package com.kakao.ix.repository;

import com.kakao.ix.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

  Cart findByUserIdAndProductId(int userId, int productId);

  @Query("select c from Cart c join fetch c.user")
  List<Cart> findByUserId(int userId);

  void delete(Cart cart);

}
