package com.kakao.xi.service;

import com.kakao.xi.domain.User;
import com.kakao.xi.repository.UserRepository;
import com.kakao.xi.util.SHA256Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * User 조회
   * @param login
   * @return
   */
  public User findByLogin(String login) {
    User user = userRepository.findByLogin(login);
    return user;
  }

  /**
   * User 조회
   * @param login
   * @param password
   * @return
   */
  public User findByLoginAndPassword(String login, String password) {
    User user = userRepository.findByLoginAndPassword(login, password);
    return user;
  }

  /**
   * 회원가입 시, 새로운 User 저장
   * @param login
   * @param password
   */
  public void insert(String login, String password) {
    String encryptPassword = SHA256Encrypt.encrypt(password);
    User user = new User();
    user.setLogin(login);
    user.setPassword(encryptPassword);
    userRepository.save(user);
  }

}
