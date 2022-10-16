package com.example.vesselcheck.domain.entity;

import com.example.vesselcheck.domain.service.Dto.ComponentUpdateDto;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Components extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;

    private FaultType faultType;

    private String componentName;//enum?
    private String sequenceNumber;
    private String uploadImageName;
    private String storeImageName;
    private WorkingStatus workingStatus;
    private String imageUrlPath;
    @ManyToOne
    @JoinColumn(name="block_id")
    private Block block;

    public static Components createComponent(Block block , String componentName, String sequenceNumber){
        Components components = new Components();
        components.componentName = componentName;
        components.block = block;
        components.sequenceNumber = sequenceNumber;
        return components;
    }
    public static Components createComponent(Block block , String componentName, String sequenceNumber,String upload,String store){
        Components components = new Components();
        components.componentName = componentName;
        components.block = block;
        components.sequenceNumber = sequenceNumber;
        components.uploadImageName = upload;
        components.storeImageName = store;
        return components;
    }



    public void update(Integer classId , String urlPath){
        if(classId == -1){
            this.faultType = FaultType.GOOD;
            this.workingStatus = WorkingStatus.W;
            this.imageUrlPath = urlPath;
        }
        else{
            this.faultType = FaultType.F1;
            this.workingStatus = WorkingStatus.A;
            this.imageUrlPath = urlPath;
        }



    }
    public void update(ComponentUpdateDto updateDto){
        this.componentName = updateDto.getComponentName();
        this.sequenceNumber = updateDto.getSequenceNumber();
        this.workingStatus = updateDto.getWorkingStatus();
    }
}
