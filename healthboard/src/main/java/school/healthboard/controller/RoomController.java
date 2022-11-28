package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import school.healthboard.entity.ChatMessage;
import school.healthboard.entity.Member;
import school.healthboard.entity.ChatRoom;
import school.healthboard.repository.ChatMessageRepository;
import school.healthboard.repository.ChatRoomRepository;
import school.healthboard.service.ChatRoomService;
import school.healthboard.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RoomController {

    private final ChatRoomRepository roomRepository;
    private final ChatRoomService chatRoomService;
    private final ChatMessageRepository chatMessageRepository;
    private final MemberService memberService;

    //방 이름 = 모임 글 제목 | GuestNo | HostNo -> 이 3가지 정보로 채팅방 조회
    @PostMapping("/chat/new")
    public String createChatRoom(HttpServletRequest request) {
        //채팅하기를 누른 사용자No 가져오기 - Guest
        HttpSession session = request.getSession(false);
        Long guestId = (Long) session.getAttribute("memberNo");


        //모임글 올린 사람이 채팅주인 - Host
        Long hostId = Long.valueOf(request.getParameter("hostId"));

        //모임글 제목 = 채팅방 이름
        String moimTitle = request.getParameter("moimTitle");

        //채팅방이 존재하면 -> 채팅방으로 리다이렉트
        if (chatRoomService.existChatRoom(hostId, guestId, moimTitle)) {
            System.out.println("이미 존재해요~~");
            return "redirect:/";
        }

        //채팅방 생성하고 채팅방으로 이동
        ChatRoom chatRoom = chatRoomService.createChatRoom(hostId, guestId, moimTitle);
        return "redirect:/chat/room/" + chatRoom.getRoomId();
    }


    //채팅방 들어가기
    @GetMapping("/chat/room/{roomId}")
    public String getRoom(@PathVariable("roomId") Long roomId, Model model, HttpServletRequest request) {
        log.info("Get Chat Room, roomId : {}", roomId);


        //세션에 저장된 회원 정보 가져오기
        HttpSession session = request.getSession();
        Long memberNo = (Long) session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);
        model.addAttribute("member", member);

        //채팅방 조회하고 채팅방 메시지 리스트
        ChatRoom chatRoom = roomRepository.findById(roomId).get();

        //내가 속한 채팅방이 아니면 리다이렉트
        if (chatRoom.getRoomGuest() != member && chatRoom.getRoomHost() != member) {
            return "redirect:/moim";
        }
        List<ChatMessage> chatMessageByChatRoom = chatMessageRepository.findChatMessageByChatRoom(chatRoom);

        Member roomHost = chatRoom.getRoomHost();
        Member roomGuest = chatRoom.getRoomGuest();

        //로그인 사용자 와 채팅 상대방 넘겨주기
        if(roomHost == member){
            model.addAttribute("chatMember", roomGuest);
        }else {
            model.addAttribute("chatMember", roomHost);
        }
        model.addAttribute("loginMember", member);

        model.addAttribute("messages", chatMessageByChatRoom);
        model.addAttribute("room", chatRoom);
//        model.addAttribute("userName", member.getMemberName());
//        model.addAttribute("memberNo", memberNo);

        return "/chat/real-chatRoom";
    }

    //내 채팅방 목록 조회
    @GetMapping("/chat/rooms")
    public String rooms(Model model, HttpServletRequest request,
                        @PageableDefault(page = 0, size = 5, sort = "roomId", direction = Sort.Direction.DESC) Pageable pageable) {
        //로그인 한 사용자 조회
        HttpSession session = request.getSession(false);
        Long memberNo = (Long) session.getAttribute("memberNo");
        Member member = memberService.findMember(memberNo);
        model.addAttribute("member", member);

        //내가 속한 채팅방 모두 조회(페이징X, 페이징O)
//        List<ChatRoom> allChatRoom = chatRoomService.findAllChatRoom(memberNo, memberNo);
//
        Page<ChatRoom> allChatRoom = chatRoomService.findAllChatRoomPage(member, member, pageable);
        model.addAttribute("chatRoomList", allChatRoom);

        System.out.println(allChatRoom.getTotalPages());


//        return "/chat/chat-list";
        return "/chat/real-chatList";
    }

    //채팅방 나가기
    @PostMapping("/chat/room/{roomId}/exit")
    public String exitRoom(@PathVariable("roomId") Long roomId) {
        chatRoomService.exitChatRoom(roomId);

        return "redirect:/chat/rooms";
    }


//    //채팅방 목록 조회
//    @GetMapping("/rooms")
//    public String rooms(Model model) {
//        log.info("# ALL Chat Rooms");
//        model.addAttribute("list", roomRepository.findAll());
//
//        return "/chat/rooms";
//    }
//
//    //채팅방 개설
//    @PostMapping("/room")
//    public String create(@RequestParam String roomName, RedirectAttributes rttr) {
//        log.info("Creat Chat Room , roomName : {}" + roomName);
//        rttr.addFlashAttribute("roomName", chatRoomService.createChatRoom(roomName));
//
//        return "redirect:/chat/rooms";
//    }
//
//    //채팅방 들어가기
//    @GetMapping("/room")
//    public void getRoom(@RequestParam Long roomId, Model model, HttpServletRequest request){
//        log.info("Get Chat Room, roomId : {}", roomId);
//
//
//        //세션에 저장된 회원 정보 가져오기
//        HttpSession session = request.getSession();
//        Long memberNo = (Long)session.getAttribute("memberNo");
//        Member member = memberService.findMember(memberNo);
//
//
//        //채팅방 조회하고 채팅방 메시지 리스트
//        ChatRoom chatRoom = roomRepository.findById(roomId).get();
//        List<ChatMessage> chatMessageByChatRoom = chatMessageRepository.findChatMessageByChatRoom(chatRoom);
//
//        model.addAttribute("messages", chatMessageByChatRoom);
//        model.addAttribute("room", chatRoom);
//        model.addAttribute("userName", member.getMemberName());
//        model.addAttribute("memberNo", memberNo);
//    }
//
//    //채팅방 나가기
//    @GetMapping("/room-exit")
//    public String exitRoom(){
//        return "/chat/rooms";
//    }
}

