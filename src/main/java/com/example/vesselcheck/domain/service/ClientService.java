package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;
    public ClientInfo clientInfo(Long clientId){
        return new ClientInfo(clientRepository.findById(clientId).orElse(null));
    }
}
