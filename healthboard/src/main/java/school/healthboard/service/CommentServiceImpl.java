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
    public Comment edit(CommentDto commentDto) {
        return null;
    }

    @Override
    public Comment delete(CommentDto commentDto) {
        return null;
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }
}
