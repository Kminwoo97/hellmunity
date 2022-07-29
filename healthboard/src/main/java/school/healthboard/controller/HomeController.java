package school.healthboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import school.healthboard.SessionConst;
import school.healthboard.dto.MemberSigninDto;
import school.healthboard.entity.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    //HttpSession을 사용한 로그인 화면으로 이동
    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        //옵션을 false 줘서 로그인 한 사용자만 정보를 가져온다.
        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }

        //세션에 회원 데이터가 없으면 home으로 이동
        Member loginMember = (Member)session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(loginMember == null){
            return "home";
        }

        System.out.println(loginMember.getMemberNo());

        //세션이 유지되면 로그인 한 페이지로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
