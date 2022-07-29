package school.healthboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.healthboard.entity.CommunityBoard;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {
}
