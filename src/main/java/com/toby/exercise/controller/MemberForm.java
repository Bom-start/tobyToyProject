package com.toby.exercise.controller;

import lombok.Builder;

@Builder
public class MemberForm {

    private String name;
    private Long id;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    public MemberForm(String name, Long id) {
        this.name = name;
        this.id = id;
    }
}
