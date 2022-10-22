package com.example.vesselcheck.domain.service;

import com.example.vesselcheck.domain.Exception.FileUploadExceptionCustom;
import com.example.vesselcheck.domain.Repository.BlockRepository;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ComponentRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.*;
import com.example.vesselcheck.domain.service.Dto.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    private final static String hostName = "http://localhost:8080/images/";
    private final static String imageServerHostName = "http://34.64.185.37:9090/";

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
        return new ComponentInfo(components.getId(),components.getFaultType(),components.getComponentName(),
                components.getSequenceNumber(),components.getUploadImageName(),components.getImageUrlPath(),components.getWorkingStatus());
    }

    /**
     * 부품 업로드
     */
    public void registerComponentList(ComponentForm componentForm){
        Block block = blockRepository.findBlockByBlockName(componentForm.getBlockName());
        try {
            //부품 이미지 저장.
            List<Components> componentsList = fileStore.storeFiles(block, componentForm);
            //불량 판정.
            for (Components components : componentsList) {
                decisionV1(components);
            }
            // 결과 저장.
            componentRepository.saveAll(componentsList);
        }catch(Exception e){
            throw new FileUploadExceptionCustom("파일 업로드 에러");
        }
    }

    private void decisionV1(Components components){
        String imagePath = hostName + components.getStoreImageName();
        URI uri = UriComponentsBuilder.fromUriString(imageServerHostName +"v1/decision")
                .encode()
                .build()
                .toUri();
        ResponseEntity<componentImageResponse> result =
                new RestTemplate().exchange(new RequestEntity<>(new componentImageRequest(imagePath), HttpMethod.POST, uri), componentImageResponse.class);

        log.info("result = [{}]",result.getBody());
        componentImageResponse body = result.getBody();
        components.update(body.getClass_id(),body.getImage_url());
    }

    /**
     * 블럭 조회
     */
    public List<BlockInfo> searchBlock(BlockSearchCond blockSearchCond){
        return blockRepository.searchBlock(blockSearchCond).stream()
                .map(ele->new BlockInfo(ele.getVessel().getIMO(),ele.getVessel().getVesselName(),ele.getBlockName(),ele.getWorkingStep())).collect(Collectors.toList());
    }




    @Data
    static class componentImageResponse{
        private Integer class_id;
        private String image_url;
    }
    @Data
    @AllArgsConstructor
    static class componentImageRequest{
        private String image_path;
    }
}
