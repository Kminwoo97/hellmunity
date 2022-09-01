package school.healthboard.entity.dto;


import lombok.Data;
import school.healthboard.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

//회원 가입 dto
@Data
public class MemberSignupDto {

    @NotBlank(message = "아이디를 입력해주세요")
    @Pattern(regexp = "[a-zA-Z0-9]{5,15}",
            message = "아이디는 영문, 숫자만 가능하며 5 ~ 15자리까지 가능합니다.")
    private String memberId;
    
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-zA-Z])[0-9a-zA-Z]{6,15}",
            message = "비밀번호는 영문과 숫자 조합으로 6 ~ 15자리까지 가능합니다.")
    private String memberPassword;

    @NotBlank(message = "닉네임을 입력해주세요")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$" , message = "닉네임은 특수문자를 포함하지 않은 2~10자리여야 합니다.")
    private String memberName;

    public Member toEntity(){
        return Member.builder()
                .memberId(memberId)
                .memberName(memberName)
                .memberPasswd(memberPassword)
                .build();
    }
}
