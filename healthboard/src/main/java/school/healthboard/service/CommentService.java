package school.healthboard.service;

import school.healthboard.entity.Comment;
import school.healthboard.entity.dto.CommentDto;

import java.util.List;

public interface CommentService {
    /**
     * 1. 댓글 등록
     * 2. 댓글 수정
     * 3. 댓글 삭제
     * 4. 댓글 조회
     */
    Comment save(CommentDto commentDto);
    Comment edit(CommentDto commentDto);
    Comment delete(CommentDto commentDto);
    List<Comment> findAll();
}
