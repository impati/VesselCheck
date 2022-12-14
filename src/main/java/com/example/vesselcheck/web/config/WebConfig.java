package com.example.vesselcheck.web.config;

import com.example.vesselcheck.domain.service.FileStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns(//비 로그인 사용자가 접속할 수 있는 페이지
//                        "/", "/members/add", "/login", "/logout",
//                        "/css/**", "/*.ico", "/error","/client/add","/images/**"
//                        ,"/image/**"
//                );
    }


    @Bean
    public IsLoginClient loginClient(){return new IsLoginClient();}
}
