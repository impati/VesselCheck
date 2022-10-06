package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.VesselType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VesselInfo {
    private Long vesselId;
    private String IMO;
    private String vesselName;
    private VesselType vesselType;
    private boolean isOwnership;

    public VesselInfo(String IMO, String vesselName, VesselType vesselType) {
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
