package school.healthboard.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import school.healthboard.entity.ChatRoom;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    //채팅방 생성시 이미 존재하는지 확인
    boolean existsChatRoomByRoomGuestAndAndRoomHostAndRoomName(Member guest, Member host, String roomName);

    //내가 속한 채팅방 모두 가져오기
//    List<ChatRoom> findChatRoomByRoomHostOrRoomGuest(Member hostNo, Member guestNo);
    Page<ChatRoom> findChatRoomByRoomHostOrRoomGuest(Member host, Member guest, Pageable pageable);
}
