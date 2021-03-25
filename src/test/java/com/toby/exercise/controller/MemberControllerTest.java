package com.toby.exercise.controller;

import com.toby.exercise.domain.Member;
import com.toby.exercise.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private MemberRepository memberRepository;

    @LocalServerPort
    private int port;

    @AfterEach
    public void deleteMember() throws SQLException {
        System.out.println("deleteAll");
        memberRepository.deleteAll();
        System.out.println("deleteAll");
    }


    @Test
    void createForm() throws Exception{
        String body = this.testRestTemplate.getForObject("/members/new",String.class);

        assertThat(body).contains("createMemberForm");
    }


    @Test
    void create() throws Exception {

        String url = "http://localhost:" + port + "/members/new";

        mvc.perform(post(url)
            .param("name","test123"))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location","/"))
            .andDo(MockMvcResultHandlers.print());

        List<Member> list =  memberRepository.findAll();

        assertNotNull(list.get(0).getId());
        assertNotNull(list.get(0).getName());
    }

    @Test
    void list() throws Exception {
        List<Member> list =  memberRepository.findAll();

        assertThat(list.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void update() throws Exception {

        Member member = new Member();
        member.setName("test4321");

        //given
        Member saveMember = memberRepository.save(member);

        Long updateId = saveMember.getId();
        String expectedName = "test112233";


        String url = "http://localhost:" + port + "/members/update";

        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"name\":\""+expectedName+"\",\"id\":\""+updateId+"\"}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/members"))
                .andDo(MockMvcResultHandlers.print());

        List<Member> list =  memberRepository.findAll();

        assertThat(list.get(0).getName()).isEqualTo(expectedName);


    }

    @Test
    void delete() throws Exception {

        Member member = new Member();
        member.setName("test4321");

        //given
        Member saveMember = memberRepository.save(member);

        Long deleteId = saveMember.getId();

        String url = "http://localhost:" + port + "/members/delete";
        mvc.perform(get(url)
                .param("id",Long.toString(deleteId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/members"))
                .andDo(MockMvcResultHandlers.print());

        Optional<Member> deleteMember =  memberRepository.findById(deleteId);

        assertThat(deleteMember.isPresent()).isFalse();
    }
}