package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.entity.ChatMessage;
import school.healthboard.entity.ChatRoom;
import school.healthboard.entity.dto.ChatMessageDto;
import school.healthboard.repository.ChatMessageRepository;
import school.healthboard.repository.ChatRoomRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatMessageServiceImpl implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessage save(ChatMessageDto chatMessageDto) {
        ChatMessage chatMessage = chatMessageDto.toEntity();

        //채팅방 조회
        Long chatRoomId = chatMessageDto.getRoomId();
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).get();

        chatMessage.setChatRoom(chatRoom);
        chatMessage.setSendDateTime(LocalDateTime.now());
        return chatMessageRepository.save(chatMessage);
    }
}
