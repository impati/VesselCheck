package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.WorkingStatus;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ComponentUpdateDto {
    private String componentName;//enum?
    private String sequenceNumber;
    private WorkingStatus workingStatus;
}
