package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.WorkingStep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockInfo {
    private String imo;
    private String vesselName;
    private String blockName;
    private WorkingStep workingStep;
}
