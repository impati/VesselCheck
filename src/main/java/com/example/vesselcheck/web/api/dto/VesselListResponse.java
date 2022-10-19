package com.example.vesselcheck.web.api.dto;

import com.example.vesselcheck.domain.service.Dto.VesselInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class VesselListResponse {
    List<VesselInfo> vesselInfoList = new ArrayList<>();
}
