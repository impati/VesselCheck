package com.example.vesselcheck.web.api.dto;


import com.example.vesselcheck.domain.entity.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSaveRequest {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String belongs;
    @NotBlank
    private String duty;
    @NotNull
    private ClientType clientType;
}
