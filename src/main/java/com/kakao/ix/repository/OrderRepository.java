package com.kakao.ix.repository;

import com.kakao.ix.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  Order findByUserIdAndProductId(int userId, int productId);

  @Query("select o from Order o join fetch o.user")
  List<Order> findByUserId(int userId);

}
