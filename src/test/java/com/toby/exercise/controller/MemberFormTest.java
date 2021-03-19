package com.toby.exercise.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberFormTest {

    @Test
    public void getSetTest(){

        //given
        String name = "test";
        Long id = 1L;

        //when
        MemberForm memberForm = new MemberForm(name,id);

        //then
        assertThat(memberForm.getName()).isEqualTo(name);
        assertThat(memberForm.getId()).isEqualTo(id);


    }

}