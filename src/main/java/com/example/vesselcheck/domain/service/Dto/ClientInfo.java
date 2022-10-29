package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.*;

import lombok.Data;

import java.util.Optional;


/**
 * 사용자 정보
 */
@Data
public class ClientInfo {
    private String name;
    private String belongs;
    private String email;
    private String duty;
    private String clientTypeString;
    private ClientType clientType;
    private Long clientId;
    public ClientInfo(Client client) {
        this.name = client.getName();
        this.belongs = client.getBelongs();
        this.email = client.getEmail();
        this.clientId = client.getId();
        this.duty = client.getDuty();
        if(client instanceof Manufacturer) {
            this.clientTypeString = "제조 업체";
            this.clientType = ClientType.MANUFACTURER;
        }
        else {
            this.clientTypeString = "검사관";
            this.clientType = ClientType.INSPECTOR;
        }

    }
}
