package school.healthboard.dto;


import lombok.Data;
import school.healthboard.entity.Member;

import javax.validation.constraints.NotEmpty;

//회원 가입 dto
@Data
public class MemberSignupDto {

    @NotEmpty(message = "필수 입력 값입니다.")
    private String memberId;
    @NotEmpty(message = "필수 입력 값입니다.")
    private String memberPassword;
    @NotEmpty(message = "필수 입력 값입니다.")
    private String memberName;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .memberNickname(memberName)
                .memberPasswd(memberPassword)
                .build();
    }
}
