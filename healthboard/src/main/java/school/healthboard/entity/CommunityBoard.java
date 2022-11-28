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
public class CommunityBoard extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long communityBoardId;

    private String communityBoardTitle;
    private String communityBoardDetail;

//    private String communityBoardImage;


    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

    @OneToMany(mappedBy = "board", orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    public void setWriter(Member writer) {
        this.writer = writer;
        writer.getCommunityBoardList().add(this);
    }
}
