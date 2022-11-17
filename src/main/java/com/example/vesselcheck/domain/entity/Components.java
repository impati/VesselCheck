package com.example.vesselcheck.domain.entity;

import com.example.vesselcheck.domain.service.Dto.ComponentUpdateDto;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@ToString
public class Components extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "component_id")
    private Long id;
    @Enumerated(EnumType.STRING)
    private FaultType faultType;
    private String componentName;//enum?
    private String sequenceNumber;
    private String uploadImageName;
    private String storeImageName;
    @Enumerated(EnumType.STRING)
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


    public void update(String storeFileName){
        this.storeImageName = storeFileName;
    }public void update(WorkingStatus workingStatus){
        this.workingStatus = workingStatus;
    }
    public void update(Integer classId , String urlPath){
        if(classId == -1){
            this.faultType = FaultType.GOOD;
            this.workingStatus = WorkingStatus.InspectionComplete;
            this.imageUrlPath = urlPath;
        }
        else{
            this.faultType = Arrays.stream(FaultType.values()).filter(f->f.getClassId().equals(classId)).findFirst().get();
            this.workingStatus = WorkingStatus.WorkingStart;
            this.imageUrlPath = urlPath;
        }



    }
    public void update(ComponentUpdateDto updateDto){
        this.componentName = updateDto.getComponentName();
        this.sequenceNumber = updateDto.getSequenceNumber();
        this.workingStatus = updateDto.getWorkingStatus();
    }
}
