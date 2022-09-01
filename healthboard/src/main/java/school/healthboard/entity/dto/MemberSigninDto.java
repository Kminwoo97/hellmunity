package school.healthboard.entity.dto;

import lombok.Data;
import school.healthboard.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

//로그인 DTO
@Data
public class MemberSigninDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
}
