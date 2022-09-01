package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import school.healthboard.entity.Comment;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.entity.dto.CommentDto;
import school.healthboard.service.CommentService;
import school.healthboard.service.CommunityBoardService;
import school.healthboard.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommunityBoardService communityBoardService;
    private final MemberService memberService;


    @PostMapping("/item/{boardId}/comment")
    public String registerComment(@PathVariable("boardId") Long boardId, @Valid @ModelAttribute("comment") CommentDto commentDto,
                                  BindingResult bindingResult,
                                  Model model, HttpServletRequest request) {
        //세션에서 댓글 작성자 번호 가져와서 회원 조회
        HttpSession session = request.getSession(false);
        Long memberNo = (Long) session.getAttribute("memberNo");
        Member writer = memberService.findMember(memberNo);

        Optional<CommunityBoard> board = communityBoardService.findOne(boardId);
        CommunityBoard findBoard = board.get();

        commentDto.setWriter(writer);
        commentDto.setCommunityBoard(findBoard);


        //데이터 세팅
        model.addAttribute("board",findBoard);
        model.addAttribute("comments", findBoard.getCommentList());

        //댓글에 아무것도 입력하지 않을 시, 오류 문구 출력
        if (bindingResult.hasErrors()) {
            return "item/itemShow";
        }

        Comment save = commentService.save(commentDto);

        return "/item/itemShow";
    }
}
