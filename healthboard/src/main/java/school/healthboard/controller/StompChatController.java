package school.healthboard.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import school.healthboard.entity.ChatMessage;
import school.healthboard.entity.dto.ChatMessageDto;
import school.healthboard.service.ChatMessageService;

@RequiredArgsConstructor
@Slf4j
@Controller
public class StompChatController {

    //특정 Broker로 메시지를 전달
    private final SimpMessagingTemplate template;

    private final ChatMessageService chatMessageService;

    /**
     * Client가 SEND 할 수 있는 경로
     * StompConfig 에서 설정한  applicationDestinationPrefixs와 @MessageMapping 경로가 병합된다.
     * ex) "/pub/chat/enter" -> /pub 이 prefix 로 설정한 경로, 그 뒤에는 내가 설정한 경로
     */
    // -> /pub/chat/enter 로 발행 요청을 하게되면 /sub/chat/room/{roomId} 로 메시지를 보내준다.
    // -> 채팅방 입장시, 참여 메시지 전송한다.
    @MessageMapping("/chat/enter")
    public void enter(@Payload String message) throws JsonProcessingException {
        //message -> roomId(Long), writer(Long) 받음
        System.out.println("/pub/chat/enter : " + message);
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageDto chatMessageDto = mapper.readValue(message, ChatMessageDto.class);

        //메시지 저장하기
        chatMessageService.save(chatMessageDto);

        template.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), "{'roomId' : 1, 'message' : asdfas , 'writer' :1}");
    }

    @MessageMapping("/chat/message")
    public void message(@Payload String message) throws JsonProcessingException {
        System.out.println("/pub/chat/enter : " + message);
        ObjectMapper mapper = new ObjectMapper();
        ChatMessageDto chatMessageDto = mapper.readValue(message, ChatMessageDto.class);

        //메시지 저장하기
        ChatMessage save = chatMessageService.save(chatMessageDto);

        Gson gson = new Gson();
        String returnMessage = gson.toJson(chatMessageDto);

        template.convertAndSend("/sub/chat/room/" + chatMessageDto.getRoomId(), returnMessage);
    }

}
