package com.example.vesselcheck.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
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
    @Enumerated(value = EnumType.STRING)
    private VesselType vesselType;
    private Integer totalTon; // 총 톤 수
    private LocalDate startDate; //착공 예정일
    private LocalDate endDate; // 준공 예정일일



    public static Vessel createVessel(String IMO , String vesselName, VesselType vesselType){
        Vessel vessel = new Vessel();
        vessel.IMO = IMO;
        vessel.vesselName = vesselName;
        vessel.vesselType = vesselType;
        return vessel;
    }

    public static Vessel createVessel(String imo,String vesselName ,VesselType vesseltype,
                                      Integer ton,LocalDate startDate,LocalDate endDate){
       Vessel vessel = new Vessel();
       vessel.IMO = imo;
       vessel.vesselName = vesselName;
       vessel.vesselType = vesseltype;
       vessel.totalTon = ton;
       vessel.startDate = startDate;
       vessel.endDate = endDate;
       return vessel;
    }





}
