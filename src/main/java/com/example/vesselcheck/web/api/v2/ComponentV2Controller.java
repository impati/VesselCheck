package com.example.vesselcheck.web.api.v2;

import com.example.vesselcheck.domain.Exception.NotFoundEntity;
import com.example.vesselcheck.domain.Repository.BlockRepository;
import com.example.vesselcheck.domain.Repository.ClientRepository;
import com.example.vesselcheck.domain.Repository.ComponentRepository;
import com.example.vesselcheck.domain.Repository.VesselRepository;
import com.example.vesselcheck.domain.entity.Client;
import com.example.vesselcheck.domain.entity.Vessel;
import com.example.vesselcheck.domain.service.ComponentService;
import com.example.vesselcheck.domain.service.Dto.*;
import com.example.vesselcheck.domain.service.FileStore;
import com.example.vesselcheck.web.api.dto.*;
import com.example.vesselcheck.web.api.v1.KakaoLogInConst;
import com.example.vesselcheck.web.config.IsToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ComponentV2Controller{
    private final FileStore fileStore;
    private final ComponentRepository componentRepository;
    private final ComponentService componentService;
    private final VesselRepository vesselRepository;
    private final ClientRepository clientRepository;

    @PostMapping("/v2/block/register")
    @IsToken
    public PostResult blockRegister(@Valid @RequestBody BlockRegisterRequest blockRegisterRequest , HttpServletRequest req){
        Vessel vessel = vesselRepository.findByIMO(blockRegisterRequest.getImo()).orElse(null);
        Client client = clientRepository.findByKakaoId(KakaoLogInConst.getKaKaoInfo(req.getHeader("Authorization")).getId()).orElse(null);
        componentService.registerBlock(vessel.getId(),client.getId(),blockRegisterRequest.getBlockName(),blockRegisterRequest.getWorkingStep());
        return new PostResult("OK");
    }

    @GetMapping("/v2/block/list")
    @IsToken
    public BlockSearchResponse blockSearch(@ModelAttribute BlockSearchCond blockSearchCond,HttpServletRequest req){
        BlockSearchResponse resp = new BlockSearchResponse();
        resp.setBlockInfoList(componentService.searchBlock(blockSearchCond));
        return resp;
    }



    @PostMapping("/v2/component/register")
    @IsToken
    public PostResult componentRegister(@Valid @ModelAttribute ComponentForm componentForm,HttpServletRequest req){
        componentService.registerComponentList(componentForm);
        return new PostResult("OK");
    }



    @GetMapping("/v2/vessel/{imo}/component/list")
    @IsToken
    public ComponentInfoList componentList(@PathVariable String imo , @ModelAttribute ComponentSearchCond componentSearchCond,
                                           HttpServletRequest req){
        log.info("ComponentSearchCond {}",componentSearchCond);
        Vessel vessel = vesselRepository.findByIMO(imo).orElse(null);
        if(vessel == null) throw new NotFoundEntity("선박 imo 번호가 잘못되었습니다.");
        componentSearchCond.setVesselId(vessel.getId());
        ComponentInfoList resp = new ComponentInfoList();
        resp.setComponentInfoList(
                componentService.searchComponent(componentSearchCond)
                        .stream()
                        .map(c ->new ComponentInfo(c.getId(),c.getFaultType(),
                                c.getComponentName(),c.getSequenceNumber(),c.getUploadImageName(),c.getImageUrlPath(),c.getWorkingStatus(),
                                c.getBlock().getBlockName(),c.getBlock().getVessel().getIMO()))
                        .collect(Collectors.toList()));
        return resp;
    }

    @GetMapping("/v2/component/{componentId}")
    @IsToken
    public ComponentInfo componentInfo (@PathVariable Long componentId , HttpServletRequest req){
        return componentRepository.findById(componentId)
                .map(c ->new ComponentInfo(c.getId(),c.getFaultType(),
                        c.getComponentName(),c.getSequenceNumber(),c.getUploadImageName(),c.getImageUrlPath(),c.getWorkingStatus(),
                        c.getBlock().getBlockName(),c.getBlock().getVessel().getIMO()))
                .get();
    }




    /**
     * 최신 버전
     * 이미지 응답.
     */
    @ResponseBody
    @GetMapping(value = "/image/{filename}")
    @IsToken
    public ResponseEntity<Resource> userSearch(@PathVariable String filename) throws IOException {
        String inputFile = fileStore.getFullPath(filename);
        Path path = new File(inputFile).toPath();
        FileSystemResource resource = new FileSystemResource(path);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .body(resource);
    }

    @ResponseBody
    @PatchMapping(value = "/v2/component/re-upload")
    @IsToken
    public PostResult reUpload(@Valid @ModelAttribute ComponentReForm componentReForm, HttpServletRequest req){
        componentService.reUpload(componentReForm);
        return new PostResult("ok");
    }


    @PatchMapping(value = "/v2/component/working-status")
    @IsToken
    public PostResult changeWorkingStatus(@Valid @RequestBody WorkingChangeDto workingChangeDto,HttpServletRequest req){
        componentService.workingChange(workingChangeDto.getComponentId());
        return new PostResult("ok");
    }


















}
