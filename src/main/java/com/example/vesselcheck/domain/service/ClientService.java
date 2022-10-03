package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
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
        log.info("clientId {}",clientId);
        clientRepository.findAll().stream().forEach(ele-> System.out.println(ele));
        Client client = clientRepository.findById(clientId).get();
        log.info("client {}",client);
        ClientInfo clientInfo = new ClientInfo(client);
        log.info("clientInfo {}",clientInfo );
        return clientInfo;
    }

    public Long clientRegister(String name,String belongs,String email,ClientType clientType){
        Client client = Client.createClient(name,belongs,email,clientType);
        clientRepository.save(client);
        return client.getId();
    }







}
