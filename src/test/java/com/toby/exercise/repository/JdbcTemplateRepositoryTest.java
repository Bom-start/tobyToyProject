package com.toby.exercise.repository;

import com.toby.exercise.domain.Member;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class JdbcTemplateRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void save() throws SQLException {
        Member member = new Member();
        member.setName("spring");

        memberRepository.save(member);

        Member result =  memberRepository.findById(member.getId()).get();
        Assertions.assertEquals(member.getName(), result.getName());
    }

    @Test
    public void findByName() throws SQLException {
        Member member1 = new Member();
        member1.setName("spring1");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberRepository.save(member2);

        Member result = memberRepository.findByName("spring1").get();
        Assertions.assertEquals(member1.getName(), result.getName());
    }




}
