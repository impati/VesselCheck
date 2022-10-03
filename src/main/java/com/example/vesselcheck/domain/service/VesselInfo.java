package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.entity.VesselType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VesselInfo {
    private String IMO;
    private String vesselName;
    private VesselType vesselType;
}
