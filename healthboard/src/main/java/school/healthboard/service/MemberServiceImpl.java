package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.entity.Member;
import school.healthboard.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberSignupDto memberSignupDto) {
        Member member = memberSignupDto.toEntity();
        Member saveMember = memberRepository.save(member);
        return saveMember;
    }

    @Override
    public Member findMember(Long memberNo) {
        Member findMember = memberRepository.findByMemberNo(memberNo);
        return findMember;
    }

    @Override
    public boolean checkMemberIdDuplication(String memberId) {
        return memberRepository.existsByMemberId(memberId);
    }

    @Override
    public boolean checkMemberNameDuplication(String memberName) {
        return memberRepository.existsByMemberName(memberName);
    }

}
