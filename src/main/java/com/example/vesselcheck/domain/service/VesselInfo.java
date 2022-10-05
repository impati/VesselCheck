package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.VesselType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VesselInfo {
    private Long VesselId;
    private String IMO;
    private String vesselName;
    private VesselType vesselType;
    private boolean isOwnership;

    public VesselInfo(String IMO, String vesselName, VesselType vesselType) {
        this.IMO = IMO;
        this.vesselName = vesselName;
        this.vesselType = vesselType;
    }
}
