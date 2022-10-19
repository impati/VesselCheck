package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClientInfoResponse {
    private String name;
    private String email;
    private String belongs;
    private String duty;
    private ClientType clientType;
}
