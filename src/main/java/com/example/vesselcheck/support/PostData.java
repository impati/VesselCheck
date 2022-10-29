package com.example.vesselcheck.support;

import com.example.vesselcheck.domain.Repository.*;
import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.ClientService;
import com.example.vesselcheck.domain.service.ComponentService;
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
    private final ComponentService componentService;
    private final VesselRepository vesselRepository;
    private final ComponentRepository componentRepository;
    private final ClientRepository clientRepository;
    private final BlockRepository blockRepository;



    @EventListener(ApplicationReadyEvent.class)
    public void postData(){
        clientService.clientRegister("wnsduds1","sejong","yongs170@naver.com", ClientType.INSPECTOR);
        clientService.clientRegister("impati","sejong","impati0716@icloud.com", ClientType.MANUFACTURER);


        String IMOs [] = new String[]{"1234","abcd","가나다라","qwer","5678"};
        String names [] = new String[]{"행복선박","행운 선박","팝콘 선박","낙성대 선박","우주 선박"};
        VesselType vesselTypes[] = new VesselType[]{VesselType.General,VesselType.General,VesselType.General,VesselType.General,VesselType.General};

        /**
         * 선박 등록 , 블록 , 부품 등록
         */
        for(int i =0;i<5;i++){
            Vessel vessel = Vessel.createVessel(IMOs[i],names[i],vesselTypes[i]);
            vesselRepository.save(vessel);

            if(i == 0) {
                Block block = Block.createBlock(vessel, clientRepository.findById(3L).orElse(null), "aaa", WorkingStep.PieceAssembly);
                blockRepository.save(block);
                Components components1 = Components.createComponent(block, "보온채", "11-11");
                Components components2 = Components.createComponent(block, "배관", "22-22");
                Components components3 = Components.createComponent(block, "모재", "33-33");
                componentRepository.save(components1);
                componentRepository.save(components2);
                componentRepository.save(components3);
            }
        }

        log.info("End");
    }
}
