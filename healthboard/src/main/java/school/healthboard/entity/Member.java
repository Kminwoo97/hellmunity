package school.healthboard.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;

    @Column(unique = true)
    private String memberId;
    private String memberPasswd;
    private String memberNickname;
    private int memberAge;

    @OneToMany(mappedBy = "communityBoardId")
    private List<CommunityBoard> communityBoardList = new ArrayList<CommunityBoard>();

    @OneToMany(mappedBy = "moimBoardId")
    private List<MoimBoard> moimBoardList = new ArrayList<MoimBoard>();


    @Builder
    public Member(String memberId, String memberPasswd, String memberNickname) {
        this.memberId = memberId;
        this.memberPasswd = memberPasswd;
        this.memberNickname = memberNickname;
    }
}
