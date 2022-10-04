package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.entity.ClientType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class DefaultClientServiceTest {
    @Autowired
    ClientService clientService;
    @Autowired
    ClientRepository clientRepository;



    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("")
    public void inheritance() throws Exception{
        Long id = clientService.clientRegister("wnsduds1","sejong","yongs", ClientType.INSPECTOR);
        em.flush();
        em.clear();
        clientService.clientInfo(id);

    }


}