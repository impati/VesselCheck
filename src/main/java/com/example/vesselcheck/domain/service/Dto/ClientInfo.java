package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


/**
 * 사용자 정보
 */
@Data
@NoArgsConstructor
public class ClientInfo {
    private String name;
    private String belongs;
    private String email;
    private String duty;
    private ClientType clientType;
    private Long clientId;


    public static ClientInfo CreateClientInfo(Client client){
        if(client == null) return null;
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.name = client.getName();
        clientInfo.belongs = client.getBelongs();
        clientInfo.email = client.getEmail();
        clientInfo.clientId = client.getId();
        clientInfo.duty = client.getDuty();
        if(client instanceof Manufacturer) {
            clientInfo.clientType = ClientType.MANUFACTURER;
        }
        else {
            clientInfo.clientType = ClientType.INSPECTOR;
        }
        return clientInfo;
    }
}
