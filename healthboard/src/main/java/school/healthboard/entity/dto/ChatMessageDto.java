package school.healthboard.entity.dto;

import lombok.*;
import school.healthboard.entity.ChatMessage;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDto {

    private Long roomId; //채팅방
    private Long writer; //보내는 사람 - Long
    private String writer_name; // 보내는 사람 - String
    private Long receiver; //받는 사람 - No
    private String receiver_name; //받는 사람 - 닉네임
    private String message; //메시지 내용



    public ChatMessage toEntity(){
        return ChatMessage.builder()
                .message(message)
                .writer(writer)
                .writer_name(writer_name)
                .receiver(receiver)
                .receiver_name(receiver_name)
                .build();
    }
}
