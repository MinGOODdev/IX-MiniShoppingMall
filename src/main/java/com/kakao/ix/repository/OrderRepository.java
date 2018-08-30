package com.kakao.ix.repository;

import com.kakao.ix.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

  Order findByUserIdAndProductId(int userId, int productId);

  List<Order> findByUserId(int userId);

}
