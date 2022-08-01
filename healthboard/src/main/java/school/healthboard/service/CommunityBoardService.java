package school.healthboard.service;

import school.healthboard.entity.CommunityBoard;

import java.util.List;

public interface CommunityBoardService {
    /**
     * 1. 글 쓰기
     * 2. 글 목록 보여주기
     * 3. 글 삭제
     */
    CommunityBoard save(CommunityBoard communityBoard);
    List<CommunityBoard> findAll();
}
