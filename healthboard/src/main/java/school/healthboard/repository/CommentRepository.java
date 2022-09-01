package school.healthboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.healthboard.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
