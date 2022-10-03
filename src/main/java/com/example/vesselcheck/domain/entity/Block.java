package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Block extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    private String blockName;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel_id")
    private Vessel vessel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    private WorkingStep workingStep;

}
