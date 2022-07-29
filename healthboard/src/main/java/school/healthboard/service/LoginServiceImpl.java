package school.healthboard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.healthboard.entity.Member;
import school.healthboard.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final MemberRepository memberRepository;

    //로그인
    public Optional<Member> login(String loginId, String password){
        Optional<Member> findMember = memberRepository.findMemberByMemberIdAndMemberPasswd(
                loginId, password);

        //검색이 안되면 null 반환
        if(findMember == null){
            return null;
        }

        return findMember;
    }

    //로그아웃
}
