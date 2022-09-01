package school.healthboard.service;

import school.healthboard.entity.CommunityBoard;

import java.util.List;
import java.util.Optional;

public interface CommunityBoardService {
    /**
     * 게시글 쓰기
     * 게시글 목록 보여주기
     * 게시글 조회하기
     * 게시글 수정하기
     * 게시글 삭제하기
     */
    CommunityBoard save(CommunityBoard communityBoard);
    List<CommunityBoard> findAll();
    Optional<CommunityBoard> findOne(Long boardId);
    CommunityBoard editOne(Long boardId, CommunityBoard communityBoard);
    void delete(Long boardId);

}
