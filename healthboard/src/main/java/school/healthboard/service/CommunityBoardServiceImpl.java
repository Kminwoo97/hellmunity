package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.repository.CommunityBoardRepository;
import school.healthboard.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityBoardServiceImpl implements CommunityBoardService {

    private final CommunityBoardRepository communityBoardRepository;
    private final MemberRepository memberRepository;

    @Override
    public CommunityBoard save(CommunityBoard communityBoard) {
        return communityBoardRepository.save(communityBoard);
    }

    //findAll - 페이징 처리 O
    @Override
    public Page<CommunityBoard> findAllPage(Pageable pageable) {
        Page<CommunityBoard> all = communityBoardRepository.findAll(pageable);
        return all;
    }

    //findAll - 페이징 처리 X
    @Override
    public List<CommunityBoard> findAll() {
        return communityBoardRepository.findAll();
    }

    @Override
    public Optional<CommunityBoard> findOne(Long boardId) {
        Optional<CommunityBoard> findBoard = communityBoardRepository.findById(boardId);
        if (findBoard == null){
            return null;
        }
        return findBoard;
    }

    @Override
    public CommunityBoard editOne(Long boardId, CommunityBoard communityBoard) {
        Optional<CommunityBoard> findBoard = communityBoardRepository.findById(boardId);
        findBoard.get().setCommunityBoardTitle(communityBoard.getCommunityBoardTitle());
        findBoard.get().setCommunityBoardDetail(communityBoard.getCommunityBoardDetail());
        communityBoardRepository.save(findBoard.get());
        return findBoard.get();
    }

    @Override
    public void delete(Long boardId) {
        communityBoardRepository.deleteById(boardId);
    }

    @Override
    public Page<CommunityBoard> findMyPage(Pageable pageable, Long memberNo) {

        Member findMember = memberRepository.findByMemberNo(memberNo);

        return communityBoardRepository.findCommunityBoardByWriter(findMember, pageable);
    }

    @Override
    public Page<CommunityBoard> searchTitle(String communityBoardTitle, Pageable pageable) {
        return communityBoardRepository.findCommunityBoardByCommunityBoardTitleContaining(communityBoardTitle,pageable);
    }
}
