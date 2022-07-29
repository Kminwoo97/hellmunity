package school.healthboard.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commentContent;

    @CreatedDate
    private LocalDateTime commentRegister;
    @LastModifiedBy
    private LocalDateTime commentUpdate;


    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

    @ManyToOne
    @JoinColumn(name = "communityBoardId")
    private CommunityBoard board;

}
