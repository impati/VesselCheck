package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.FaultType;
import com.example.vesselcheck.domain.entity.WorkingStatus;
import lombok.Data;
import lombok.ToString;

/**
 * λΆν κ²μ DTO
 */
@Data
public class ComponentSearchCond {
    private FaultType faultType;
    private String componentName;
    private String sequenceNumber;
    private WorkingStatus workingStatus;
    private String blockName;
    private Long clientId;
    private Long vesselId;

}
