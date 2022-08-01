package school.healthboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class CommunityBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityBoardId;

    private String communityBoardTitle;
    private String communityBoardDetail;

    private String communityBoardImage;

    @CreatedDate
    private LocalDateTime communityBoardRegister;
    @LastModifiedBy
    private LocalDateTime communityBoardUpdate;

    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

    @OneToMany(mappedBy = "commentId")
    private List<Comment> commentList = new ArrayList<>();

}
