package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoResponse {
    private String name;
    private String email;
    private String belongs;
    private String duty;
    private ClientType client_type;
}
