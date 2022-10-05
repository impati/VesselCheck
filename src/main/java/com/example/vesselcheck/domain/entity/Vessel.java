package com.example.vesselcheck.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


    public static Vessel createVessel(String IMO , String vesselName, VesselType vesselType){
        Vessel vessel = new Vessel();
        vessel.IMO = IMO;
        vessel.vesselName = vesselName;
        vessel.vesselType = vesselType;
        return vessel;
    }






}
