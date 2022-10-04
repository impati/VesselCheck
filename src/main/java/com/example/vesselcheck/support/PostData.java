package com.example.vesselcheck.support;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
@Slf4j
@RequiredArgsConstructor
public class PostData {
    private final ClientService clientService;


    @EventListener(ApplicationReadyEvent.class)
    public void postData(){
        clientService.clientRegister("wnsduds1","sejong","yongs170@naver.com", ClientType.INSPECTOR);
        clientService.clientRegister("impati","sejong","impati0716@icloud.com", ClientType.MANUFACTURER);

        log.info("End");
    }
}
