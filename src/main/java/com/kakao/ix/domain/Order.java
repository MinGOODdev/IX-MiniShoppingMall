package com.kakao.ix.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
//@Table(name = "order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private int number;

  @ManyToOne
  @JoinColumn(name = "userId")
  User user;

  @ManyToOne
  @JoinColumn(name = "productId")
  Product product;

}
