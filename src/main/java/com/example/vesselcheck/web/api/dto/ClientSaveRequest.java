package com.example.vesselcheck.web.api.dto;


import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSaveRequest {
    private String name;
    private String email;
    private String belongs;
    private String duty;
    private ClientType client_type;
}
