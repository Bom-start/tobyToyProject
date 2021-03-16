package com.toby.exercise.repository;

import com.toby.exercise.domain.Member;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id) throws SQLException;
    Optional<Member> findByName(String name) throws SQLException;
    List<Member> findAll() throws SQLException;
}
