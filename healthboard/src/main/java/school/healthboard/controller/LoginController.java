package school.healthboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import school.healthboard.entity.dto.MemberSigninDto;
import school.healthboard.entity.Member;
import school.healthboard.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;


    //로그인 페이지로 이동
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") MemberSigninDto memberSigninDto) {
        return "login/loginForm";
    }



    //4. filter를 적용한 로그인
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginForm") MemberSigninDto form, BindingResult bindingResult, HttpServletRequest request,
                        @RequestParam(defaultValue = "/") String redirectURL) {

        //유효하지 않은 입력 폼 입력 시 로그인 폼으로 이동
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Optional<Member> loginMember = (Optional<Member>) loginService.login(form.getLoginId(), form.getPassword());

        //조회된 회원이 없는 경우
        if (loginMember.isEmpty()) {
            //reject()는 글로벌 오류이다.
            bindingResult.reject("loginfail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }


        /*로그인 성공 처리 TODO*/

        //request.getSession()은 세션이 있으면 반환하고
        //없으면 신규 생성해서 반환한다.
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 번호를 저장한다.
        session.setAttribute("memberNo", loginMember.get().getMemberNo());



        /*filter 에서 넘겨받은 redirecURL을 적용 시키기 위해서 이렇게 바꾸었다.
        로그인을 하지 않고 /items 로 갔다가 로그인 페이지로 리다이렉트 되었다가
        로그인을 하면 /items 페이지로 이동할 것이다. 반면에 로그인을 했으면 defaultvalue 인 "/"가
        적용되어서 home 으로 돌아갈 것이다.
         */
        return "redirect:" + redirectURL;
    }


    //3. HttpSession 을 이요한 로그아웃
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request) {
        //세션을 없애는 것이 목적이기 때문에 false 옵션을 주고 조회해 온다.
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate(); //세셔 만료
        }
        return "redirect:/";
    }
}
