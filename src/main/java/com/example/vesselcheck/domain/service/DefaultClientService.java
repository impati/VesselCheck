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
        return ClientInfo.CreateClientInfo(client);
    }

    /**
     * @param kakaoId
     */
    @Override
    public ClientInfo clientInfoBy(Long kakaoId) {
        Client client =  clientRepository.findByKakaoId(kakaoId).orElse(null);
        return ClientInfo.CreateClientInfo(client);
    }


    /**
     *RestAPI 버전
     */
    @Override
    public Client clientRegister(String name, String belongs, String email,String duty, ClientType clientType, Long kakaoId) {
        Client client = Client.createClient(name,belongs,email,duty,clientType,kakaoId);
        clientRepository.save(client);
        return client;
    }

    public Long clientRegister(String name,String belongs,String email,ClientType clientType){
        Client client = Client.createClient(name,belongs,email,clientType);
        clientRepository.save(client);
        return client.getId();
    }







}
