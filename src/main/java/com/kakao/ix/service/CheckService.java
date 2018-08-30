package com.kakao.ix.service;

import com.kakao.ix.domain.User;
import com.kakao.ix.repository.CheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckService implements CheckRepository {

  @Autowired
  private UserService userService;

  /**
   * 같은 login의 사용자가 DB에 존재하는지 검사
   * 존재하지 않으면 true (회원가입 성공)
   * 존재하면 false (회원가입 실패)
   * @param login
   * @return
   */
  @Override
  public Boolean userExistCheck(String login, String password) {
    User user = userService.findByLogin(login);
    if (user == null) {
      userService.insert(login, password);  // 회원가입 성공 - DB 저장
      return true;
    }
    else return false;
  }

}
