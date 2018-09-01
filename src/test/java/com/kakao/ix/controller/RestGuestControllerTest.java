package com.kakao.ix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.ix.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestGuestControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;
  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));

  @Before
  public void init() throws Exception {
    this.mockMvc = webAppContextSetup(wac).build();
  }

  @Test
  public void 회원가입() throws Exception {
    User user = User.builder()
            .login("moonilly")
            .password("111111")
            .build();

    MvcResult mvcResult = mockMvc.perform(post("/api/register")
            .contentType(contentType)
            .content(asJsonString(user)))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();

    log.info(mvcResult.getResponse().getContentAsString());
  }

  public static String asJsonString(Object obj) {
    try {
      final ObjectMapper objectMapper = new ObjectMapper();
      final String jsonContent = objectMapper.writeValueAsString(obj);
      return jsonContent;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
