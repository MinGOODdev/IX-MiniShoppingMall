package com.kakao.ix.service;

import com.kakao.ix.domain.User;
import com.kakao.ix.util.SHA256Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void 아이디_패스워드로_사용자_조회() throws Exception {
    String login = "mingood";
    String password = SHA256Encrypt.encrypt("111111");
    User user = userService.findByLoginAndPassword(login, password);
    log.info("* {}", user.getLogin());
  }

//  @Test
//  public void 유저_저장_회원가입() throws Exception {
//    userService.insert("mingood", "111111");
//  }

}
