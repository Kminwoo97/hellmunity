package school.healthboard.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commentContent;


    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "communityBoardId")
    private CommunityBoard board;

    @Builder
    public Comment(String commentContent, Member writer, CommunityBoard board) {
        this.commentContent = commentContent;
        this.writer = writer;
        this.board = board;
    }
}
