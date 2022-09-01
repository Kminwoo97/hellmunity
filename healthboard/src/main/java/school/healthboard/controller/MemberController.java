package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.entity.Member;
import school.healthboard.service.MemberService;
import school.healthboard.validation.CheckMemberIdValidator;
import school.healthboard.validation.CheckMemberNameValidator;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final CheckMemberIdValidator checkMemberIdValidator;
    private final CheckMemberNameValidator checkMemberNameValidator;

    //커스텀 유효성 검증
    @InitBinder
    public void validatorBinder(WebDataBinder binder){
        binder.addValidators(checkMemberIdValidator);
        binder.addValidators(checkMemberNameValidator);
    }


    /**
     * 회원가입 페이지로 이동
     */
    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") MemberSignupDto memberSignupDto) {
        return "/members/addMemberForm";
    }

    /**
     * 회원 가입
     */
    @PostMapping("/members/add")
    public String createMember(@Valid @ModelAttribute("member") MemberSignupDto memberSignupDto, BindingResult bindingResult){

        //회원가입 validation 걸린 경우 회원가입 페이지로 redirect
        //@ModelAttribute 를 사용해서 자동으로 model.addAttriubte()가 되어서 값 유지 가능
        if(bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }

        //회원가입 validation 통과 후, 회원가입 실행
        memberService.createMember(memberSignupDto);
        return "redirect:/";
    }

    /**
     * 회원 중복 체크
     */


    /**
     * 회원 My Page
     */

}
