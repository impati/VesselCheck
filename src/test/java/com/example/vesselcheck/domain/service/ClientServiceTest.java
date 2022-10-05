package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.web.controller.LoginController;
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

    @Autowired
    LoginController loginController;

    @Test
    public void Client() throws Exception{
        // given
        Long id = clientService.clientRegister("wnsduds1","sejong","yongs",ClientType.INSPECTOR);
        clientService.clientInfo(1L);



    }
    @Test
    public void login() throws Exception{


    }

}