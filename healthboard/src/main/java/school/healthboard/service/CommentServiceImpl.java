package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.entity.Comment;
import school.healthboard.entity.dto.CommentDto;
import school.healthboard.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    public Comment save(CommentDto commentDto) {
        Comment comment = commentDto.toEntity();

        Comment save = commentRepository.save(comment);

        return save;
    }

    @Override
    public Comment edit(Long commentId, String editContent) {
        Comment comment = commentRepository.findById(commentId).get();
        comment.setCommentContent(editContent);
        Comment saveComment = commentRepository.save(comment);
        return saveComment;
    }

    @Override
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

}
