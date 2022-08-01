package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import school.healthboard.dto.MemberSigninDto;
import school.healthboard.dto.MemberSignupDto;
import school.healthboard.entity.Member;
import school.healthboard.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    /**
     * 회원가입 페이지로 이동
     */
    @GetMapping("/api/members/add")
    public String addForm(@ModelAttribute("member") MemberSignupDto memberSignupDto) {
        return "/members/addMemberForm";
    }

    /**
     * 회원 가입
     */
    @PostMapping("/api/members/add")
    public String createMember(@Valid @ModelAttribute("member") MemberSignupDto memberSignupDto, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
            System.out.println(bindingResult.getFieldErrors());
            return "members/addMemberForm";
        }

        Member createMember = memberService.createMember(memberSignupDto);
        return "redirect:/api";
    }

}
