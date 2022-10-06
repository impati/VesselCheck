package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;

public interface ClientService {
     ClientInfo clientInfo(Long clientId);
     Long clientRegister(String name, String belongs, String email, ClientType clientType);

}
