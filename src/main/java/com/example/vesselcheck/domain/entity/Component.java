package com.example.vesselcheck.domain.entity;

import com.example.vesselcheck.domain.service.ComponentUpdateDto;
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

    private String componentName;//enum?
    private String sequenceNumber;
    private String imageName;
    private WorkingStatus workingStatus;

    @ManyToOne
    @JoinColumn(name="block_id")
    private Block block;

    public static Component createComponent(Block block , String componentName, String sequenceNumber){
        Component component = new Component();
        component.componentName = componentName;
        component.block = block;
        component.sequenceNumber = sequenceNumber;
        return component;
    }


    public void update(ComponentUpdateDto updateDto){
        this.componentName = updateDto.getComponentName();
        this.sequenceNumber = updateDto.getSequenceNumber();
        this.workingStatus = updateDto.getWorkingStatus();
    }
}
