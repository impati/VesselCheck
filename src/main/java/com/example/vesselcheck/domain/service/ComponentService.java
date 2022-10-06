package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Exception.FileUploadExceptionCustom;
import com.example.vesselcheck.domain.Repository.BlockRepository;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ComponentRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.Dto.ComponentForm;
import com.example.vesselcheck.domain.service.Dto.ComponentInfo;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;
import com.example.vesselcheck.domain.service.Dto.ComponentUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TODO : 이미지 처리
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ComponentService {
    private final BlockRepository blockRepository;
    private final ComponentRepository componentRepository;
    private final VesselRepository vesselRepository;
    private final ClientRepository clientRepository;
    private final FileStore fileStore;
    /**
     * 부품 등록 ..TODO :이미지 업로드 및 추가 기능 ..
     */
    public Components registerComponent(String blockName, String componentName, String sequenceNumber) {
        Block block = blockRepository.findBlockByBlockName(blockName);
        Components components = Components.createComponent(block,componentName,sequenceNumber);
        componentRepository.save(components);
        /**
         * 이미지 처리 및 모델 추론 .
         */
        return components;
    }

    /**
     * 부품 수정 ..TODO :이미지 재 업로드 및 추가 기능 ..
     */
    public Components updateComponent(Long componentId, ComponentUpdateDto updateDto){
        Components components = componentRepository.findById(componentId).orElse(null);
        components.update(updateDto);
        return components;
    }

    /**
     * 부품 조회
     */
    public List<Components> searchComponent(ComponentSearchCond componentSearchCond){
        return componentRepository.searchComponent(componentSearchCond);
    }

    /**
     *
     * 블럭 등록.
     */
    public Block registerBlock(Long vesselId,Long clientId,String blockName, WorkingStep workingStep){
        Vessel vessel = vesselRepository.findById(vesselId).orElse(null);
        Client client = clientRepository.findById(clientId).orElse(null);
        Block block = Block.createBlock(vessel,client,blockName,workingStep);
        blockRepository.save(block);
        return block;
    }

    /**
     * 부품 정보 리턴
     */
    public ComponentInfo componentInfo(Long componentId){
        Components components = componentRepository.findById(componentId).orElse(null);
        return new ComponentInfo(components.getFaultType(),components.getComponentName(),
                components.getSequenceNumber(),components.getUploadImageName(),components.getWorkingStatus());
    }

    /**
     * 부품 업로드
     */
    public void registerComponentList(ComponentForm componentForm){
        Block block = blockRepository.findBlockByBlockName(componentForm.getBlockName());
        try {
            List<Components> componentsList = fileStore.storeFiles(block, componentForm);
            componentRepository.saveAll(componentsList);
        }catch(Exception e){
            throw new FileUploadExceptionCustom("파일 업로드 에러");
        }
    }

}
