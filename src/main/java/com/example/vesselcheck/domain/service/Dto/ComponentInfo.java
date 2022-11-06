package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.FaultType;
import com.example.vesselcheck.domain.entity.WorkingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComponentInfo {
    private Long componentId;
    private FaultType faultType;
    private String componentName;//enum?
    private String sequenceNumber;
    private String uploadImageName;
    private String storeImageUrl;
    private WorkingStatus workingStatus;
    private String blockName;
    private String imo;

    public ComponentInfo(FaultType faultType, String componentName, String sequenceNumber, WorkingStatus workingStatus) {
        this.faultType = faultType;
        this.componentName = componentName;
        this.sequenceNumber = sequenceNumber;
        this.workingStatus = workingStatus;
    }

    public ComponentInfo(Long componentId, FaultType faultType, String componentName, String sequenceNumber, WorkingStatus workingStatus) {
        this.componentId = componentId;
        this.faultType = faultType;
        this.componentName = componentName;
        this.sequenceNumber = sequenceNumber;
        this.workingStatus = workingStatus;
    }

    public ComponentInfo(Long id, FaultType faultType, String componentName, String sequenceNumber, String uploadImageName, String imageUrlPath, WorkingStatus workingStatus) {

    }
}
