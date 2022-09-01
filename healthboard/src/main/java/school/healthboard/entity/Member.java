package school.healthboard.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(unique = true)
    private String memberId;
    private String memberPasswd;
    private String memberName;
    private int memberAge;

    @OneToMany(mappedBy = "writer")
    private List<CommunityBoard> communityBoardList = new ArrayList<CommunityBoard>();

    @OneToMany(mappedBy = "writer")
    private List<MoimBoard> moimBoardList = new ArrayList<MoimBoard>();


    @Builder
    public Member(String memberId, String memberPasswd, String memberName) {
        this.memberId = memberId;
        this.memberPasswd = memberPasswd;
        this.memberName = memberName;
    }
}
