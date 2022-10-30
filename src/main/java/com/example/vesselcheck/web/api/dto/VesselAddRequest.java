package com.example.vesselcheck.web.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VesselAddRequest{
    @NotBlank
    private String imo;
}
