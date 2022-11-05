package com.example.vesselcheck.web.api.dto;
import com.example.vesselcheck.domain.entity.VesselType;
import lombok.Data;

@Data
public class VesselListRequest{
    private String imo;
    private String vesselName;
    private VesselType vesselType;
    private Integer ton;
}
