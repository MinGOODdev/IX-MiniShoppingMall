package com.kakao.ix.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "cart")
public class Cart {

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
