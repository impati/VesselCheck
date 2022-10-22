package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.WorkingStep;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlockSearchCond {
    private String imo;
    private String block_name;
    private WorkingStep working_step;
}
