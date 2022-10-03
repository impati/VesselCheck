package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Repository.BlockRepository;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ComponentRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.*;
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

    /**
     * 부품 등록 ..TODO :이미지 업로드 및 추가 기능 ..
     */
    public Component registerComponent(Long blockId,String componentName,String sequenceNumber) {
        Block block = blockRepository.findById(blockId).orElse(null);
        Component  component = Component.createComponent(block,componentName,sequenceNumber);
        componentRepository.save(component);
        /**
         * 이미지 처리 및 모델 추론 .
         */
        return component;
    }

    /**
     * 부품 수정 ..TODO :이미지 재 업로드 및 추가 기능 ..
     */
    public Component updateComponent(Long componentId,ComponentUpdateDto updateDto){
        Component component = componentRepository.findById(componentId).orElse(null);
        component.update(updateDto);
        return component;
    }

    /**
     * 부품 조회
     */
    public List<Component> searchComponent(ComponentSearchCond componentSearchCond){
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












}
