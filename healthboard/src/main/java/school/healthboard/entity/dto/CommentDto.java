package school.healthboard.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import school.healthboard.entity.Comment;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class CommentDto {

    private CommunityBoard communityBoard;
    private Member writer;
    @NotBlank(message = "내용을 입력해주세요")
    private String content;



    public Comment toEntity(){
        return Comment.builder()
                .board(communityBoard)
                .writer(writer)
                .commentContent(content)
                .build();
    }

}
