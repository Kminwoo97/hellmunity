package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import school.healthboard.entity.Comment;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.entity.MoimBoard;
import school.healthboard.entity.dto.CommentDto;
import school.healthboard.service.MemberService;
import school.healthboard.service.MoimService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MoimController {

    private final MemberService memberService;
    private final MoimService moimService;


    //1-2 게시글 목록으로 이동 - 페이징 처리 O
    @GetMapping("/moim")
    public String boardAll(Model model,
                           @PageableDefault(page = 0, size = 5, sort = "moimBoardId", direction = Sort.Direction.DESC) Pageable pageable,
                           String searchTitle) {
        Page<MoimBoard> moimList = moimService.findAllPage(pageable);
        model.addAttribute("moimList", moimList);
        model.addAttribute("maxPage", 10);

        return "/front-end/moim";
    }

    @GetMapping("/moim/search")
    public String boardSearch(@RequestParam("moimBoardTitle") String moimBoardTitle,
                           @PageableDefault(page = 0, size = 5, sort = "moimBoardId", direction = Sort.Direction.DESC) Pageable pageable,
                           Model model,
                           String searchTitle) {
        Page<MoimBoard> moimList = moimService.searchTitle(moimBoardTitle, pageable);
        model.addAttribute("moimList", moimList);
        model.addAttribute("maxPage", 10);

        return "/front-end/moim";
    }

    //2-1. 모임 등록으로 이동
    @GetMapping("/moim/add")
    public String boardSaveForm(@ModelAttribute("moimBoard") MoimBoard moimBoard, Model model) {
        model.addAttribute("member", moimBoard.getWriter());
        return "/front-end/moim-register";
    }

    //2-2. 모임 등록 - 현재 로그인 사용자 id값 가져오기
    @PostMapping("/moim/add")
    public String boardSave(@ModelAttribute("moimBoard") MoimBoard moimBoard, HttpServletRequest request) {
        //세션에 저장된 회원 정보 가져오기
        HttpSession session = request.getSession();
        Long memberNo = (Long)session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);


        moimBoard.setWriter(member);

        moimService.save(moimBoard);

        return "redirect:/moim";
    }

    //3. 특정 모임글 상세보기
    @GetMapping("/moim/{moimId}")
    public String boardFind(@PathVariable Long moimId,
                            Model model, HttpServletRequest request){

        Optional<MoimBoard> findBoard = moimService.findOne(moimId);
        if(findBoard.isEmpty()){
            //에러 메시지 출력하고
            return "redirect:/moim";
        }
        MoimBoard moimBoard = findBoard.get();

        //모임 정보 세팅, 댓글 세팅
        model.addAttribute("moim", moimBoard);

        return "/front-end/moimDetail";
    }

    //4-1. 모임글 수정 - 이동
    @GetMapping("/moim/{moimId}/edit")
    public String boardEditPage(@PathVariable Long moimId,
                                HttpServletRequest request, Model model) {
        //게시글을 작성한 사용자 가저오기
        Optional<MoimBoard> findBoard = moimService.findOne(moimId);
        Member writer = findBoard.get().getWriter();

        //로그인 한 사용자 가져오기
        HttpSession session = request.getSession();
        Long memberNo = (Long) session.getAttribute("memberNo");

        if (!memberNo.equals(writer.getMemberNo())) {
            System.out.println("작성자가 아닙니다.");
            return "redirect:/moim";
        }


        MoimBoard moim = findBoard.get();
        model.addAttribute("moimBoard", moim);
//        return "/items/itemUpdateForm";
        return "/front-end/moim-edit";
    }

    //4-2. 모임글 수정 - 수정
    @PostMapping("/moim/{moimId}/edit")
    public String boardEdit(@PathVariable Long moimId, @ModelAttribute("moimBoard") MoimBoard moimBoard){
        moimService.editOne(moimId,moimBoard);
        return "redirect:/moim/"+moimId;
    }

    //5. 게시글 삭제
    @PostMapping("/moim/{moimId}/delete")
    public String boardRemove(@PathVariable Long moimId, HttpServletRequest request){
        moimService.delete(moimId);
        return "redirect:/moim";
    }
}
