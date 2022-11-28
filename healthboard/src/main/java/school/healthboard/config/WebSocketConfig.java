package school.healthboard.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker //STOMP 사용하기 위해서 선언하는 어노테이션
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //html에서 SockJs 를 연결하는 경로가 EndPoint 이다.
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://localhost:8080")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {

        /**해당 경로로 시작하는 STOMP 메시지의 "destination" 헤더는 @Controller 객체의 @MessageMapping 메서드로 라우팅
        Clinet에서 SEND 요청을 처리한다. */
        //stomp.send() 호출 - 메시지 보낼 때
        config.setApplicationDestinationPrefixes("/pub");

        //topic, queue 로 시작하는 "destination" 헤더를 가진 메시지를 브로커로 라우팅한다.
        /**해당 경로로 SimpleBroker를 등록. SimpleBroker는 해당하는 경로를 Subscribe 하
         * Client에게 메시지를 전달하는 간단한 작업을 수행한다. */
        //stomp.subscribe() 호출 - 메시지 받을 때 -> 여기가 아마도 화면에 뿌려주는 거 같다.
        config.enableSimpleBroker("/sub");
    }
}
