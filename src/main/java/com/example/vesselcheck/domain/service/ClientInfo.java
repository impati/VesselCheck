package com.example.vesselcheck.domain.service;

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

    public ClientInfo(Client client) {
        this.name = client.getName();
        this.belongs = client.getBelongs();
        this.email = client.getEmail();
        if(client instanceof Manufacturer) this.specialty = ((Manufacturer)client).getSpecialty();
        else this.rankPosition = ((Inspector)client).getRankPosition();
    }
}
