package school.healthboard.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import school.healthboard.interceptor.LogInterceptor;
import school.healthboard.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // Log 인터셉터  등록
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/front-end/css/**", "/*.ico", "/error");

        //로그인 check 인터셉터 등록
        //모든 경로에 적용하되, white list는 제외 시킨다.
        //white list -> 로그인*회원가입 페이지, 게시글 조회, 모임 조회, 홈 화면
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/questions", "/css/**", "/image/**", "/*.ico",
                        "/moim", "/test");
    }

}
