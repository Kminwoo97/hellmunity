package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.healthboard.entity.ChatRoom;
import school.healthboard.entity.Member;
import school.healthboard.repository.ChatRoomRepository;
import school.healthboard.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberRepository memberRepository;

    @Override
    public boolean existChatRoom(Long hostId, Long guestId, String roomName) {

        Member host = memberRepository.findById(hostId).get();
        Member guest = memberRepository.findById(guestId).get();

        return chatRoomRepository.existsChatRoomByRoomGuestAndAndRoomHostAndRoomName(guest,host,roomName);
    }

    @Override
    public ChatRoom createChatRoom(Long hostId, Long guestId, String roomName) {

        ChatRoom chatRoom = new ChatRoom();

        Member host = memberRepository.findById(hostId).get();
        Member guest = memberRepository.findById(guestId).get();

        chatRoom.setRoomHost(host);
        chatRoom.setRoomGuest(guest);
        chatRoom.setRoomName(roomName);

        return chatRoomRepository.save(chatRoom);
    }

//    @Override
//    public List<ChatRoom> findAllChatRoom(Long hostNo, Long guestNo) {
//        Member findMember = memberRepository.findById(hostNo).get();
//        return chatRoomRepository.findChatRoomByRoomHostOrRoomGuest(findMember, findMember);
//    }

    @Override
    public Page<ChatRoom> findAllChatRoomPage(Member host, Member guest, Pageable pageable) {
        Page<ChatRoom> chatRoomByRoomHostOrRoomGuest = chatRoomRepository.findChatRoomByRoomHostOrRoomGuest(host, guest, pageable);
        System.out.println(chatRoomByRoomHostOrRoomGuest.isEmpty());
        return chatRoomByRoomHostOrRoomGuest;
    }

    @Override
    public void exitChatRoom(Long roomId) {
        chatRoomRepository.deleteById(roomId);
    }

//    @Override
//    public ChatRoom createChatRoom(String roomName) {
//        ChatRoom chatRoom = new ChatRoom();
//        chatRoom.setRoomName(roomName);
//        return chatRoomRepository.save(chatRoom);
//    }
}
