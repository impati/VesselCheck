package com.example.vesselcheck.web.controller;

import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoginDto {
    private String loginId;
    private String password;
    private String name;
    private String belongs;
    private String email;
    private ClientType clientType;

}
