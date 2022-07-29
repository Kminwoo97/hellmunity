package school.healthboard.dto;

import lombok.Data;
import school.healthboard.entity.Member;

import javax.validation.constraints.NotEmpty;

//로그인 DTO
@Data
public class MemberSigninDto {

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String password;
}
