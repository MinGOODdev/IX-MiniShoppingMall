package com.kakao.ix.config;

import com.kakao.ix.util.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // TODO 로그인 사용자만 할 수 있는 것 설정하기

  @Autowired
  private MyAuthenticationProvider myAuthenticationProvider;

  @Override
  public void configure(WebSecurity webSecurity) {
    // 보안 기능이 필요없는 Resource(css, js, png 등)의 경로를 설정합니다.
    webSecurity.ignoring().antMatchers("/res/**");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.csrf().disable();

    httpSecurity.authorizeRequests()
            .antMatchers("/**").permitAll();

    httpSecurity.formLogin().loginPage("/guest/login")
            .loginProcessingUrl("/login_processing")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/index", true)
            .usernameParameter("login")
            .passwordParameter("password");

    httpSecurity.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout_processing"))
            .logoutSuccessUrl("/index")
            .invalidateHttpSession(true);   // 로그아웃 시, 세션 데이터 전부 삭제

    httpSecurity.authenticationProvider(myAuthenticationProvider);
  }

}
