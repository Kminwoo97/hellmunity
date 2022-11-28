package school.healthboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.entity.MoimBoard;

import java.util.List;

public interface MoimBoardRepository extends JpaRepository<MoimBoard, Long> {

    @Query("SELECT C FROM MoimBoard C ORDER BY C.createdDate DESC")
    List<MoimBoard> findAllDesc();

    Page<MoimBoard> findMoimBoardByWriter(Member member, Pageable paging);

    //제목 검색시
    Page<MoimBoard> findMoimBoardByMoimBoardTitleContaining(@Param("moimBoardTitle") String moimBoardTitle, Pageable pageable);

}
