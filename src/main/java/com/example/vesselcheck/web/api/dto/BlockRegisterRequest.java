package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.WorkingStep;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BlockRegisterRequest{
    @NotBlank
    private String imo;
    @NotBlank
    private String blockName;
    @NotNull
    private WorkingStep workingStep;
}

