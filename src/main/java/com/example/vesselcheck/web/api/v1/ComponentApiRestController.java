package com.example.vesselcheck.web.api.v1;

import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ComponentRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.Components;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.service.ComponentService;
import com.example.vesselcheck.domain.service.Dto.BlockSearchCond;
import com.example.vesselcheck.domain.service.Dto.ComponentForm;
import com.example.vesselcheck.domain.service.Dto.ComponentInfo;
import com.example.vesselcheck.domain.service.Dto.ComponentSearchCond;
import com.example.vesselcheck.domain.service.FileStore;
import com.example.vesselcheck.web.api.dto.BlockRegisterRequest;
import com.example.vesselcheck.web.api.dto.BlockSearchResponse;
import com.example.vesselcheck.web.api.dto.ComponentInfoList;
import com.example.vesselcheck.web.dto.ResponseKakaoClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComponentApiRestController {
    private final ComponentService componentService;
    private final VesselRepository vesselRepository;
    private final ClientRepository clientRepository;
    private final FileStore fileStore;
    private final ComponentRepository componentRepository;

    /**
     * 블럭 저장
     */
    @PostMapping("/v1/block/register")
    public void blockRegister(@RequestBody BlockRegisterRequest blockRegisterRequest, HttpServletRequest req){
        log.info("blockRegisterRequest = [{}]",blockRegisterRequest);
        Vessel vessel = vesselRepository.findByIMO(blockRegisterRequest.getImo()).orElse(null);
        Client client = clientRepository.findByKakaoId(KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId()).orElse(null);
        componentService.registerBlock(vessel.getId(),client.getId(),blockRegisterRequest.getBlock_name(),blockRegisterRequest.getWorking_step());
    }

    /**
     * 블럭 조회
     */
    @GetMapping("/v1/block/list")
    public BlockSearchResponse blockList(@ModelAttribute BlockSearchCond blockSearchCond){
        log.info("blockSearchCond [{}]",blockSearchCond);
        BlockSearchResponse resp = new BlockSearchResponse();
        resp.setBlockInfoList(componentService.searchBlock(blockSearchCond));
        return resp;
    }


    /**
     * 부품 업로드
     */
    @PostMapping("/v1/component/register")
    public String componentRegister(@ModelAttribute ComponentForm componentForm, HttpServletRequest req){
        log.info("componentForm [{}]",componentForm);
        componentService.registerComponentList(componentForm);
        return "OK";
    }
    /**
     * 부품 조회
     */
    @GetMapping("/v1/vessel/{imo}/component/list")
    public ComponentInfoList componentList(@PathVariable String imo , @ModelAttribute ComponentSearchCond componentSearchCond,
                              HttpServletRequest req){
        log.info("componentSearchCond [{}]",componentSearchCond);

        // 사용자 , 선박 세팅.
        Client client = clientRepository.findByKakaoId(KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId()).orElse(null);
        Vessel vessel = vesselRepository.findByIMO(imo).orElse(null);
        componentSearchCond.setClientId(client.getId());
        componentSearchCond.setVesselId(vessel.getId());

        ComponentInfoList resp = new ComponentInfoList();
        resp.setComponentInfoList(
        componentService.searchComponent(componentSearchCond)
                .stream()
                .map(c ->new ComponentInfo(c.getId(),c.getFaultType(),
                        c.getComponentName(),c.getSequenceNumber(),c.getUploadImageName(),c.getImageUrlPath(),c.getWorkingStatus()))
                .collect(Collectors.toList()));
        log.info("resp = [{}]",resp);
        return resp;
    }
    /**
     * 부품 상세
     */
    @GetMapping("/v1/component/{componentId}")
    public ComponentInfo componentInfo(@PathVariable Long componentId){
        return componentRepository.findById(componentId)
                .map(c ->new ComponentInfo(c.getId(),c.getFaultType(),
                        c.getComponentName(),c.getSequenceNumber(),c.getUploadImageName(),c.getImageUrlPath(),c.getWorkingStatus()))
                .get();
    }
    /**
     * 최신 버전
     * 이미지 응답.
     */
    @ResponseBody
    @GetMapping(value = "/image/{filename}")
    public ResponseEntity<Resource> userSearch(@PathVariable String filename) throws IOException {
        String inputFile = fileStore.getFullPath(filename);
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }





}
