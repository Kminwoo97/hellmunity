package school.healthboard.service;

import school.healthboard.entity.Member;

import java.util.Optional;

public interface LoginService {
    Optional<Member> login(String loginId, String password);
}
