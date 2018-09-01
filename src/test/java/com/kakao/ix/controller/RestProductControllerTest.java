package com.kakao.ix.controller;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RestProductControllerTest {

  @Autowired
  private WebApplicationContext wac;

  private MockMvc mockMvc;
  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf-8"));

  @Before
  public void init() throws Exception {
    this.mockMvc = webAppContextSetup(wac).build();
  }

  @Test
  public void 상품_목록_전체_조회() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andDo(print())
            .andReturn();
    log.info(mvcResult.getResponse().getContentAsString());
  }

  @Test
  public void 상품_개별_조회() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/api/product/2"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(contentType))
            .andDo(print())
            .andReturn();
    log.info(mvcResult.getResponse().getContentAsString());
  }

}
