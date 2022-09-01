package school.healthboard.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.repository.MemberRepository;
import school.healthboard.validation.AbstractValidator;

@RequiredArgsConstructor
@Component
public class CheckMemberIdValidator extends AbstractValidator<MemberSignupDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberSignupDto dto, Errors errors) {
        if (memberRepository.existsByMemberId(dto.getMemberId())) {
            errors.rejectValue("memberId", "아이디 중복 오류", "이미 사용중인 아이디 입니다.");
        }
    }
}
