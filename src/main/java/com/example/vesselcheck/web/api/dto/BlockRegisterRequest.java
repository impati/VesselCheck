package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.entity.WorkingStep;
import lombok.Data;

@Data
public class BlockRegisterRequest{
    private String imo;
    private String block_name;
    private WorkingStep working_step;
}

