package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.ClientType;
import com.example.vesselcheck.domain.service.Dto.ClientInfo;
import com.example.vesselcheck.web.dto.JoinForm;

public interface ClientService {
     ClientInfo clientInfo(Long clientId);
     ClientInfo clientInfoBy(Long kakaoId);
     Client clientRegister(String name, String belongs, String email, ClientType clientType,Long kakaoId);
     Long clientRegister(String name, String belongs, String email, ClientType clientType);
}
