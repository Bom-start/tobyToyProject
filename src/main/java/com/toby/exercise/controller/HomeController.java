package com.toby.exercise.controller;

import com.toby.exercise.domain.Member;
import com.toby.exercise.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

@Controller
public class HomeController {

    private final MemberService memberService;

    public HomeController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home(){return "home";}

    @GetMapping("/members/update")
    public String memberUpdate(@RequestParam("id") Long id, Model model) throws SQLException {
        model.addAttribute("id",id);
        return "members/updateMemberForm";
    }
}
