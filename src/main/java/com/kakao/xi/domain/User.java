package com.kakao.xi.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity
//@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String login;
  private String password;

}
