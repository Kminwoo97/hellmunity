package school.healthboard.service;

import school.healthboard.entity.ChatMessage;
import school.healthboard.entity.dto.ChatMessageDto;

public interface ChatMessageService {
    public
    ChatMessage save(ChatMessageDto chatMessageDto);
}
