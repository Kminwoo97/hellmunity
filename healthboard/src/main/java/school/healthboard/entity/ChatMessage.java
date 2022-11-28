package school.healthboard.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ChatId;

    private Long writer; //보내는 사람 - No
    private String writer_name;//보내는 사람 - 닉네임
    private Long receiver; //받는 사람 - No
    private String receiver_name; //받는 사람 - 닉네임
    private String message;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private ChatRoom chatRoom;

    @CreatedDate
    @Column
    private LocalDateTime sendDateTime;

}
