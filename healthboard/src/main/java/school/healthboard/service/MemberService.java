package school.healthboard.service;

import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.entity.Member;

public interface MemberService {
    /**
     * 회원 가입 관련 메서드
     */
    Member createMember(MemberSignupDto memberSignupDto);
    boolean checkMemberIdDuplication(String memberId);
    boolean checkMemberNameDuplication(String memberName);
    Member findMember(Long memberNo);

    /**
     * 회원 수정
     */
}
