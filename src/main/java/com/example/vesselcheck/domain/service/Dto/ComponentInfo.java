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
    private String imageName;
    private WorkingStatus workingStatus;

    public ComponentInfo(FaultType faultType, String componentName, String sequenceNumber, String imageName, WorkingStatus workingStatus) {
        this.faultType = faultType;
        this.componentName = componentName;
        this.sequenceNumber = sequenceNumber;
        this.imageName = imageName;
        this.workingStatus = workingStatus;
    }
}
