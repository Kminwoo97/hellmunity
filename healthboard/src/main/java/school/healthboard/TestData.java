package school.healthboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.healthboard.entity.CommunityBoard;
import school.healthboard.entity.Member;
import school.healthboard.repository.CommunityBoardRepository;
import school.healthboard.repository.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {
    private final MemberRepository memberRepository;
    private final CommunityBoardRepository communityBoardRepository;

    @PostConstruct
    public void init(){
        Member member1 = new Member();
        member1.setMemberId("test");
        member1.setMemberPasswd("test");
        member1.setMemberName("test");
        Member saveMember1 = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setMemberId("mwkim");
        member2.setMemberPasswd("mwkim");
        member2.setMemberName("mwkim");
        Member saveMember2 = memberRepository.save(member2);


        Member member3 = new Member();
        member3.setMemberId("test2");
        member3.setMemberPasswd("test2");
        member3.setMemberName("test2");
        Member saveMember3 = memberRepository.save(member3);

        CommunityBoard communityBoard1 = new CommunityBoard();
        communityBoard1.setCommunityBoardTitle("test1");
        communityBoard1.setCommunityBoardDetail("test입니다.");
        communityBoard1.setWriter(saveMember1);
        communityBoardRepository.save(communityBoard1);

        CommunityBoard communityBoard2 = new CommunityBoard();
        communityBoard2.setCommunityBoardTitle("테스트 테스트");
        communityBoard2.setCommunityBoardDetail("훌랄라라라");
        communityBoard2.setWriter(saveMember2);
        communityBoardRepository.save(communityBoard2);

        CommunityBoard communityBoard3 = new CommunityBoard();
        communityBoard3.setCommunityBoardTitle("테스트 제목");
        communityBoard3.setCommunityBoardDetail("아라라라라");
        communityBoard3.setWriter(saveMember3);
        communityBoardRepository.save(communityBoard3);

        for(int i=0; i<=100; i++){
            CommunityBoard test = new CommunityBoard();
            test.setCommunityBoardTitle("test"+i);
            test.setWriter(saveMember3);
            test.setCommunityBoardDetail("test"+i+" 입니다~~");
            communityBoardRepository.save(test);
        }
    }
}
