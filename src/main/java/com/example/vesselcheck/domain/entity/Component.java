package com.example.vesselcheck.domain.entity;

import lombok.Getter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;

@Entity
@Getter
public class Component extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;

    private FaultType faultType;

    private String componentName;
    private String sequenceNumber;
    private String imageName;
    private WorkingStatus workingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="block_id")
    private Block block;

}
