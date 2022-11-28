package school.healthboard.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class MoimBoard extends BaseTimeEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimBoardId;
    private String moimBoardTitle;
    private String moimBoardDetail;

//    private LocalDateTime moimBoardDay;
//    private int moimBoardTotalPerson;
//    private int moimBoardCurPerson;


    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

    public void setWriter(Member writer) {
        this.writer = writer;
        writer.getMoimBoardList().add(this);
    }
}
