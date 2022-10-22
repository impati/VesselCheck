package com.example.vesselcheck.domain.service.Dto;

import com.example.vesselcheck.domain.entity.VesselType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 선박 검색 조건 DTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VesselSearchCond {
    private String IMO;
    private String name;
    private VesselType vesselType;


}
