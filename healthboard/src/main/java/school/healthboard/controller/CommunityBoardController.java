package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import school.healthboard.entity.Comment;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.entity.dto.CommentDto;
import school.healthboard.service.CommunityBoardService;
import school.healthboard.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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

    //1. 게시글 목록으로 이동
    @GetMapping("/items")
    public String boardAll(Model model, HttpServletRequest request) {
        List<CommunityBoard> boardList = communityBoardService.findAll();
        model.addAttribute("boardList", boardList);

        return "/item/itemList";
    }

    //2. 게시글 등록으로 이동
    @GetMapping("/item/add")
    public String boardSaveForm(@ModelAttribute("communityBoard") CommunityBoard communityBoard, Model model) {
        model.addAttribute("member", communityBoard.getWriter());
        return "/item/itemForm";
    }

    //3. 게시글 등록 - 현재 로그인 사용자 id값 가져오기
    @PostMapping("/item/add")
    public String boardSave(@ModelAttribute("communityBoard") CommunityBoard communityBoard, HttpServletRequest request) {
        //세션에 저장된 회원 정보 가져오기
        HttpSession session = request.getSession();
        Long memberNo = (Long)session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);


        communityBoard.setWriter(member);

        communityBoardService.save(communityBoard);

        return "redirect:/items";
    }

    //4. 특정 게시글 상세보기
    @GetMapping("/item/{boardId}")
    public String boardFind(@PathVariable Long boardId, @ModelAttribute("comment") CommentDto commentDto,
                            Model model, HttpServletRequest request){

        Optional<CommunityBoard> findBoard = communityBoardService.findOne(boardId);
        if(findBoard.isEmpty()){
            //에러 메시지 출력하고
            return "redirect:/items";
        }
        CommunityBoard communityBoard = findBoard.get();
        List<Comment> commentList = communityBoard.getCommentList();

        //게시판 정보 세팅, 댓글 세팅
        model.addAttribute("board", communityBoard);
        model.addAttribute("comments", commentList);

        return "/item/itemShow";
    }

    //5. 게시글 수정 - 이동
    @GetMapping("/item/{boardId}/edit")
    public String boardEditPage(@PathVariable Long boardId, @ModelAttribute("communityBoard") CommunityBoard communityBoard,
                                HttpServletRequest request, Model model) {
        //게시글을 작성한 사용자 가저오기
        Optional<CommunityBoard> findBoard = communityBoardService.findOne(boardId);
        Member writer = findBoard.get().getWriter();

        //로그인 한 사용자 가져오기
        HttpSession session = request.getSession();
        Long memberNo = (Long) session.getAttribute("memberNo");

        if (!memberNo.equals(writer.getMemberNo())) {
            System.out.println("작성자가 아닙니다.");
            return "redirect:/items";
        }


        CommunityBoard board = findBoard.get();
        communityBoard.setCommunityBoardTitle(board.getCommunityBoardTitle());
        communityBoard.setCommunityBoardDetail(board.getCommunityBoardDetail());
        return "/item/itemUpdateForm";
    }

    //5. 게시글 수정 - 수정
    @PostMapping("/item/{boardId}/edit")
    public String boardEdit(@PathVariable Long boardId, @ModelAttribute("communityBoard") CommunityBoard communityBoard){
        communityBoardService.editOne(boardId,communityBoard);
        return "redirect:/items";
    }

    //6. 게시글 삭제
    @PostMapping("/item/{boardId}/delete")
    public String boardRemove(@PathVariable Long boardId, HttpServletRequest request){
        communityBoardService.delete(boardId);
        return "redirect:/items";
    }
}