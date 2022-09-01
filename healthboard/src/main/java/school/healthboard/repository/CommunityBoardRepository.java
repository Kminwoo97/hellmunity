package school.healthboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import school.healthboard.entity.CommunityBoard;

import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {
    @Query("SELECT C FROM CommunityBoard C ORDER BY C.createdDate DESC")
    List<CommunityBoard> findAllDesc();
}
