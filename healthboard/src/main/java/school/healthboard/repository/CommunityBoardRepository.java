package school.healthboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;

import java.util.List;

public interface CommunityBoardRepository extends JpaRepository<CommunityBoard, Long> {
    @Query("SELECT C FROM CommunityBoard C ORDER BY C.createdDate DESC")
    List<CommunityBoard> findAllDesc();

    Page<CommunityBoard> findCommunityBoardByWriter(Member member, Pageable paging);

    //제목 검색시
    Page<CommunityBoard> findCommunityBoardByCommunityBoardTitleContaining(@Param("communityBoardTitle") String communityBoardTitle, Pageable pageable);
}
