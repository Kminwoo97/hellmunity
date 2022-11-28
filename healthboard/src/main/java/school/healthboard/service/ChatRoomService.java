package school.healthboard.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import school.healthboard.entity.ChatRoom;
import school.healthboard.entity.Member;

import java.util.List;

public interface ChatRoomService {


    //채팅방 개설전에 채팅방 있는지 체킹
    boolean existChatRoom(Long hostId, Long guestId, String roomName);

    //채팅방 개설하기
    ChatRoom createChatRoom(Long hostId, Long guestId, String roomName);

    //내가 속한 채팅방 모두 가져오기
//    List<ChatRoom> findAllChatRoom(Long hostNo, Long guestNo);
    Page<ChatRoom> findAllChatRoomPage(Member host, Member guest, Pageable pageable);

    //채팅방 나가기
    void exitChatRoom(Long roomId);


}
