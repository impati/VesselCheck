package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class ClientServiceTest {


    @Autowired
    ClientService clientService;

    @Autowired
    ClientRepository clientRepository;
    @Test
    @DisplayName("회원 저장 및 조회")
    public void Client() throws Exception{
        // given
        Long id = clientService.clientRegister("wnsduds1","sejong","yongs",ClientType.INSPECTOR);
        ClientInfo clientInfo = clientService.clientInfo(id);
        System.out.println(clientInfo.getName());


    }

}