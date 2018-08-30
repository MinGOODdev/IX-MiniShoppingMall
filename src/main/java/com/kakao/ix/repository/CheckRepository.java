package com.kakao.ix.repository;

public interface CheckRepository {

  Boolean userExistCheck(String login, String password);

}
