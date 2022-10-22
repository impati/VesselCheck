package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class DefaultClientService implements ClientService {
    private final ClientRepository clientRepository;
    public ClientInfo clientInfo(Long clientId){
        Client client =  clientRepository.findById(clientId).orElse(null);
        ClientInfo clientInfo = new ClientInfo(client);
        return clientInfo;
    }

    @Override
    public ClientInfo clientInfoBy(Long kakaoId) {
        log.info("kakaoId [{}]",kakaoId);
        Client client =  clientRepository.findByKakaoId(kakaoId).orElse(null);
        if(client == null) return  null;
        else {
            ClientInfo clientInfo = new ClientInfo(client);
            return clientInfo;
        }
    }


    /**
     *RestAPI 버전
     */
    @Override
    public Client clientRegister(String name, String belongs, String email,String duty, ClientType clientType, Long kakaoId) {
        Client client = Client.createClient(name,belongs,email,duty,clientType);
        client.setKakaoId(kakaoId);
        log.info("client = [{}]",client);
        clientRepository.save(client);
        return client;
    }

    public Long clientRegister(String name,String belongs,String email,ClientType clientType){
        Client client = Client.createClient(name,belongs,email,clientType);
        clientRepository.save(client);
        return client.getId();
    }







}
