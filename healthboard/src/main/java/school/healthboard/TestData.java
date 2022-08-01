package school.healthboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import school.healthboard.entity.Member;
import school.healthboard.repository.MemberRepository;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init(){
        Member member1 = new Member();
        member1.setMemberId("test");
        member1.setMemberNickname("test");
        member1.setMemberPasswd("test");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setMemberId("mwkim");
        member2.setMemberNickname("민우킴");
        member2.setMemberPasswd("mwkim");
        memberRepository.save(member2);


        Member member3 = new Member();
        member2.setMemberId("test2");
        member2.setMemberNickname("test2");
        member2.setMemberPasswd("test2");
        memberRepository.save(member2);
    }
}
