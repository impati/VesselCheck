package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.VesselType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VesselInfo {
    @JsonIgnore
    private Long vesselId;
    private String IMO;
    private String vesselName;
    private VesselType vesselType;
    private Integer ton;
    private LocalDate startDate; //착공 예정일
    private LocalDate endDate; // 준공 예정일일

    private boolean isOwnership;

    public VesselInfo(Long id, String IMO, String vesselName, VesselType vesselType, boolean b) {
        this.IMO = IMO;
        this.vesselName = vesselName;
        this.vesselType = vesselType;
    }

    public VesselInfo(Long vesselId, String IMO, String vesselName, VesselType vesselType) {
        this.vesselId = vesselId;
        this.IMO = IMO;
        this.vesselName = vesselName;
        this.vesselType = vesselType;
    }





}
