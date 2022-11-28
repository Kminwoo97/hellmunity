package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.MoimBoard;
import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.entity.Member;
import school.healthboard.repository.MoimBoardRepository;
import school.healthboard.service.CommunityBoardService;
import school.healthboard.service.MemberService;
import school.healthboard.service.MoimService;
import school.healthboard.validation.CheckMemberIdValidator;
import school.healthboard.validation.CheckMemberNameValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final CommunityBoardService communityBoardService;
    private final MoimService moimService;
    private final CheckMemberIdValidator checkMemberIdValidator;
    private final CheckMemberNameValidator checkMemberNameValidator;

    //커스텀 유효성 검증
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkMemberIdValidator);
        binder.addValidators(checkMemberNameValidator);
    }


    /**
     * 회원가입 페이지로 이동
     */
    @GetMapping("/members/add")
    public String addForm(@ModelAttribute("member") MemberSignupDto memberSignupDto) {
        return "/front-end/signup";
    }

    /**
     * 회원 가입
     */
    @PostMapping("/members/add")
    public String createMember(@Valid @ModelAttribute("member") MemberSignupDto memberSignupDto, BindingResult bindingResult) {

        //회원가입 validation 걸린 경우 회원가입 페이지로 redirect
        //@ModelAttribute 를 사용해서 자동으로 model.addAttriubte()가 되어서 값 유지 가능
        if (bindingResult.hasErrors()) {
            return "/front-end/signup";
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
    //마이페이지 - 내정보
    @GetMapping("/member/mypage")
    public String mypage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");

        Member member = memberService.findMember(memberNo);
        model.addAttribute("member", member);

        return "/front-end/my-page";
    }

    //마이페이지 - Q&A List
    @GetMapping("/member/mypage/qnaList")
    public String myqna(HttpServletRequest request, Model model,
                        @PageableDefault(page = 0, size = 5, sort = "communityBoardId", direction = Sort.Direction.DESC) Pageable pageable) {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);
        model.addAttribute("member", member);

        Page<CommunityBoard> boardList = communityBoardService.findMyPage(pageable, memberNo);
        model.addAttribute("boardList", boardList);
        model.addAttribute("maxPage", 10);
        return "/front-end/my-qna";
    }

    //마이페이지 - 모임글 List
    @GetMapping("/member/mypage/moimList")
    public String mymoim(HttpServletRequest request, Model model,
                         @PageableDefault(page = 0, size = 5, sort = "moimBoardId", direction = Sort.Direction.DESC) Pageable pageable) {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long) session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);
        model.addAttribute("member", member);

        Page<MoimBoard> moimList = moimService.findMyPage(pageable, memberNo);
        model.addAttribute("moimList", moimList);
        model.addAttribute("maxPage", 10);

        return "/front-end/my-moim";
    }

    //마이페이지 - 채팅방 List
    @GetMapping("/member/mypage/chatList")
    public String mychat(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        Long memberNo = (Long)session.getAttribute("memberNo");

        return "/front-end/my-page";
    }
}
