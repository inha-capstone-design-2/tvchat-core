package com.capstone.tvchat.api.auth.controller;

import com.capstone.tvchat.api.auth.domain.dto.request.MemberSignupDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void signup() throws Exception {
        MemberSignupDto tester = MemberSignupDto.of()
                .nickname("tester")
                .build();

        String content = objectMapper.writeValueAsString(tester);
        mockMvc.perform(
                post("/api/auth/signup").header(HttpHeaders.AUTHORIZATION,"Basic dGVzdGVyQHRlc3QuY29tOnRlc3Rlcg==")
                        .contentType(MediaType.APPLICATION_JSON).content(content))
                .andExpect(jsonPath("$.data.email").value("tester@test.com"))
                .andDo(print());
    }

    @Test
    void logout() {

    }

    @Test
    void login() throws Exception {
        mockMvc.perform(
                        post("/api/auth/login").header(HttpHeaders.AUTHORIZATION,"Basic dGVzdGVyQHRlc3QuY29tOnRlc3Rlcg=="))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void reissue(){

    }
}