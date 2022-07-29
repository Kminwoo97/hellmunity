package school.healthboard.service;

import school.healthboard.dto.MemberSignupDto;
import school.healthboard.entity.Member;

public interface MemberService {
    /**
     * 회원 가입
     */
    Member createMember(MemberSignupDto memberSignupDto);


    /**
     * 회원 수정
     */
}
