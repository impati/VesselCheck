package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ClientVesselRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientVessel;
import com.example.vesselcheck.domain.entity.Vessel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClientVesselService {
    private final ClientVesselRepository clientVesselRepository;
    private final ClientRepository clientRepository;
    private final VesselRepository vesselRepository;

    public void clientVesselAdd(Long clientId, Long vesselId){
        if(clientVesselRepository.existClientAndVessel(clientId,vesselId) != 0)return;
        Client client = clientRepository.findById(clientId).orElse(null);
        Vessel vessel = vesselRepository.findById(vesselId).orElse(null);
        ClientVessel clientVessel= new ClientVessel(client,vessel);
        clientVesselRepository.save(clientVessel);
    }
}
