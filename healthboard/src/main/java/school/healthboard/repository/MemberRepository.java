package school.healthboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.healthboard.entity.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByMemberIdAndMemberPasswd(String loginId, String password);
    Member findByMemberNo(Long memberNo);
    /**
     * 유효성 검사 - 중복체크(아이디, 닉네임)
     * 중복 : true
     * 중복이 아닌 경우 : false
     */
    boolean existsByMemberId(String memberId);
    boolean existsByMemberName(String memberName);

}
