package com.example.vesselcheck.domain.Repository;

import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.service.VesselSearchCond;

import java.util.List;

public interface VesselQueryDsl {
    List<Vessel> searchVessel(VesselSearchCond vesselSearchCond);
}
