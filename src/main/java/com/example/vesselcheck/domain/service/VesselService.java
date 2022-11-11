package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Exception.NoAuthorizationAboutVessel;
import com.example.vesselcheck.domain.Exception.NotFoundEntity;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ClientVesselRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.entity.VesselType;
import com.example.vesselcheck.domain.service.Dto.VesselInfo;
import com.example.vesselcheck.domain.service.Dto.VesselSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     *  선박 등록 기능 v2
     */
    public Vessel vesselRegister(Long kakoId,String IMO , String vesselName, VesselType vesselType, Integer ton,
                                 LocalDate startDate, LocalDate endDate){
        Client client = clientRepository.findByKakaoId(kakoId).orElse(null);
        Vessel vessel = Vessel.createVessel(IMO,vesselName,vesselType,ton,startDate,endDate);
        vesselRepository.save(vessel);

        ClientVessel clientVessel = new ClientVessel(client,vessel);
        clientVesselRepository.save(clientVessel);
        return vessel;
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
     * 사용자 선박 조회 v2
     */
    public List<Vessel> searchVesselV2(Long kakaoId){
        Client client = clientRepository.findByKakaoId(kakaoId).orElse(null);
        return clientVesselRepository.findVesselByClient(client.getId())
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
     * 사용자 선박 추가 - IMO - ver
     */
    public void addVesselOfClient(String IMO,Long clientId){
        Vessel vessel = vesselRepository.findByIMO(IMO).orElse(null);
        if(vessel == null) throw new NotFoundEntity("imo 번호가 잘못되었습니다");
        Client  client  = clientRepository.findById(clientId).orElse(null);
        ClientVessel clientVessel = new ClientVessel(client,vessel);
        clientVesselRepository.save(clientVessel);
    }



    /**
     * 선박 정보
     */
    public VesselInfo vesselInfo(Long vesselId){
        Vessel vessel = vesselRepository.findById(vesselId).orElse(null);
        return new VesselInfo(vessel.getId(),vessel.getVesselName(),vessel.getIMO(),vessel.getVesselType());
    }
    /**
     * 선박 정보
     */
    public VesselInfo vesselInfoV2(String imo){
        Vessel vessel = vesselRepository.findByIMO(imo).orElse(null);
        if(vessel == null) throw new NotFoundEntity("imo 번호가 잘못되었습니다");
        return new VesselInfo(vessel.getId(),vessel.getVesselName(),vessel.getIMO(),vessel.getVesselType(),
                vessel.getTotalTon(),vessel.getStartDate(),vessel.getEndDate(),true);
    }
     /**
     * 선박 정보 - IMO ver
     */
    public VesselInfo vesselInfo(Long clientId,  String vesselIMO){
        Vessel vessel = vesselRepository.findByIMO(vesselIMO).orElse(null);
        if(clientVesselRepository.existClientAndVessel(clientId, vessel.getId()) > 0){
            return new VesselInfo(vessel.getId(),vessel.getVesselName(),vessel.getIMO(),vessel.getVesselType(),
                    vessel.getTotalTon(),vessel.getStartDate(),vessel.getEndDate(),true);
        }
        else throw new NoAuthorizationAboutVessel("선박 접근 권한 오류");
    }


    /**
     * 전체 선박 조회
     * - 현재 사용자가 조회된 선박을 소유하고 있을 시 isOwnership true
     * - 현재 사용자가 조회된 선박을 소유하고 있지 않을 시 isOwnership false
     *
     * TODO : 더 좋은 방법 없을까? 현재 O(n^2)
     */
    public List<VesselInfo> vesselInfoList (Long clientId, VesselSearchCond vesselSearchCond ){
        List<Vessel> vesselList = vesselRepository.searchVessel(vesselSearchCond);
        List<ClientVessel> clientVessels = clientVesselRepository.findByVesselListAndClient(clientId, vesselList);
        return vesselList.stream().map(v->
                new VesselInfo(v.getId(),v.getIMO(),v.getVesselName(),v.getVesselType(),v.getTotalTon(),v.getStartDate(),v.getEndDate(),isOwner(v,clientVessels)))
                .collect(Collectors.toList());
    }
    private boolean isOwner(Vessel vessel,List<ClientVessel> clientVessels){
        return clientVessels.stream().filter(cv->cv.getVessel().equals(vessel)).findFirst().isPresent();
    }









}
