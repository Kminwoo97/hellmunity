package school.healthboard.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import school.healthboard.entity.dto.MemberSignupDto;
import school.healthboard.repository.MemberRepository;

@RequiredArgsConstructor
@Component
public class CheckMemberNameValidator extends AbstractValidator<MemberSignupDto> {

    private final MemberRepository memberRepository;

    @Override
    protected void doValidate(MemberSignupDto dto, Errors errors) {
        if (memberRepository.existsByMemberId(dto.getMemberName())) {
            errors.rejectValue("memberName", "닉네임 중복 오류", "이미 사용중인 닉네임 입니다.");
        }
    }
}
