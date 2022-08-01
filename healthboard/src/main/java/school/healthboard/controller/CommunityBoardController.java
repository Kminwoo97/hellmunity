package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.service.CommunityBoardService;
import school.healthboard.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommunityBoardController {
    /* JSESSIONID 쿠키 값으로 세션 가져온 후, 세션안의 Member 가져오기
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(session.getId());
        System.out.println("member.getMemberNo() = " + member.getMemberNo());
        System.out.println("member.getMemberId() = " + member.getMemberId());
    */

    private final CommunityBoardService communityBoardService;
    private final MemberService memberService;

    //1. 아이템 목록으로 이동
    @GetMapping("/api/items")
    public String boardAll(Model model, HttpServletRequest request) {
        List<CommunityBoard> boardList = communityBoardService.findAll();
        model.addAttribute("boardList", boardList);

        return "/item/itemList";
    }

    //2. 아이템 등록으로 이동
    @GetMapping("/api/item/add")
    public String boardSaveForm(@ModelAttribute("communityBoard") CommunityBoard communityBoard, Model model) {
        model.addAttribute("member", communityBoard.getWriter());
        return "/item/itemForm";
    }

    //3. 아이템 등록 - 현재 로그인 사용자 id값 가져오기
    @PostMapping("/api/item/add")
    public String boardSave(@ModelAttribute("communityBoard") CommunityBoard communityBoard, HttpServletRequest request) {
        //세션에 저장된 회원 정보 가져오기
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute(session.getId());

        communityBoard.setWriter(member);

        communityBoardService.save(communityBoard);

        return "redirect:/api/items";
    }
}