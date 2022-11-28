package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.entity.MoimBoard;
import school.healthboard.repository.MemberRepository;
import school.healthboard.repository.MoimBoardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoimServiceImpl implements MoimService {

    private final MoimBoardRepository moimBoardRepository;
    private final MemberRepository memberRepository;

    @Override
    public MoimBoard save(MoimBoard moimBoard) {
        return moimBoardRepository.save(moimBoard);
    }

    @Override
    public Page<MoimBoard> findAllPage(Pageable pageable) {
        Page<MoimBoard> all = moimBoardRepository.findAll(pageable);
        return all;
    }

    @Override
    public List<MoimBoard> findAll() {
        return null;
    }

    @Override
    public Optional<MoimBoard> findOne(Long moimId) {
        return moimBoardRepository.findById(moimId);
    }

    @Override
    public MoimBoard editOne(Long moimId, MoimBoard moimBoard) {
        Optional<MoimBoard> findBoard = moimBoardRepository.findById(moimId);
        findBoard.get().setMoimBoardTitle(moimBoard.getMoimBoardTitle());
        findBoard.get().setMoimBoardDetail(moimBoard.getMoimBoardDetail());
        moimBoardRepository.save(findBoard.get());
        return findBoard.get();
    }

    @Override
    public void delete(Long moimId) {
        moimBoardRepository.deleteById(moimId);
    }

    @Override
    public Page<MoimBoard> findMyPage(Pageable pageable, Long memberNo) {
        Member findMember = memberRepository.findByMemberNo(memberNo);

        return moimBoardRepository.findMoimBoardByWriter(findMember, pageable);
    }

    @Override
    public Page<MoimBoard> searchTitle(String moimBoardTitle, Pageable pageable) {
        return moimBoardRepository.findMoimBoardByMoimBoardTitleContaining(moimBoardTitle, pageable);
    }
}
