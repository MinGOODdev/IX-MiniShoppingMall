package com.kakao.ix.util;

import com.kakao.ix.domain.User;
import com.kakao.ix.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private UserService userService;

  public Authentication authenticate(String login, String password) throws AuthenticationException {
    String encryptPassword = SHA256Encrypt.encrypt(password);
    User user = userService.findByLoginAndPassword(login, encryptPassword);
    if (user == null) return null;

    List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
    String role = "ordinary";

    grantedAuthorityList.add(new SimpleGrantedAuthority(role));
    return new MyAuthentication(login, encryptPassword, grantedAuthorityList, user);
  }

  /**
   * 사용자가 입력한 아이디와 패스워드를 검사할 때,
   * Spring-Security 엔진에 의해 이 클래스의 authenticate 메소드가 자동으로 호출됩니다.
   * 사용자가 입력한 아이디와 패스워드가 이 메소드의 파라미터로 전달됩니다.
   *
   * @param authentication
   * @return
   * @throws AuthenticationException
   */
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String login = authentication.getName();
    String password = authentication.getCredentials().toString();
    return authenticate(login, password);
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(UsernamePasswordAuthenticationToken.class);
  }

  public class MyAuthentication extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 1L;
    User user;

    public MyAuthentication(String login, String password, List<GrantedAuthority> grantedAuthorityList, User user) {
      super(login, password, grantedAuthorityList);
      this.user = user;
    }

    public User getUser() {
      return user;
    }

    public void setUser(User user) {
      this.user = user;
    }
  }

}
