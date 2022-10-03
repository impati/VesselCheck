package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.FaultType;
import com.example.vesselcheck.domain.entity.WorkingStatus;
import lombok.Data;

/**
 * 부품 검색 DTO
 */
@Data
public class ComponentSearchCond {
    private FaultType faultType;
    private String componentName;
    private String sequenceNumber;
    private WorkingStatus workingStatus;
}
