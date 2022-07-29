package school.healthboard.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    public static final String LOG_ID = "logId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        //uuid 를 afterCompletion()에서도 찍고싶어서 request.setAttribute()를 사용한다.
        request.setAttribute(LOG_ID,uuid);

        /**
         * @RequestMapping : HandlerMethod 사용
         * 정적 리스소 : ResourceHttpRequestHandler 사용
         */
        //어떤 컨트롤러가 호출되는지 handler로 호출이 가능하다.
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;//여기에 호출할 컨트롤러 메소드의 모든 정보가 포함되어 있다.
        }
        log.info("REQUEST [{}][{}][{}]", uuid, requestURI, handler);

        return true; //true 로 하면 다음 진행사항인 컨트롤러가 호출된다.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("posthandler [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}][{}][{}]", uuid, requestURI, handler);

        //예외가 터진 경우
        if(ex != null){
            log.error("aftetCompletion error!!", ex);
        }
    }
}
