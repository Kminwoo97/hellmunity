package school.healthboard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.MoimBoard;

import java.util.List;
import java.util.Optional;

public interface MoimService {
    /**
     * 모임글 쓰기
     * 모임글 목록 보여주기
     * 모임글 조회하기
     * 모임글 수정하기
     * 모임글 삭제하기
     * 내가 쓴 모임글 조회하기
     */
    MoimBoard save(MoimBoard moimBoard);
    Page<MoimBoard> findAllPage(Pageable pageable);
    List<MoimBoard> findAll();
    Optional<MoimBoard> findOne(Long moimId);
    MoimBoard editOne(Long moimId, MoimBoard moimBoard);
    void delete(Long moimId);
    Page<MoimBoard> findMyPage(Pageable pageable, Long memberNo);

    Page<MoimBoard> searchTitle(String moimBoardTitle, Pageable pageable);
}
