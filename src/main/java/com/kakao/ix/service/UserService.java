package com.kakao.ix.service;

import com.kakao.ix.domain.User;
import com.kakao.ix.repository.UserRepository;
import com.kakao.ix.util.SHA256Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * 현재 로그인 사용자 가져오기
   * @return
   */
  public User currentLoginUser() {
    Principal principal = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepository.findByLogin(principal.getName());
    return user;
  }

  /**
   * User 조회
   * @param id
   * @return
   */
  public User findById(int id) {
    User user = userRepository.findById(id);
    return user;
  }

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
    User user = User.builder()
            .login(login)
            .password(encryptPassword)
            .build();
    userRepository.save(user);
  }

}
