package com.toby.exercise.controller;

import com.toby.exercise.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void createForm() throws Exception{
        String body = this.testRestTemplate.getForObject("/members/new",String.class);

        assertThat(body).contains("createMemberForm");
    }


}