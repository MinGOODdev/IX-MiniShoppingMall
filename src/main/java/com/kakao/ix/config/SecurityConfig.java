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

  @Autowired
  private MyAuthenticationProvider myAuthenticationProvider;

  @Override
  public void configure(WebSecurity webSecurity) {
    // 보안 기능이 필요없는 Resource(css, js, png 등)의 경로를 설정합니다.
    webSecurity.ignoring().antMatchers("/res/**");
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    // httpSecurity.csrf().disable(); // form:form 태그 사용

    // url 별 접근 권한 설정
    httpSecurity.authorizeRequests()
            .antMatchers("/kakao/product/cart").authenticated()
            .antMatchers("/kakao/cart/**").authenticated()
            .antMatchers("/rest/kakao/product/**/**/cart").authenticated()
            .antMatchers("/rest/kakao/cart/**").authenticated()
            .antMatchers("/rest/kakao/orders").authenticated()
            .antMatchers("/**").permitAll();

    // 로그인 설정
    httpSecurity.formLogin().loginPage("/login")
            .loginProcessingUrl("/login_processing")
            .failureUrl("/login?error")
            .defaultSuccessUrl("/index", true)
            .usernameParameter("login")
            .passwordParameter("password");

    // 로그아웃 설정
    httpSecurity.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout_processing"))
            .logoutSuccessUrl("/index")
            .invalidateHttpSession(true);   // 로그아웃 시, 세션 데이터 전부 삭제

    httpSecurity.authenticationProvider(myAuthenticationProvider);
  }

}
