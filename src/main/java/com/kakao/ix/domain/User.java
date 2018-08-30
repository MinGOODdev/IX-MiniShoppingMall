package com.kakao.ix.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Builder
@ToString
@Entity
@Table(name = "\"user\"")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String login;
  private String password;

  public User() {}

  public User(int id, String login, String password) {
    this.id = id;
    this.login = login;
    this.password = password;
  }

}
