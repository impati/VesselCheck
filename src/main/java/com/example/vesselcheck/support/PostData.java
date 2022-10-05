package com.example.vesselcheck.support;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ClientVesselRepository;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.entity.VesselType;
import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.domain.service.VesselService;
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
    private final VesselService vesselService;
    private final ClientVesselRepository clientVesselRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void postData(){
        clientService.clientRegister("wnsduds1","sejong","yongs170@naver.com", ClientType.INSPECTOR);
        clientService.clientRegister("impati","sejong","impati0716@icloud.com", ClientType.MANUFACTURER);


        String IMOs [] = new String[]{"1234","abcd","가나다라","qwer","5678"};
        String names [] = new String[]{"행복선박","행운 선박","팝콘 선박","낙성대 선박","우주 선박"};
        VesselType vesselTypes[] = new VesselType[]{VesselType.A,VesselType.B,VesselType.C,VesselType.A,VesselType.B};

        /**
         * 선박 등록
         */
        for(int i =0;i<5;i++){
            vesselService.vesselRegister(IMOs[i],names[i],vesselTypes[i]);
        }








        log.info("End");
    }
}
