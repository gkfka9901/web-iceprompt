package com.study.web.controller;

import com.study.web.dto.MemberDTO;
import com.study.web.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    //생성자 주입
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    // 회원가입
    @GetMapping("/web/membership")
    public String membership() {

        return "membership";
    }

    @PostMapping("/web/membershippro")
    public String membershippro(@ModelAttribute MemberDTO memberDTO) {
        System.out.println("MemberController.membershippro");
        System.out.println("memberDTO = " + memberDTO);
        memberService.membership(memberDTO);
        return "redirect:/web/login";
    }

    // 로그인
    @PostMapping("/web/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "test";
        } else {
            // 로그인 실패
            return "redirect:/web/login";
        }
    }
}
