package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ClientVesselRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.entity.VesselType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class VesselService {
    private final VesselRepository vesselRepository;
    private final ClientRepository clientRepository;
    private final ClientVesselRepository clientVesselRepository;
    /**
     *  선박 등록 기능
     */
    public Vessel vesselRegister(String IMO , String vesselName, VesselType vesselType){
        Vessel vessel = Vessel.createVessel(IMO,vesselName,vesselType);
        vesselRepository.save(vessel);
        return vessel;
    }

    /**
     * 전체 선박 조회
     */
    public List<Vessel> searchVessel(VesselSearchCond vesselSearchCond){
        return vesselRepository.searchVessel(vesselSearchCond);
    }

    /**
     * 사용자 선박 조회
     */
    public List<Vessel> searchVessel(Long clientId){
        return clientVesselRepository.findVesselByClient(clientId)
                .stream()
                .map(cv -> cv.getVessel()).collect(Collectors.toList());
    }


    /**
     * 사용자 선박 추가
     */
    public void addVesselOfClient(Long vesselId,Long clientId){
        Vessel vessel = vesselRepository.findById(vesselId).orElse(null);
        Client  client  = clientRepository.findById(clientId).orElse(null);
        ClientVessel clientVessel = new ClientVessel(client,vessel);
        clientVesselRepository.save(clientVessel);
    }


    /**
     * 선박 정보
     */
    public VesselInfo vesselInfo(String IMO , String vesselName, VesselType vesselType){
        return new VesselInfo(IMO,vesselName,vesselType);
    }

}
