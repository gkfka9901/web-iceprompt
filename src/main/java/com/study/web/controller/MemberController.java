package com.study.web.controller;

import com.study.web.dto.MemberDTO;
import com.study.web.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/web/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/web/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // 로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        } else {
            // 로그인 실패
            return "redirect:/web/login";
        }
    }

    @GetMapping("/web/member/manage")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberlist";
    }

    @GetMapping("/web/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/web/member/update")
    public String updateForm(HttpSession sesssion, Model model) {
        String myEmail = (String) sesssion.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @PostMapping("/web/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/web/member/" + memberDTO.getId();
    }

    @GetMapping("/web/member/delete/{id}")
    public String delete(@PathVariable Long id) {
        memberService.deleteById(id);
        return "redirect:/web/member/manage";
    }

    @GetMapping("/web/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/";
    }

    @PostMapping("/web/member/email-check")
    public  @ResponseBody String emailCheck(@RequestParam("memberEmail") String memberEmail) {
        System.out.println("memberEmail = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }
//        if (checkResult != null) {
//            return "ok";
//        } else {
//            return "no";
//        }
//    }
}
