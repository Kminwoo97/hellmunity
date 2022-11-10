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
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommunityBoardService communityBoardService;
    private final MemberService memberService;


    //댓글 등록 - Create
    @PostMapping("/question/{boardId}/comment")
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
            return "/front-end/qnaDetail";
        }

        Comment save = commentService.save(commentDto);

//        return "/front-end/qnaDetail";
        return "redirect:/question/"+boardId;
    }


    //댓글 수정 - Update
    @PostMapping("/question/{boardId}/comment/{commentId}/edit")
    public String editComment(@PathVariable("boardId") Long boardId,
                              @PathVariable("commentId") Long commentId,
                              @Valid @ModelAttribute("comment") String editContent, BindingResult bindingResult,
                              Model model, HttpServletRequest request){

        commentService.edit(commentId, editContent);

        //수정 했을 떄, 새로고침 없이 화면에 데이터 보여주기 위해서 comments 와 board 담는다.
        Optional<CommunityBoard> board = communityBoardService.findOne(boardId);
        CommunityBoard findBoard = board.get();
        List<Comment> commentList = findBoard.getCommentList();
        model.addAttribute("comments", commentList);
        model.addAttribute("board",findBoard);

        return "/front-end/qnaDetail" + " :: #commentList";

    }

    //댓글 삭제 - Delete
    @PostMapping("/question/{boardId}/comment/{commentId}/delete")
    public String registerComment(@PathVariable("boardId") Long boardId,
                                  @PathVariable("commentId") Long commentId,
                                  Model model, HttpServletRequest request) {

        commentService.delete(commentId);

        //삭제를 했을 떄, 새로고침 없이 화면에 데이터 보여주기 위해서 comments 와 board 담는다.
        Optional<CommunityBoard> board = communityBoardService.findOne(boardId);
        CommunityBoard findBoard = board.get();
        List<Comment> commentList = findBoard.getCommentList();
        model.addAttribute("comments", commentList);
        model.addAttribute("board",findBoard);

        return "/front-end/qnaDetail" + " :: #commentList";
    }
}
