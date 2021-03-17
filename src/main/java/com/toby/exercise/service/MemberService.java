package com.toby.exercise.service;

import com.toby.exercise.domain.Member;
import com.toby.exercise.repository.MemberRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;
    //member 리포지토리를 외부에서 주입

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) throws SQLException {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) throws SQLException{
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers() throws SQLException{
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) throws SQLException{
        return memberRepository.findById(memberId);
    }

}
