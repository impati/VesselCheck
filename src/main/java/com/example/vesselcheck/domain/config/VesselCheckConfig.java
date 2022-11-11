package com.example.vesselcheck.domain.config;

import com.example.vesselcheck.domain.aspect.LogTraceAspect;
import com.example.vesselcheck.domain.service.FileStore;
import com.example.vesselcheck.domain.trace.LogTrace;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class VesselCheckConfig {
    private final EntityManager em;
    private final LogTrace logTrace;
    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(em);
    }

    @Bean
    public FileStore fileStore(){
        return new FileStore();
    }

    @Bean
    public LogTraceAspect logTraceAspect(){return new LogTraceAspect(logTrace);}

}
