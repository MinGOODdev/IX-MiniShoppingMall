package com.kakao.ix.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String productName;
  private int productPrice;
  private String imgUrl;

  @Override
  public String toString() {
    return String.format("%s / %d / %s", productName, productPrice, imgUrl);
  }
}
