package school.healthboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import school.healthboard.entity.ChatMessage;
import school.healthboard.entity.ChatRoom;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    //채팅방의 모든 메시지 조회
    List<ChatMessage> findChatMessageByChatRoom(ChatRoom chatRoom);
}
