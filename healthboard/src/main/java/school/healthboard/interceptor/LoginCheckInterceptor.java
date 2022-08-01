package school.healthboard.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import school.healthboard.SessionConst;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    // HandlerInterceptor 는 prehandle()만 있어도 된다.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //요청 URI 가져오기
        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉트 실행 {}", requestURI);

        //세션 가져오기
        HttpSession session = request.getSession();

        /*
        if(session.getAttribute(SessionConst.LOGIN_MEMBER) != null){
            log.info("기존의 로그인 되어 있던 사용자 세션 만료");
            session.invalidate();
        }
         */


        //미인증 사용자는 login 페이지로 보낸다.
        //필터와 비교하면 화이트 리스트도 체크하고 그랬는데 로직이 엄청 단순해졌다..
        //인터셉트는 이러한 것은 WebConfig 에서 등록할 때 가능하다.
        if (session == null || session.getAttribute(session.getId()) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/api/login?redirectURL="+requestURI);
        }
        return true; // 다음 진행으로 넘어가서 컨트롤러 호출
    }
}

