package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.dto.MemberSigninDto;
import school.healthboard.dto.MemberSignupDto;
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
}
