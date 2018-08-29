package com.kakao.xi.repository;

import com.kakao.xi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findById(int id);

  User findByLoginAndPassword(String login, String password);

  User findByLogin(String login);

}
