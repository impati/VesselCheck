package com.example.vesselcheck.domain.entity;

import javax.persistence.*;

@Entity
public class Vessel extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vessel_id")
    private Long id;

    private String IMO;
    private String vesselName;
    private VesselType vesselType;

}
