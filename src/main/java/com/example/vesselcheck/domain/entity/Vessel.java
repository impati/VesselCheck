package com.example.vesselcheck.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Vessel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vessel_id")
    private Long id;

    private String IMO;
    private String vesselName;
    private VesselType vesselType;
    private Integer totalTon; // 총 톤 수
    private LocalDateTime startDate; //착공 예정일
    private LocalDateTime endDate; // 준공 예정일일



    public static Vessel createVessel(String IMO , String vesselName, VesselType vesselType){
        Vessel vessel = new Vessel();
        vessel.IMO = IMO;
        vessel.vesselName = vesselName;
        vessel.vesselType = vesselType;
        return vessel;
    }




}
