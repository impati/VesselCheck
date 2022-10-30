package com.example.vesselcheck.web.api.dto;
import com.example.vesselcheck.domain.entity.VesselType;
import lombok.Data;

@Data
public class VesselListRequest{
    private String imo;
    private String vessel_name;
    private VesselType vessel_type;
    private Integer ton;
}
