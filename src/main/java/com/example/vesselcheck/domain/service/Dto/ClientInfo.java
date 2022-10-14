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
    private RankPosition rankPosition;
    private Specialty specialty;
    private String clientTypeString;
    private Long clientId;
    public ClientInfo(Client client) {
        this.name = client.getName();
        this.belongs = client.getBelongs();
        this.email = client.getEmail();
        this.clientId = client.getId();

        if(client instanceof Manufacturer) {
            this.clientTypeString = "제조 업체";
            this.specialty = ((Manufacturer)client).getSpecialty();
        }
        else {
            this.clientTypeString = "검사관";
            this.rankPosition = ((Inspector)client).getRankPosition();
        }

    }
}
