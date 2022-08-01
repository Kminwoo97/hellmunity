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
public class MoimBoard {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moimBoardId;

    private String moimBoardTitle;
    private LocalDateTime moimBoardDay;

    @CreatedDate
    private String moimBoardPlace;
    @LastModifiedBy
    private String moimBoardImage;

    private int moimBoardTotalPerson;
    private int moimBoardCurPerson;


    @ManyToOne
    @JoinColumn(name = "memberNo")
    private Member writer;

}
