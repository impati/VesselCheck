package com.example.vesselcheck.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Block extends BaseEntity{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel_id")
    private Vessel vessel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;


    private String blockName;
    private WorkingStep workingStep;


    public static Block createBlock(Vessel vessel , Client client,
                                    String blockName, WorkingStep workingStep){
        Block block = new Block();
        block.blockName = blockName;
        block.client = client;
        block.vessel = vessel;
        block.workingStep = workingStep;
        return block;
    }
}
