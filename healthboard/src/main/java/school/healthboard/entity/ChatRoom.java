package school.healthboard.entity;

import lombok.Getter;
import lombok.Setter;
import school.healthboard.entity.ChatMessage;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    private String roomName;

    @ManyToOne
    @JoinColumn(referencedColumnName = "memberNo", name = "hostMemberNo")
    private Member roomHost;


    @ManyToOne
    @JoinColumn(referencedColumnName = "memberNo", name = "guestMemberNo")
    private Member roomGuest;

    @OneToMany(mappedBy = "chatRoom", orphanRemoval = true)
    private List<ChatMessage> chatMessageList = new ArrayList<>();
}
