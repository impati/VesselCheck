package com.example.vesselcheck.web.config;

import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Aspect
public class IsLoginClient {

    @Before("@annotation(com.example.vesselcheck.web.config.IsToken)")
    public void tokenCheck(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        for(int i = 0;i<args.length;i++){
            if(args[i] instanceof HttpServletRequest){
                HttpServletRequest req = (HttpServletRequest)args[i];
                KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization"));
            }
        }
    }
}
