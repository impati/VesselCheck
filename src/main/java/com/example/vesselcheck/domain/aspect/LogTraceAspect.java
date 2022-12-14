package com.example.vesselcheck.domain.aspect;

import com.example.vesselcheck.domain.trace.LogTrace;
import com.example.vesselcheck.domain.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class LogTraceAspect {

    private final LogTrace logTrace;

    public LogTraceAspect(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    @Around("execution(* com.example.vesselcheck.web..*(..)) || execution(* com.example.vesselcheck.domain.service..*(..)) || execution(* com.example.vesselcheck.domain.Repository..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            String message = joinPoint.getSignature().toShortString();
            for(int i = 0;i<joinPoint.getArgs().length;i++){
                message += joinPoint.getArgs()[i] + " ";
            }

            status = logTrace.begin(message);

            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
