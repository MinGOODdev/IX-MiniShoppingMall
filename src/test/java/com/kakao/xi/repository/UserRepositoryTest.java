package com.kakao.xi.repository;

import com.kakao.xi.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void 아이디로_사용자_조회() throws Exception {
    User user = userRepository.findByLogin("mingood");
    log.info("* {}", user);
  }

  @Test
  public void 아이디_패스워드로_사용자_조회() throws Exception {
    User user = userRepository.findByLoginAndPassword("mingooddev", "111111");
    log.info("* {}", user.getLogin());
  }

}
