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
//    private int memberAge;

    @OneToMany(mappedBy = "writer", orphanRemoval = true)
    private List<CommunityBoard> communityBoardList = new ArrayList<CommunityBoard>();

    @OneToMany(mappedBy = "writer",orphanRemoval = true)
    private List<MoimBoard> moimBoardList = new ArrayList<MoimBoard>();

    @OneToMany(mappedBy = "roomHost",orphanRemoval = true)
    private List<ChatRoom> hostChatRoomList = new ArrayList<>();

    @OneToMany(mappedBy = "roomGuest",orphanRemoval = true)
    private List<ChatRoom> guestChatRoomList = new ArrayList<>();


    @Builder
    public Member(String memberId, String memberPasswd, String memberName) {
        this.memberId = memberId;
        this.memberPasswd = memberPasswd;
        this.memberName = memberName;
    }
}
